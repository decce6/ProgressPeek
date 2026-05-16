package me.decce.progresspeek.mixins;

import me.decce.transformingbase.core.ProgressPeekCore;
import me.decce.transformingbase.core.ProgressStatus;
import net.minecraft.client.gui.screens.LevelLoadingScreen;
import net.minecraft.client.multiplayer.LevelLoadTracker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LevelLoadingScreen.class)
public class LevelLoadingScreenMixin {
    @Shadow
    private LevelLoadTracker loadTracker;
    @Shadow
    private float smoothedProgress;

    //? <=1.21.11 {
    @Inject(method = "render", at = @At("RETURN"))
    //? } else {
    /*@Inject(method = "extractRenderState", at = @At("RETURN"))
    *///? }
    private void progresspeek$updateProgress(CallbackInfo ci) {
        if (this.loadTracker.hasProgress()) {
            ProgressPeekCore.setStatus(ProgressStatus.NORMAL);
            ProgressPeekCore.setValue(this.smoothedProgress);
        }
        else {
            ProgressPeekCore.setStatus(ProgressStatus.INDETERMINATE);
            ProgressPeekCore.setValue(0);
        }
    }

    @Inject(method = "onClose", at = @At("HEAD"))
    private void progresspeek$endProgress(CallbackInfo ci) {
        ProgressPeekCore.transitionToNoProgress(true);
    }
}
