package me.decce.transformingbase.service.neoforge.transformers;

//? neoforge {
/*import me.decce.transformingbase.core.ProgressPeekCore;
import me.decce.transformingbase.core.interop.backend.RuntimeBackend;
import me.decce.transformingbase.core.interop.backend.RuntimeBackends;
import me.decce.transformingbase.core.interop.shared.NativeGlfwHandle;
import me.decce.transformingbase.core.interop.shared.NativeSdlHandle;
import net.lenni0451.classtransform.annotations.CShadow;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.neoforged.fml.earlydisplay.DisplayWindow;
import org.spongepowered.asm.mixin.injection.At;

@CTransformer(DisplayWindow.class)
public class DisplayWindowTransformer {
    @CShadow
    private long window;

    @CInject(method = "initWindow",
            target = { @CTarget(value = "INVOKE", target = "Lorg/lwjgl/sdl/SDLInit;SDL_Init(I)Z", optional = true), @CTarget(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/GLX;_initGlfw()Z", optional = true) })
    private void progresspeek$preLibraryInitialize() {
        RuntimeBackends.current().onLoad(RuntimeBackend.LoadStage.PRE_LIBRARY_INITIALIZE);
    }

    @CInject(method = "initWindow", target = @CTarget(value = "INVOKE", target = "Lorg/lwjgl/glfw/GLFW;glfwCreateWindow(IILjava/lang/CharSequence;JJ)J", shift = CTarget.Shift.BEFORE, optional = true))
    private void progresspeek$preWindowCreation$glfw() {
        RuntimeBackends.current().onLoad(RuntimeBackend.LoadStage.PRE_WINDOW_CREATION);
    }

    @CInject(method = "initWindow", target = @CTarget(value = "INVOKE", target = "Lorg/lwjgl/sdl/SDLInit;SDL_Init(I)Z", shift = CTarget.Shift.BEFORE, optional = true))
    private void progresspeek$preWindowCreation$sdl() {
        RuntimeBackends.current().onLoad(RuntimeBackend.LoadStage.PRE_WINDOW_CREATION);
    }

    @CInject(method = "initWindow", target = @CTarget("RETURN"))
    private void progresspeek$initialize() {
        //? >=26.3 {
        /^ProgressPeekCore.init(NativeSdlHandle.fromSdlHandle(window));
        ^///? } else {
        ProgressPeekCore.init(NativeGlfwHandle.fromGlfwHandle(window));
        //? }
    }
}
*///? }
