package me.decce.progresspeek.mixins;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import me.decce.progresspeek.ProgressPeekMod;
import me.decce.progresspeek.api.ProgressPeekAPI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Overlay;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    //? <=26.1 {
    @Inject(method = "setOverlay", at = @At("RETURN"))
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
    //? }

    //? >=1.21.10 {
    @WrapMethod(method = "disconnect(Lnet/minecraft/client/gui/screens/Screen;ZZ)V")
    public void progresspeek$disconnect(Screen screen, boolean keepResourcePacks, boolean stopSound, Operation<Void> original) {
        ProgressPeekMod.API.setStatus(ProgressPeekAPI.Status.INDETERMINATE);
        original.call(screen, keepResourcePacks, stopSound);
        ProgressPeekMod.API.transitionToNoProgress(true);
    }
    //? }
}
