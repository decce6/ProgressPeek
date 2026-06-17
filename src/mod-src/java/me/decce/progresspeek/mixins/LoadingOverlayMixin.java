package me.decce.progresspeek.mixins;

import me.decce.progresspeek.ProgressPeekMod;
import me.decce.transformingbase.core.ProgressPeekCore;
import me.decce.transformingbase.core.ProgressStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.LoadingOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LoadingOverlay.class)
public class LoadingOverlayMixin {
    @Shadow
    private float currentProgress;

    //? <=1.21.11 {
    @Inject(method = "render", at = @At("RETURN"))
    //? } else {
    /*@Inject(method = "extractRenderState", at = @At("RETURN"))
    *///? }
    private void progresspeek$updateProgress(GuiGraphics guiGraphics, int i, int j, float f, CallbackInfo ci) {
        //noinspection ConstantValue
        if (this == (Object) ProgressPeekMod.getOverlay()) {
            ProgressPeekCore.setStatus(ProgressStatus.NORMAL);
            ProgressPeekCore.setValue(this.currentProgress);
        }
    }
}
