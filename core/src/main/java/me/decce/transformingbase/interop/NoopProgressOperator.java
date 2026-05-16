package me.decce.transformingbase.interop;

import me.decce.transformingbase.core.ProgressStatus;

public class NoopProgressOperator implements ProgressOperator {
    @Override
    public void initialize(long glfwWindow) {

    }

    @Override
    public void setStatus(ProgressStatus status) {

    }

    @Override
    public void setValue(int percentage) {

    }

    @Override
    public void transitionToNoProgress(boolean notify) {

    }
}
