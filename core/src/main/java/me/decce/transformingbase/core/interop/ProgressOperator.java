package me.decce.transformingbase.core.interop;

import me.decce.transformingbase.core.ProgressPeekCore;
import me.decce.transformingbase.core.ProgressStatus;
import me.decce.transformingbase.core.interop.dbus.DBusProgressOperator;
import me.decce.transformingbase.core.interop.win32.Win32ProgressOperator;
import org.lwjgl.system.Platform;

public interface ProgressOperator {
    static ProgressOperator create() {
        if (!ProgressPeekCore.config.enabled) {
            return new NoopProgressOperator();
        }
        if (!"64".equals(System.getProperty("sun.arch.data.model"))) {
            ProgressPeekCore.LOGGER.info("Disabling ProgressPeek as current platform is not 64-bit.");
            return new NoopProgressOperator();
        }
        switch (Platform.get()) {
            case WINDOWS -> {
                if (ProgressPeekCore.config.windowsEnabled) {
                    return new Win32ProgressOperator();
                }
            }
            case LINUX -> {
                if (ProgressPeekCore.config.linuxEnabled) {
                    return new DBusProgressOperator();
                }
            }
            default -> {
                ProgressPeekCore.LOGGER.info("Disabling ProgressPeek as current platform is unsupported: {}", Platform.get().getName());
                return new NoopProgressOperator();
            }
        }
        return new NoopProgressOperator();
    }

    void preInitialize();
    void initialize(long glfwWindow);
    void setStatus(ProgressStatus status);
    void setValue(int percentage);
    void requestAttention();
}
