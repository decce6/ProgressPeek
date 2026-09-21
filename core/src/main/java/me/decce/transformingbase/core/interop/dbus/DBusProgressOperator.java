package me.decce.transformingbase.core.interop.dbus;

import me.decce.transformingbase.core.ProgressStatus;
import me.decce.transformingbase.core.interop.ProgressOperator;
import me.decce.transformingbase.core.interop.shared.DesktopFileHelper;
import org.lwjgl.glfw.GLFW;

public class DBusProgressOperator implements ProgressOperator {
    private final DesktopFileHelper desktopFileHelper = new DesktopFileHelper();
    private ProgressStatus status = ProgressStatus.NONE;
    private int value;
    private DBusCommunicationThread thread;
    private long glfwWindow;
    private boolean desktopFileGenerated;

    @Override
    public void verifySupported() throws UnsupportedProgressOperatorException {
        if (LibDBus.LIBDBUS == null) {
            throw new UnsupportedProgressOperatorException("Failed to initialize the libdbus library!");
        }
    }

    @Override
    public void preInitialize() {
        if (!desktopFileGenerated) {
            desktopFileGenerated = true;
            desktopFileHelper.writeDesktopFile();
        }

        GLFW.glfwWindowHintString(GLFW.GLFW_X11_CLASS_NAME, desktopFileHelper.className);
        GLFW.glfwWindowHintString(GLFW.GLFW_X11_INSTANCE_NAME, desktopFileHelper.className);
        GLFW.glfwWindowHintString(GLFW.GLFW_WAYLAND_APP_ID, desktopFileHelper.className);

        thread = new DBusCommunicationThread(desktopFileHelper.className);
        thread.start();
    }

    @Override
    public void initialize(long windowHandle) {
        if (!desktopFileGenerated) {
            preInitialize();
        }
        if (this.glfwWindow != windowHandle) {
            this.glfwWindow = windowHandle;
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
        if (this.glfwWindow != 0L) {
            // TODO: Ideally we should not depend on GLFW here
            GLFW.glfwRequestWindowAttention(this.glfwWindow);
        }
    }


}
