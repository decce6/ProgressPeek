package me.decce.progresspeek.mixins;

import me.decce.transformingbase.core.ProgressPeekCore;
import me.decce.transformingbase.core.ProgressStatus;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreateWorldScreen.class)
public class CreateWorldScreenMixin {
    @Inject(method = "queueLoadScreen", at = @At("HEAD"))
    private static void progresspeek$queueLoadScreen(CallbackInfo ci) {
        ProgressPeekCore.setStatus(ProgressStatus.INDETERMINATE);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void progresspeek$endProgress(CallbackInfo ci) {
        ProgressPeekCore.transitionToNoProgress(false);
    }
}
