package me.decce.transformingbase.service;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.toml.TomlFormat;
import me.decce.transformingbase.constants.Constants;
import me.decce.transformingbase.core.ProgressPeekConfig;
import me.decce.transformingbase.core.ProgressPeekCore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ConfigLoader {
    private static final Logger LOGGER = LogManager.getLogger(Constants.MOD_NAME);
    private static final Path CONFIG_PATH;
    private static final Path CONFIG_FILE;

    static {
        CONFIG_PATH = Paths.get("config");
        CONFIG_FILE = CONFIG_PATH.resolve(Constants.MOD_ID + ".toml");
        try {
            if (!Files.exists(CONFIG_PATH)) {
                Files.createDirectories(CONFIG_PATH);
            }
        } catch (IOException ignored) {}
    }

    private static CommentedFileConfig makeNightConfig() {
        return CommentedFileConfig.builder(CONFIG_FILE, TomlFormat.instance())
                .preserveInsertionOrder()
                .sync()
                .build();
    }

    public static void save(ProgressPeekConfig config) {
        try (var night = toNightConfig(config)) {
            night.save();
        } catch (Exception e) {
            LOGGER.error("Failed to save configuration!", e);
        }
    }

    public static ProgressPeekConfig load() {
        return loadConfig();
    }

    private static ProgressPeekConfig loadConfig() {
        if (CONFIG_FILE.toFile().exists()) {
            try {
                return fromNightConfig();
            } catch (Exception e) {
                LOGGER.error("Failed to read configuration!", e);
            }
        }
        return new ProgressPeekConfig();
    }

    private static CommentedFileConfig toNightConfig(ProgressPeekConfig object) {
        var config = makeNightConfig();
        try {
            for (Field field : ProgressPeekConfig.class.getDeclaredFields()) {
                var modifiers = field.getModifiers();
                if (Modifier.isStatic(modifiers) || Modifier.isTransient(modifiers) || Modifier.isFinal(modifiers)) {
                    continue;
                }
                String key = field.getName();
                if (field.isAnnotationPresent(ProgressPeekConfig.Key.class)) {
                    key = field.getAnnotation(ProgressPeekConfig.Key.class).value();
                }
                if (field.getType().isEnum()) {
                    config.set(key, ((Enum<?>) field.get(object)).name());
                }
                else {
                    config.set(key, field.get(object));
                }
                if (field.isAnnotationPresent(ProgressPeekConfig.Comment.class)) {
                    config.setComment(key, field.getAnnotation(ProgressPeekConfig.Comment.class).value());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return config;
    }

    private static ProgressPeekConfig fromNightConfig() {
        var config = new ProgressPeekConfig();
        try (var night = makeNightConfig()) {
            night.load();
            for (Field field : ProgressPeekConfig.class.getDeclaredFields()) {
                var modifiers = field.getModifiers();
                if (Modifier.isStatic(modifiers) || Modifier.isTransient(modifiers) || Modifier.isFinal(modifiers)) {
                    continue;
                }
                String key = field.getName();
                if (field.isAnnotationPresent(ProgressPeekConfig.Key.class)) {
                    key = field.getAnnotation(ProgressPeekConfig.Key.class).value();
                }
                if (night.contains(key)) {
                    if (field.getType().isEnum()) {
                        try {
                            Enum<?> enumValue = Enum.valueOf(field.getType().asSubclass(Enum.class), night.get(key));
                            field.set(config, enumValue);
                        } catch (IllegalArgumentException e) {
                            ProgressPeekCore.LOGGER.error("Invalid value {} for enum {}, using default value", night.get(key), field.getType().getName());
                        }
                    }
                    else {
                        field.set(config, night.get(key));
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return config;
    }
}
