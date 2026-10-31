package me.decce.transformingbase.core.interop.backend;

import me.decce.transformingbase.core.interop.shared.DesktopFileHelper;
import me.decce.transformingbase.core.interop.shared.NativeHandle;
import org.lwjgl.glfw.GLFW;

public class GlfwRuntimeBackend implements RuntimeBackend {
    @Override
    public void onLoad(LoadStage stage) {
        if (stage == LoadStage.PRE_WINDOW_CREATION) {
            DesktopFileHelper.instance().writeDesktopFile();

            GLFW.glfwWindowHintString(GLFW.GLFW_X11_CLASS_NAME, DesktopFileHelper.instance().className);
            GLFW.glfwWindowHintString(GLFW.GLFW_X11_INSTANCE_NAME, DesktopFileHelper.instance().className);
            GLFW.glfwWindowHintString(GLFW.GLFW_WAYLAND_APP_ID, DesktopFileHelper.instance().className);
        }
    }

    @Override
    public double getTime() {
        return GLFW.glfwGetTime();
    }

    @Override
    public void requestWindowAttention(NativeHandle handle) {
        GLFW.glfwRequestWindowAttention(handle.abstractHandle());
    }
}
