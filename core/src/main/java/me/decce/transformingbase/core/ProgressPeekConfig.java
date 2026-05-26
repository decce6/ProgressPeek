package me.decce.transformingbase.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@SuppressWarnings({"FieldMayBeFinal", "FieldCanBeLocal", "unused"})
public class ProgressPeekConfig {
    @Comment("Specifies whether to enable the mod")
    public boolean enabled = true;
    @Comment("Specifies the app id and window class name")
    @Key("linux.app_id")
    public String linuxAppId = "";
    @Comment("Specifies the app name when generating the desktop file. Note that this changes the Name property in the desktop file, not the name of the file itself.")
    @Key("linux.desktop_file_name")
    public String linuxAppName = "";
    @Comment("When enabled, explicitly flush dbus connection when sending message")
    @Key("linux.explicit_flush")
    public boolean linuxExplicitFlush = false;

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Comment {
        String value();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Key {
        String value();
    }
}
