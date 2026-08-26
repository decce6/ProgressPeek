package me.decce.transformingbase.service;

import me.decce.transformingbase.constants.Constants;
import me.decce.transformingbase.instrumentation.AgentLoader;
import me.decce.transformingbase.transform.TransformationHelper;
import me.decce.transformingbase.transform.TransformerDefinition;
import net.lenni0451.reflect.Agents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFWErrorCallbackI;

import java.lang.instrument.Instrumentation;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.util.Objects;

//? neoforge {
/*import me.decce.transformingbase.service.neoforge.transformers.DisplayWindowTransformer;
//? >=1.21.11 {
import me.decce.transformingbase.service.neoforge.transformers.ProgressBarsElementTransformer;
//? } else {
/^import me.decce.transformingbase.service.neoforge.transformers.RenderElementTransformer;
^///? }
*///? }
//? forge {
/*import me.decce.transformingbase.service.forge.transformers.RenderElementTransformer;
import me.decce.transformingbase.service.forge.transformers.DisplayWindowTransformer;
*///? }

public class Bootstrapper {
    public static final Logger LOGGER = LogManager.getLogger(Constants.MOD_NAME);
    private static boolean bootstrapped;

    public static void bootstrap() {
        if (bootstrapped) {
            return;
        }
        bootstrapped = true;

        var classLoaderHandler = new ClassLoaderHandlerImpl(GLFWErrorCallbackI.class.getClassLoader(), Bootstrapper.class.getClassLoader());
        //? !fabric {
        /*classLoaderHandler.loadCoreClasses(Bootstrapper.class);
        classLoaderHandler.removeModClassesFromServiceLayer();
        *///? }

        var helper = new TransformationHelper(classLoaderHandler.targetClassLoader, classLoaderHandler.modClassLoader);

        //? !fabric {
        /*helper.setup(getInstrumentation(), true, false
                //? if neoforge {
                /^, new TransformerDefinition("net.neoforged.fml.earlydisplay.DisplayWindow", DisplayWindowTransformer.class)
                //? >=1.21.11 {
                , new TransformerDefinition("net.neoforged.fml.earlydisplay.render.elements.ProgressBarsElement", ProgressBarsElementTransformer.class)
                //? } else {
                /^¹, new TransformerDefinition("net.neoforged.fml.earlydisplay.RenderElement", RenderElementTransformer.class)
                ¹^///? }
                ^///? }
                //? if forge {
                /^, new TransformerDefinition("net.minecraftforge.fml.earlydisplay.DisplayWindow", DisplayWindowTransformer.class)
                , new TransformerDefinition("net.minecraftforge.fml.earlydisplay.RenderElement", RenderElementTransformer.class)
                ^///? }
        );
        *///? }

        classLoaderHandler.close();

        PostBootstrapper.bootstrap();
    }

    private static Instrumentation getInstrumentation() {
        try {
            var codeSource = Objects.requireNonNull(Bootstrapper.class.getProtectionDomain().getCodeSource());
            var path = getAgentPath(codeSource);
            if (path != null) {
                try {
                    return AgentLoader.load(path);
                }
                catch (Throwable throwable) {
                    LOGGER.debug("Could not load instrumentation with primary method");
                    return getInstrumentationWithReflect(throwable);
                }
            }
            else {
                LOGGER.debug("Could not find mod jar, using fallback method for instrumentation");
                return getInstrumentationWithReflect(null);
            }
        }
        catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    private static Instrumentation getInstrumentationWithReflect(Throwable suppressed) {
        try {
            return Agents.getInstrumentation();
        } catch (Throwable throwable) {
            if (suppressed != null) {
                throwable.addSuppressed(suppressed);
            }
            throw new RuntimeException(throwable);
        }
    }

    private static String getAgentPath(CodeSource codeSource) {
        var location = codeSource.getLocation();
        try {
            return Paths.get(location.toURI()).toFile().getAbsolutePath();
        } catch (Throwable throwable) {
            return getParsedAgentPath(codeSource);
        }
    }

    private static String getParsedAgentPath(CodeSource codeSource) {
        var location = codeSource.getLocation();
        if ("union".equals(location.getProtocol())) {
            try {
                var path = location.getPath();
                if (path.contains("%")) {
                    String parsed = path.substring(0, path.indexOf('%'));
                    if (Files.exists(Paths.get(parsed))) {
                        return parsed;
                    }
                }
            }
            catch (Throwable ignored) {}
        }
        return null;
    }
}
