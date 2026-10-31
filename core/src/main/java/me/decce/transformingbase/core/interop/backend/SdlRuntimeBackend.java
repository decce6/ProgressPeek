package me.decce.transformingbase.core.interop.backend;

import me.decce.transformingbase.core.interop.shared.DesktopFileHelper;
import me.decce.transformingbase.core.interop.shared.NativeHandle;
import org.lwjgl.sdl.SDLHints;
import org.lwjgl.sdl.SDLTimer;
import org.lwjgl.sdl.SDLVideo;
import org.lwjgl.system.Platform;

import java.util.concurrent.TimeUnit;

public class SdlRuntimeBackend implements RuntimeBackend {
    private static final long NANOS_IN_A_SECOND = TimeUnit.SECONDS.toNanos(1);

    @Override
    public void onLoad(LoadStage stage) {
        if (stage == LoadStage.PRE_LIBRARY_INITIALIZATION) {
            if (Platform.get() == Platform.LINUX) {
                DesktopFileHelper.instance().writeDesktopFile();
                SDLHints.SDL_SetHint(SDLHints.SDL_HINT_APP_ID, DesktopFileHelper.instance().className);
            }
        }
    }

    @Override
    public double getTime() {
        return (double) SDLTimer.SDL_GetTicksNS() / NANOS_IN_A_SECOND;
    }

    @Override
    public void requestWindowAttention(NativeHandle handle) {
        SDLVideo.SDL_FlashWindow(handle.abstractHandle(), SDLVideo.SDL_FLASH_UNTIL_FOCUSED);
    }
}
