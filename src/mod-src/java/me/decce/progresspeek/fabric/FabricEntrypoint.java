package me.decce.progresspeek.fabric;

//? if fabric {
import me.decce.progresspeek.ProgressPeekMod;
import net.fabricmc.api.ModInitializer;

public class FabricEntrypoint implements ModInitializer {
    @Override
    public void onInitialize() {
        ProgressPeekMod.init();
    }
}
//?}
