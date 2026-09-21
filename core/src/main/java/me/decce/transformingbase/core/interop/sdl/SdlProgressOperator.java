package me.decce.transformingbase.core.interop.sdl;

import me.decce.transformingbase.core.ProgressPeekCore;
import me.decce.transformingbase.core.ProgressStatus;
import me.decce.transformingbase.core.interop.ProgressOperator;
import me.decce.transformingbase.core.interop.shared.DesktopFileHelper;
import org.lwjgl.sdl.SDLHints;
import org.lwjgl.sdl.SDLVideo;

public class SdlProgressOperator implements ProgressOperator {
    private final DesktopFileHelper desktopFileHelper = new DesktopFileHelper();
    private long windowHandle;
    private ProgressStatus status = ProgressStatus.NONE;
    private int value;
    private boolean desktopFileGenerated;

    @Override
    public void verifySupported() throws UnsupportedProgressOperatorException {
        try {
            SDLVideo.class.getName();
        } catch (Throwable throwable) {
            throw new UnsupportedProgressOperatorException(throwable);
        }
    }

    public void preSdlInitialize() {
        if (!desktopFileGenerated) {
            desktopFileGenerated = true;
            desktopFileHelper.writeDesktopFile();
        }
        SDLHints.SDL_SetHint(SDLHints.SDL_HINT_APP_ID, desktopFileHelper.className);
    }

    @Override
    public void preInitialize() {

    }

    @Override
    public void initialize(long windowHandle) {
        this.windowHandle = windowHandle;
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
        if (status != this.status) {
            this.status = status;
            var res = SDLVideo.SDL_SetWindowProgressState(windowHandle, switch (status) {
                case NONE -> SDLVideo.SDL_PROGRESS_STATE_NONE;
                case NORMAL -> SDLVideo.SDL_PROGRESS_STATE_NORMAL;
                case INDETERMINATE -> SDLVideo.SDL_PROGRESS_STATE_INDETERMINATE;
                case PAUSED -> SDLVideo.SDL_PROGRESS_STATE_PAUSED;
                case ERROR -> SDLVideo.SDL_PROGRESS_STATE_ERROR;
            });
            ProgressPeekCore.LOGGER.info("state {}", res);
        }
    }

    @Override
    public void setValue(int value) {
        if (value != this.value) {
            this.value = value;
            var res = SDLVideo.SDL_SetWindowProgressValue(windowHandle, value / 100.0f);
            ProgressPeekCore.LOGGER.info("value {}", res);
        }
    }

    @Override
    public void requestAttention() {
        SDLVideo.SDL_FlashWindow(windowHandle, SDLVideo.SDL_FLASH_UNTIL_FOCUSED);
    }
}
