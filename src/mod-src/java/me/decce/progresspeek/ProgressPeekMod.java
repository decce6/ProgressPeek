package me.decce.progresspeek;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Overlay;

public class ProgressPeekMod {
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
