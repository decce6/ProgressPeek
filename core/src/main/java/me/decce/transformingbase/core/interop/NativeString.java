package me.decce.transformingbase.core.interop;

import org.lwjgl.BufferUtils;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryUtil;

import java.nio.ByteBuffer;

public class NativeString {
    private final String string;
    private final ByteBuffer buffer;
    private PointerBuffer pointerBuffer;
    public NativeString(String string) {
        this.string = string;
        this.buffer = MemoryUtil.memUTF8(string);
    }

    public String string() {
        return string;
    }

    public long address() {
        return MemoryUtil.memAddress(buffer);
    }

    public long pointer() {
        if (pointerBuffer == null) {
            pointerBuffer = BufferUtils.createPointerBuffer(1);
        }
        return pointerBuffer.put(0, address()).address();
    }
}
