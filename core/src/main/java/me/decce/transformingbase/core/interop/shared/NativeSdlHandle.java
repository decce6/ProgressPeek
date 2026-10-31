package me.decce.transformingbase.core.interop.shared;

import org.lwjgl.sdl.SDLProperties;
import org.lwjgl.sdl.SDLVideo;

import java.util.function.LongSupplier;

public record NativeSdlHandle(LongSupplier windows, LongSupplier macOS, LongSupplier linux) implements NativeHandle {
    public static NativeSdlHandle fromSdlHandle(long sdlHandle) {
        int properties = SDLVideo.SDL_GetWindowProperties(sdlHandle);
        var windowsHandle = SDLProperties.SDL_GetPointerProperty(properties, SDLVideo.SDL_PROP_WINDOW_WIN32_HWND_POINTER, 0L);
        var macOSHandle = SDLProperties.SDL_GetPointerProperty(properties, SDLVideo.SDL_PROP_WINDOW_COCOA_WINDOW_POINTER, 0L);
        var x11Handle = SDLProperties.SDL_GetPointerProperty(properties, SDLVideo.SDL_PROP_WINDOW_X11_DISPLAY_POINTER, 0L);
        var waylandHandle = SDLProperties.SDL_GetPointerProperty(properties, SDLVideo.SDL_PROP_WINDOW_WAYLAND_DISPLAY_POINTER, 0L);
        return new NativeSdlHandle(() -> windowsHandle, () -> macOSHandle, () -> x11Handle == 0L ? waylandHandle : x11Handle);
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
