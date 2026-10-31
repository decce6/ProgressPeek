package me.decce.progresspeek.mixins;

import com.mojang.blaze3d.systems.RenderSystem;
import me.decce.transformingbase.core.ProgressPeekCore;
import me.decce.transformingbase.core.interop.backend.RuntimeBackend;
import me.decce.transformingbase.core.interop.backend.RuntimeBackends;
import net.minecraft.util.TimeSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = RenderSystem.class, remap = false)
public class RenderSystemMixin {
    @Inject(method = "initBackendSystem",
            at = { @At(value = "INVOKE", target = "Lorg/lwjgl/sdl/SDLInit;SDL_Init(I)Z"), @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/GLX;_initGlfw()Ljava/util/function/LongSupplier;") },
            expect = 1, require = 1)
    private static void progresspeek$preBackendInitialize(CallbackInfoReturnable<TimeSource.NanoTimeSource> cir) {
        RuntimeBackends.current().onLoad(RuntimeBackend.LoadStage.PRE_LIBRARY_INITIALIZE);
    }
}
