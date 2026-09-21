package me.decce.transformingbase.core.interop;

import me.decce.transformingbase.core.ProgressPeekCore;
import me.decce.transformingbase.core.ProgressStatus;
import me.decce.transformingbase.core.interop.dbus.DBusProgressOperator;
import me.decce.transformingbase.core.interop.sdl.SdlProgressOperator;
import me.decce.transformingbase.core.interop.win32.Win32ProgressOperator;
import org.lwjgl.system.Platform;

import java.util.Optional;

public interface ProgressOperator {
    static ProgressOperator create() {
        if (!ProgressPeekCore.config.enabled) {
            return new NoopProgressOperator();
        }
        if (!"64".equals(System.getProperty("sun.arch.data.model"))) {
            ProgressPeekCore.LOGGER.info("Disabling ProgressPeek as current platform is not 64-bit.");
            return new NoopProgressOperator();
        }
        var maybeSdlOperator = tryCreateSdlOperator();
        if (maybeSdlOperator.isPresent()) {
            return maybeSdlOperator.get();
        }
        ProgressOperator operator = createByPlatform();
        try {
            operator.verifySupported();
            return operator;
        } catch (UnsupportedProgressOperatorException e) {
            ProgressPeekCore.LOGGER.warn("Failed to initialize {}, ProgressPeek will be disabled", operator.getClass().getSimpleName(), e);
        }
        return new NoopProgressOperator();
    }

    private static Optional<ProgressOperator> tryCreateSdlOperator() {
        var candidate = new SdlProgressOperator();
        try {
            candidate.verifySupported();
            return Optional.of(candidate);
        } catch (UnsupportedProgressOperatorException e) {
            return Optional.empty();
        }
    }

    private static ProgressOperator createByPlatform() {
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
        }
        ProgressPeekCore.LOGGER.info("Disabling ProgressPeek as current platform is unsupported: {}", Platform.get().getName());
        return new NoopProgressOperator();
    }

    void verifySupported() throws UnsupportedProgressOperatorException;
    void preInitialize();
    void initialize(long windowHandle);
    ProgressStatus getStatus();
    int getValue();
    void setStatus(ProgressStatus status);
    void setValue(int percentage);
    void requestAttention();

    class UnsupportedProgressOperatorException extends Exception {
        public UnsupportedProgressOperatorException() {
        }

        public UnsupportedProgressOperatorException(String message) {
            super(message);
        }

        public UnsupportedProgressOperatorException(Throwable cause) {
            super(cause);
        }
    }
}
