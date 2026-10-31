package me.decce.transformingbase.core.interop.shared;

import org.lwjgl.glfw.GLFWNativeCocoa;
import org.lwjgl.glfw.GLFWNativeWayland;
import org.lwjgl.glfw.GLFWNativeWin32;
import org.lwjgl.glfw.GLFWNativeX11;

import java.util.function.LongFunction;
import java.util.function.LongSupplier;

public record NativeGlfwHandle(LongSupplier windows, LongSupplier macOS, LongSupplier linux) implements NativeHandle {
    public static NativeGlfwHandle fromGlfwHandle(long glfwHandle) {
        LongSupplier windowsHandle = () -> maybeGet(GLFWNativeWin32::glfwGetWin32Window, glfwHandle);
        LongSupplier macOSHandle = () -> maybeGet(GLFWNativeCocoa::glfwGetCocoaWindow, glfwHandle);
        LongSupplier linuxHandle = () -> {
            if (GLFW.glfwGetPlatform() == GLFW.GLFW_PLATFORM_X11) {
                return GLFWNativeX11.glfwGetX11Window(glfwHandle);
            }
            else {
                return GLFWNativeWayland.glfwGetWaylandWindow(glfwHandle);
            }
        };
        return new NativeGlfwHandle(windowsHandle, macOSHandle, linuxHandle);
    }

    private static long maybeGet(LongFunction<Long> consumer, long arg) {
        try {
            return consumer.apply(arg);
        }
        catch (Throwable ignored) {
            return 0L;
        }
    }

    @Override
    public LongSupplier forWindows() {
        return windows;
    }

    @Override
    public LongSupplier forMacOS() {
        return macOS;
    }

    @Override
    public LongSupplier forLinux() {
        return linux;
    }
}
