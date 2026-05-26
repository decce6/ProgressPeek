package me.decce.progresspeek.mixins;

import com.mojang.serialization.Dynamic;
import me.decce.transformingbase.core.ProgressPeekCore;
import me.decce.transformingbase.core.ProgressStatus;
import net.minecraft.client.gui.screens.worldselection.WorldOpenFlows;
import net.minecraft.world.level.storage.LevelStorageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldOpenFlows.class)
public class WorldOpenFlowsMixin {
    //? >=1.21.1 {
    @Inject(method = "openWorldLoadLevelStem", at = @At("HEAD"))
    private void progresspeek$openWorldLoadLevelStem(CallbackInfo ci) {
        ProgressPeekCore.setStatus(ProgressStatus.INDETERMINATE);
    }

    @Inject(method = "openWorldLoadLevelStem", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;setScreen(Lnet/minecraft/client/gui/screens/Screen;)V"))
    private void progresspeek$openWorldLoadLevelStem$1(CallbackInfo ci) {
        ProgressPeekCore.transitionToNoProgress(false);
    }
    //? }
}
