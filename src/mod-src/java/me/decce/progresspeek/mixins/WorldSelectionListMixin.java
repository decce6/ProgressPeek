package me.decce.progresspeek.mixins;

import me.decce.progresspeek.ProgressPeekMod;
import me.decce.progresspeek.api.ProgressPeekAPI;
import net.minecraft.client.gui.screens.worldselection.WorldSelectionList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldSelectionList.WorldListEntry.class)
public class WorldSelectionListMixin {
    //? <=1.20.1 {
    /*@Inject(method = "loadWorld", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/worldselection/WorldSelectionList$WorldListEntry;queueLoadScreen()V"))
    private void progresspeek$loadWorld(CallbackInfo ci) {
        ProgressPeekMod.API.setStatus(ProgressPeekAPI.Status.INDETERMINATE);
    }

    @Inject(method = "loadWorld", at = @At("RETURN"))
    private void progresspeek$loadWorld$return(CallbackInfo ci) {
        ProgressPeekMod.API.transitionToNoProgress(false);
    }
    *///? }
}
