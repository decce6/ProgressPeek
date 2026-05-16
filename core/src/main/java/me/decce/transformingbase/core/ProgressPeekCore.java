package me.decce.transformingbase.core;

import me.decce.transformingbase.interop.ProgressOperator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProgressPeekCore {
    public static final Logger LOGGER = LogManager.getLogger();
    private static final ProgressOperator operator = ProgressOperator.create();
    public static ProgressPeekConfig config;

    public static void init(long glfwWindow) {
        operator.initialize(glfwWindow);
    }

    //TODO thread safety
    public static void setStatus(ProgressStatus status) {
        operator.setStatus(status);
    }

    public static void setValue(int value) {
        operator.setValue(value);
    }

    public static void setValue(float value) {
        setValue((int) (value * 100));
    }

    public static void transitionToNoProgress(boolean notify) {
        operator.transitionToNoProgress(notify);
    }
}
