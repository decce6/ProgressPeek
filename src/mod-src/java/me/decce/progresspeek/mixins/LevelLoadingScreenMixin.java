package me.decce.progresspeek.mixins;

import me.decce.transformingbase.core.ProgressPeekCore;
import me.decce.transformingbase.core.ProgressStatus;
import net.minecraft.client.gui.screens.LevelLoadingScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? >=1.21.9 {
import net.minecraft.client.multiplayer.LevelLoadTracker;
//? } else {
/*import net.minecraft.server.level.progress.StoringChunkProgressListener;
import org.spongepowered.asm.mixin.Final;
*///? }

@Mixin(LevelLoadingScreen.class)
public class LevelLoadingScreenMixin {
    //? >=1.21.9 {
    @Shadow
    private LevelLoadTracker loadTracker;
    @Shadow
    private float smoothedProgress;
    //? } else {
    /*@Shadow
    @Final
    private StoringChunkProgressListener progressListener;
    *///? }

    //? <=1.21.11 {
    @Inject(method = "render", at = @At("RETURN"))
    //? } else {
    /*@Inject(method = "extractRenderState", at = @At("RETURN"))
    *///? }
    private void progresspeek$updateProgress(CallbackInfo ci) {
        //? >=1.21.9 {
        if (this.loadTracker.hasProgress()) {
            ProgressPeekCore.setStatus(ProgressStatus.NORMAL);
            ProgressPeekCore.setValue(this.smoothedProgress);
        }
        else {
            ProgressPeekCore.setStatus(ProgressStatus.INDETERMINATE);
            ProgressPeekCore.setValue(0);
        }
        //? } else {
        /*ProgressPeekCore.setStatus(ProgressStatus.NORMAL);
        ProgressPeekCore.setValue(progressListener.getProgress());
        *///? }
    }

    //? >=1.21.9 {
    @Inject(method = "onClose", at = @At("HEAD"))
    //? } else {
    /*@Inject(method = "removed", at = @At("HEAD"))
    *///? }
    private void progresspeek$endProgress(CallbackInfo ci) {
        ProgressPeekCore.transitionToNoProgress(true);
    }
}
