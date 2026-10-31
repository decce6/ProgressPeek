package me.decce.transformingbase.core.interop.backend;

import org.lwjgl.sdl.SDLInit;

public class RuntimeBackends {
    private static RuntimeBackend instance;

    public static RuntimeBackend current() {
        if (instance == null) {
            synchronized (RuntimeBackend.class) {
                try {
                    if (SDLInit.SDL_GetAppMetadataProperty(SDLInit.SDL_PROP_APP_METADATA_IDENTIFIER_STRING) != null) {
                        return (instance = new SdlRuntimeBackend());
                    }
                }
                catch (Throwable ignored) {}
                return (instance = new GlfwRuntimeBackend());
            }
        }
        return instance;
    }
}
