package me.decce.progresspeek.mixins;

import me.decce.progresspeek.ProgressPeekMod;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.screens.Overlay;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class GuiMixin {
    //? >=26.2 {
    /*@Inject(method = "setOverlay", at = @At("RETURN"))
    private void progresspeek$setOverlay(Overlay overlay, CallbackInfo ci) {
        if (overlay == null) {
            ProgressPeekMod.API.transitionToNoProgress(true);
        }
    }

    @Inject(method = "setScreen", at = @At("RETURN"))
    private void progresspeek$setScreen(Screen screen, CallbackInfo ci) {
        if (screen == null) {
            ProgressPeekMod.API.transitionToNoProgress(false);
        }
    }
    *///? }
}
