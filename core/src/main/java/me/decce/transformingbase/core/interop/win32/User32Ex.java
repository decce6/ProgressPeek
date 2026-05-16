package me.decce.transformingbase.core.interop.win32;

import org.lwjgl.system.APIUtil;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.SharedLibrary;
import org.lwjgl.system.windows.User32;

import static org.lwjgl.system.Checks.CHECKS;
import static org.lwjgl.system.Checks.check;
import static org.lwjgl.system.JNI.callPI;

public class User32Ex {
    private static final SharedLibrary USER32 = APIUtil.apiCreateLibrary("user32");

    public static final long FlashWindow = APIUtil.apiGetFunctionAddress(USER32, "FlashWindow");

    @NativeType("BOOL")
    public static boolean FlashWindow(@NativeType("HWND") long hWnd, @NativeType("BOOL") boolean bInvert) {
        if (CHECKS) {
            check(hWnd);
        }
        return callPI(hWnd, bInvert ? 1 : 0, FlashWindow) != 0;
    }
}
