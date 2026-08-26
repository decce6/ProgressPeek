package me.decce.progresspeek;

import me.decce.progresspeek.api.ProgressPeekAPI;
import me.decce.transformingbase.constants.Constants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Overlay;

public class ProgressPeekMod {
    public static ProgressPeekAPI API = ProgressPeekAPI.getInstance(Constants.MOD_ID);

    public static void init() {

    }

    public static Overlay getOverlay() {
        //? >=26.2 {
        /*return Minecraft.getInstance().gui.overlay();
        *///? } else {
        return Minecraft.getInstance().getOverlay();
        //? }
    }
}
