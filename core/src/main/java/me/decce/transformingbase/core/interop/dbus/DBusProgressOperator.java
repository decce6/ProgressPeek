package me.decce.transformingbase.core.interop.dbus;

import me.decce.transformingbase.core.ProgressStatus;
import me.decce.transformingbase.core.interop.ProgressOperator;
import me.decce.transformingbase.core.interop.backend.RuntimeBackends;
import me.decce.transformingbase.core.interop.shared.DesktopFileHelper;
import me.decce.transformingbase.core.interop.shared.NativeHandle;

public class DBusProgressOperator implements ProgressOperator {
    private ProgressStatus status = ProgressStatus.NONE;
    private int value;
    private DBusCommunicationThread thread;
    private NativeHandle windowHandle;

    @Override
    public void verifySupported() throws UnsupportedProgressOperatorException {
        if (LibDBus.LIBDBUS == null) {
            throw new UnsupportedProgressOperatorException("Failed to initialize the libdbus library!");
        }
    }

    @Override
    public void initialize(NativeHandle windowHandle) {
        if (thread == null) {
            thread = new DBusCommunicationThread(DesktopFileHelper.instance().className);
            thread.start();
        }

        if (this.windowHandle != windowHandle) {
            this.windowHandle = windowHandle;
            // The thread might not have been initialized at this point, if on an older version of Forge which does not load ImmediateWindowProvider from mods
            if (thread != null) {
                thread.set(ProgressStatus.NONE, 0, true); // Progress is persisted across launches - reset to 0
            }
        }
    }

    @Override
    public ProgressStatus getStatus() {
        return status;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setStatus(ProgressStatus status) {
        if (thread != null) {
            this.status = status;
            thread.set(status);
        }
    }

    @Override
    public void setValue(int percentage) {
        if (thread != null) {
            this.value = percentage;
            thread.set(percentage);
        }
    }

    @Override
    public void requestAttention() {
        if (this.windowHandle != null) {
             RuntimeBackends.current().requestWindowAttention(this.windowHandle.forLinux().getAsLong());
        }
    }


}
