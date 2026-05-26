package me.decce.progresspeek.mixins;

import com.mojang.blaze3d.platform.DisplayData;
import com.mojang.blaze3d.platform.ScreenManager;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.platform.WindowEventHandler;
import me.decce.transformingbase.core.ProgressPeekCore;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Window.class)
public class WindowMixin {
    @Shadow
    @Final
    //? >=1.21.10 {
    private long handle;
    //? } else {
    /*private long window;
    *///? }

    @Inject(method = "<init>" , at = @At(value = "INVOKE", target = "Lorg/lwjgl/glfw/GLFW;glfwCreateWindow(IILjava/lang/CharSequence;JJ)J"), require = 0)
    private void progresspeek$preInitWindow(CallbackInfo ci) {
        ProgressPeekCore.preInit();
    }

    @Inject(method = "createGlfwWindow", at = @At(value = "INVOKE", target = "Lorg/lwjgl/glfw/GLFW;glfwCreateWindow(IILjava/lang/CharSequence;JJ)J"), require = 0)
    private static void progresspeek$preInitWindow(CallbackInfoReturnable<Long> ci) {
        ProgressPeekCore.preInit();
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void progresspeek$initWindow(CallbackInfo ci) {
        //? >=1.21.10 {
        ProgressPeekCore.init(this.handle);
        //? } else {
        /*ProgressPeekCore.init(this.window);
        *///? }
    }
}
