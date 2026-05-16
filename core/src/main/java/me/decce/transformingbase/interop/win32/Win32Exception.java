package me.decce.transformingbase.interop.win32;

public class Win32Exception extends RuntimeException {
    public Win32Exception(int errorCode) {
        super("Win32 error code: " + errorCode);
    }

    public Win32Exception(String message, int errorCode) {
        super(message + " (" + errorCode + ")");
    }
}
