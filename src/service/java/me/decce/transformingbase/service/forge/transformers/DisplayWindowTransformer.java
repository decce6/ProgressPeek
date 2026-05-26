package me.decce.transformingbase.service.forge.transformers;

//? forge {
/*import me.decce.transformingbase.core.ProgressPeekCore;
import net.lenni0451.classtransform.annotations.CInline;
import net.lenni0451.classtransform.annotations.CShadow;
import net.lenni0451.classtransform.annotations.CTarget;
import net.lenni0451.classtransform.annotations.CTransformer;
import net.lenni0451.classtransform.annotations.injection.CInject;
import net.minecraftforge.fml.earlydisplay.DisplayWindow;

@CTransformer(DisplayWindow.class)
public class DisplayWindowTransformer {
    @CShadow
    private long window;

    @CInline
    @CInject(method = "initWindow", target = @CTarget(value = "INVOKE", target = "Lorg/lwjgl/glfw/GLFW;glfwCreateWindow(IILjava/lang/CharSequence;JJ)J", shift = CTarget.Shift.BEFORE))
    private void progresspeek$preInit() {
        ProgressPeekCore.preInit();
    }

    // Inject into periodicTick instead of initWindow because, if running on an older version of Forge, our
    // ImmediateWindowProvider wouldn't run, meaning our transformations would happen *after* initWindow finishes.
    @CInline
    @CInject(method = "periodicTick", target = @CTarget("RETURN"))
    private void progresspeek$initialize() {
        ProgressPeekCore.init(window);
    }
}
*///? }
