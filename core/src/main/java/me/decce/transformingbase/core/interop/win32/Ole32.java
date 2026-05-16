/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package me.decce.transformingbase.core.interop.win32;

import org.lwjgl.PointerBuffer;
import org.lwjgl.system.*;

import java.nio.ByteBuffer;

import static org.lwjgl.system.APIUtil.apiGetFunctionAddress;
import static org.lwjgl.system.Checks.CHECKS;
import static org.lwjgl.system.Checks.checkNT2;
import static org.lwjgl.system.JNI.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Ole32 {

    private static final SharedLibrary OLE32 = APIUtil.apiCreateLibrary("ole32");

    /** Contains the function pointers loaded from the ole32 {@link SharedLibrary}. */
    public static final class Functions {

        private Functions() {}

        /** Function address. */
        public static final long
                CoInitialize     = apiGetFunctionAddress(OLE32, "CoInitialize"),
                CoCreateInstance = apiGetFunctionAddress(OLE32, "CoCreateInstance"),
                IIDFromString    = apiGetFunctionAddress(OLE32, "IIDFromString"),
                CLSIDFromString  = apiGetFunctionAddress(OLE32, "CLSIDFromString");

    }

    /** Returns the ole32 {@link SharedLibrary}. */
    public static SharedLibrary getLibrary() {
        return OLE32;
    }

    protected Ole32() {
        throw new UnsupportedOperationException();
    }

    // --- [ CoInitialize ] ---

    @NativeType("HANDLE")
    public static long CoInitialize(@NativeType("LPVOID") long pvReserved) {
        long __functionAddress = Functions.CoInitialize;
        return callPP(pvReserved, __functionAddress);
    }

    // --- [ CoCreateInstance ] ---

    public static int nCoCreateInstance(long rclsid, long pUnkOuter, int dwClsContext, long riid, long ppv) {
        long __functionAddress = Functions.CoCreateInstance;
        return callPPPPI(rclsid, pUnkOuter, dwClsContext, riid, ppv, __functionAddress);
    }

    @NativeType("INT")
    public static int CoCreateInstance(@NativeType("REFCLSID") GUID rclsid, @NativeType("LPUNKNOWN") PointerBuffer pUnkOuter, @NativeType("DWORD") int dwClsContext, @NativeType("REFIID") GUID riid, @NativeType("LPVOID *") PointerBuffer ppv) {
        return nCoCreateInstance(rclsid.address(), memAddressSafe(pUnkOuter), dwClsContext, riid.address(), memAddress(ppv));
    }

    // --- [ IIDFromString ] ---

    public static int nIIDFromString(long lpsz, long lpiid) {
        long __functionAddress = Functions.IIDFromString;
        return callPPI(lpsz, lpiid, __functionAddress);
    }

    @NativeType("INT")
    public static int IIDFromString(@NativeType("LPCWSTR") ByteBuffer lpsz, @NativeType("REFIID") GUID lpiid) {
        if (CHECKS) {
            checkNT2(lpsz);
        }
        return nIIDFromString(memAddress(lpsz), lpiid.address());
    }

    @NativeType("INT")
    public static int IIDFromString(@NativeType("LPCWSTR") CharSequence lpsz, @NativeType("REFIID") GUID lpiid) {
        MemoryStack stack = stackGet(); int stackPointer = stack.getPointer();
        try {
            stack.nUTF16(lpsz, true);
            long lpszEncoded = stack.getPointerAddress();
            return nIIDFromString(lpszEncoded, lpiid.address());
        } finally {
            stack.setPointer(stackPointer);
        }
    }

    // --- [ CLSIDFromString ] ---

    public static int nCLSIDFromString(long lpsz, long pclsid) {
        long __functionAddress = Functions.CLSIDFromString;
        return callPPI(lpsz, pclsid, __functionAddress);
    }

    @NativeType("INT")
    public static int CLSIDFromString(@NativeType("LPCWSTR") ByteBuffer lpsz, @NativeType("REFCLSID") GUID pclsid) {
        if (CHECKS) {
            checkNT2(lpsz);
        }
        return nCLSIDFromString(memAddress(lpsz), pclsid.address());
    }

    @NativeType("INT")
    public static int CLSIDFromString(@NativeType("LPCWSTR") CharSequence lpsz, @NativeType("REFCLSID") GUID pclsid) {
        MemoryStack stack = stackGet(); int stackPointer = stack.getPointer();
        try {
            stack.nUTF16(lpsz, true);
            long lpszEncoded = stack.getPointerAddress();
            return nCLSIDFromString(lpszEncoded, pclsid.address());
        } finally {
            stack.setPointer(stackPointer);
        }
    }

}