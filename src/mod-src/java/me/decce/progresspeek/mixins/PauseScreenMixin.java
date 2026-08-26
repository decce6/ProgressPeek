package me.decce.progresspeek.mixins;

import me.decce.progresspeek.ProgressPeekMod;
import me.decce.progresspeek.api.ProgressPeekAPI;
import net.minecraft.client.gui.screens.PauseScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PauseScreen.class)
public class PauseScreenMixin {
    //? <=1.21.1 {
    /*@Inject(method = "onDisconnect", at = @At("HEAD"))
    private void progresspeek$onDisconnect$head(CallbackInfo ci) {
        ProgressPeekMod.API.setStatus(ProgressPeekAPI.Status.INDETERMINATE);
    }

    @Inject(method = "onDisconnect", at = @At("RETURN"))
    private void progresspeek$onDisconnect$return(CallbackInfo ci) {
        ProgressPeekMod.API.transitionToNoProgress(false);
    }
    *///? }
}
