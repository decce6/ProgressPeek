package me.decce.progresspeek.mixins;

import me.decce.progresspeek.ProgressPeekMod;
import me.decce.progresspeek.api.ProgressPeekAPI;
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
            ProgressPeekMod.API.setStatus(ProgressPeekAPI.Status.NORMAL);
            ProgressPeekMod.API.setValue(this.smoothedProgress);
        }
        else {
            ProgressPeekMod.API.setStatus(ProgressPeekAPI.Status.INDETERMINATE);
            ProgressPeekMod.API.setValue(0);
        }
        //? } else {
        /*ProgressPeekMod.API.setStatus(ProgressPeekAPI.Status.NORMAL);
        ProgressPeekMod.API.setValue(progressListener.getProgress());
        *///? }
    }

    //? >=1.21.9 {
    @Inject(method = "onClose", at = @At("HEAD"))
    //? } else {
    /*@Inject(method = "removed", at = @At("HEAD"))
    *///? }
    private void progresspeek$endProgress(CallbackInfo ci) {
        ProgressPeekMod.API.transitionToNoProgress(true);
    }
}
