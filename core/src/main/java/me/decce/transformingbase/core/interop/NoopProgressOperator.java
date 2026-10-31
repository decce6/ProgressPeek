package me.decce.transformingbase.core.interop;

import me.decce.transformingbase.core.ProgressStatus;
import me.decce.transformingbase.core.interop.shared.NativeHandle;

public class NoopProgressOperator implements ProgressOperator {
    @Override
    public void verifySupported() throws UnsupportedProgressOperatorException {

    }

    @Override
    public void preInitialize() {

    }

    @Override
    public void initialize(NativeHandle windowHandle) {

    }

    @Override
    public ProgressStatus getStatus() {
        return ProgressStatus.NONE;
    }

    @Override
    public int getValue() {
        return 0;
    }

    @Override
    public void setStatus(ProgressStatus status) {

    }

    @Override
    public void setValue(int percentage) {

    }

    @Override
    public void requestAttention() {

    }
}
