package me.decce.progresspeek.mixins;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import me.decce.transformingbase.core.ProgressPeekCore;
import me.decce.transformingbase.core.ProgressStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Overlay;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Inject(method = "setOverlay", at = @At("RETURN"))
    private void progresspeek$setOverlay(Overlay overlay, CallbackInfo ci) {
        if (overlay == null) {
            ProgressPeekCore.transitionToNoProgress(true);
        }
    }

    //? >=1.21.1 {
    @WrapMethod(method = "disconnect(Lnet/minecraft/client/gui/screens/Screen;ZZ)V")
    public void progresspeek$disconnect(Screen screen, boolean keepResourcePacks, boolean stopSound, Operation<Void> original) {
        ProgressPeekCore.setStatus(ProgressStatus.INDETERMINATE);
        original.call(screen, keepResourcePacks, stopSound);
        ProgressPeekCore.transitionToNoProgress(true);
    }
    //? }
}
