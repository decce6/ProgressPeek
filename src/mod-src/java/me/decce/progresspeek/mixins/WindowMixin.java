package me.decce.progresspeek.mixins;

import com.mojang.blaze3d.platform.Window;
import me.decce.transformingbase.core.ProgressPeekCore;
import me.decce.transformingbase.core.interop.backend.RuntimeBackend;
import me.decce.transformingbase.core.interop.backend.RuntimeBackends;
import me.decce.transformingbase.core.interop.shared.NativeGlfwHandle;
import me.decce.transformingbase.core.interop.shared.NativeSdlHandle;
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

    //? >=26.3 {
    /*@Inject(method = "<init>(Lcom/mojang/blaze3d/platform/WindowEventHandler;Lcom/mojang/blaze3d/platform/DisplayData;Ljava/lang/String;ZLjava/lang/String;Lcom/mojang/blaze3d/platform/MonitorManager;Lcom/mojang/renderpearl/api/device/GpuBackend;I)V", at = @At("TAIL"))
    *///? } else {
    @Inject(method = "<init>" , at = @At(value = "INVOKE", target = "Lorg/lwjgl/glfw/GLFW;glfwCreateWindow(IILjava/lang/CharSequence;JJ)J"), require = 0, expect = 0)
    //? }
    private void progresspeek$preInitWindow(CallbackInfo ci) {
        RuntimeBackends.current().onLoad(RuntimeBackend.LoadStage.PRE_WINDOW_CREATION);
    }

    @Inject(method = "createGlfwWindow", at = @At(value = "INVOKE", target = "Lorg/lwjgl/glfw/GLFW;glfwCreateWindow(IILjava/lang/CharSequence;JJ)J"), require = 0, expect = 0)
    private static void progresspeek$preInitWindow(CallbackInfoReturnable<Long> ci) {
        RuntimeBackends.current().onLoad(RuntimeBackend.LoadStage.PRE_WINDOW_CREATION);
    }

    //? >=26.3 {
    /*@Inject(method = "<init>(Lcom/mojang/blaze3d/platform/WindowEventHandler;Lcom/mojang/blaze3d/platform/DisplayData;Ljava/lang/String;ZLjava/lang/String;Lcom/mojang/blaze3d/platform/MonitorManager;Lcom/mojang/renderpearl/api/device/GpuBackend;I)V", at = @At("TAIL"))
    *///? } else {
    @Inject(method = "<init>", at = @At("TAIL"))
    //? }
    private void progresspeek$initWindow(CallbackInfo ci) {
        //? >=26.3 {
        /*ProgressPeekCore.init(NativeSdlHandle.fromSdlHandle(this.handle));
        *///? } else {
        //? >=1.21.10 {
        var glfwHandle = this.handle;
        //? } else {
        /*var glfwHandle = this.window;
        *///? }
        ProgressPeekCore.init(NativeGlfwHandle.fromGlfwHandle(glfwHandle));
        //? }
    }
}
