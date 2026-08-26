package me.decce.progresspeek.mixins;

import me.decce.progresspeek.ProgressPeekMod;
import me.decce.progresspeek.api.ProgressPeekAPI;
import net.minecraft.client.gui.screens.worldselection.WorldOpenFlows;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldOpenFlows.class)
public class WorldOpenFlowsMixin {
    //? >=1.21.1 {
    @Inject(method = "openWorldLoadLevelStem", at = @At("HEAD"))
    private void progresspeek$openWorldLoadLevelStem(CallbackInfo ci) {
        ProgressPeekMod.API.setStatus(ProgressPeekAPI.Status.INDETERMINATE);
    }

    @Inject(method = "openWorldLoadLevelStem", at = @At(value = "INVOKE", target =
            //? >=26.2 {
            /*"Lnet/minecraft/client/gui/Gui;setScreen(Lnet/minecraft/client/gui/screens/Screen;)V"
            *///? } else {
            "Lnet/minecraft/client/Minecraft;setScreen(Lnet/minecraft/client/gui/screens/Screen;)V"
            //? }
    ))
    private void progresspeek$openWorldLoadLevelStem$1(CallbackInfo ci) {
        ProgressPeekMod.API.transitionToNoProgress(false);
    }
    //? }
}
