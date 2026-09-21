package me.decce.progresspeek.mixins;

import com.mojang.blaze3d.systems.RenderSystem;
import me.decce.transformingbase.core.ProgressPeekCore;
import me.decce.transformingbase.core.interop.sdl.SdlProgressOperator;
import net.minecraft.util.TimeSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = RenderSystem.class, remap = false)
public class RenderSystemMixin {
    @Inject(method = "initBackendSystem", at = @At("HEAD"))
    private static void progresspeek$preSdlInitialize(CallbackInfoReturnable<TimeSource.NanoTimeSource> cir) {
        if (ProgressPeekCore.operator instanceof SdlProgressOperator sdlProgressOperator) {
            sdlProgressOperator.preSdlInitialize();
        }
    }
}
