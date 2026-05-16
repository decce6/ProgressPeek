package me.decce.transformingbase.core.interop.win32;

import org.lwjgl.system.APIUtil;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.SharedLibrary;

import static org.lwjgl.system.JNI.callPPI;
import static org.lwjgl.system.JNI.callPPPPI;

public class Explorerframe {
    private static final SharedLibrary EXPLORERFRAME = APIUtil.apiCreateLibrary("Explorerframe");

    @NativeType("INT")
    public static int SetProgressState(@NativeType("ITaskbarList3") long self, @NativeType("HWND") long hwnd, @NativeType("INT") int tbpFlags, long functionAddress) {
        return callPPI(self, hwnd, tbpFlags, functionAddress);
    }

    @NativeType("INT")
    public static int SetProgressValue(@NativeType("ITaskbarList3") long self, @NativeType("HWND") long hwnd, @NativeType("LONGLONG") long ullCompleted, @NativeType("LONGLONG") long ullTotal, long functionAddress) {
        return callPPPPI(self, hwnd, ullCompleted, ullTotal, functionAddress);
    }
}