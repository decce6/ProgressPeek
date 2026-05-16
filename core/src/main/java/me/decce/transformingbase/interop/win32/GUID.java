/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package me.decce.transformingbase.interop.win32;

import org.lwjgl.BufferUtils;
import org.lwjgl.system.*;

import java.nio.ByteBuffer;

import static org.lwjgl.system.Checks.check;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * <h3>Layout</h3>
 * 
 * <pre><code>
 * struct GUID {
 *     LONGLONG Data1;
 *     USHORT Data2;
 *     USHORT Data3;
 *     UCHAR * Data4;
 * }</code></pre>
 */
public class GUID extends Struct<GUID> implements NativeResource {

    /** The struct size in bytes. */
    public static final int SIZEOF;

    /** The struct alignment in bytes. */
    public static final int ALIGNOF;

    /** The struct member offsets. */
    public static final int
        DATA1,
        DATA2,
        DATA3,
        DATA4;

    static {
        Layout layout = __struct(
            __member(8),
            __member(2),
            __member(2),
            __member(POINTER_SIZE)
        );

        SIZEOF = layout.getSize();
        ALIGNOF = layout.getAlignment();

        DATA1 = layout.offsetof(0);
        DATA2 = layout.offsetof(1);
        DATA3 = layout.offsetof(2);
        DATA4 = layout.offsetof(3);
    }

    protected GUID(long address, ByteBuffer container) {
        super(address, container);
    }

    @Override
    protected GUID create(long address, ByteBuffer container) {
        return new GUID(address, container);
    }

    /**
     * Creates a {@code GUID} instance at the current position of the specified {@link ByteBuffer} container. Changes to the buffer's content will be
     * visible to the struct instance and vice versa.
     *
     * <p>The created instance holds a strong reference to the container object.</p>
     */
    public GUID(ByteBuffer container) {
        super(memAddress(container), __checkContainer(container, SIZEOF));
    }

    @Override
    public int sizeof() { return SIZEOF; }

    /** @return the value of the {@code Data1} field. */
    @NativeType("LONGLONG")
    public long Data1() { return nData1(address()); }
    /** @return the value of the {@code Data2} field. */
    @NativeType("USHORT")
    public short Data2() { return nData2(address()); }
    /** @return the value of the {@code Data3} field. */
    @NativeType("USHORT")
    public short Data3() { return nData3(address()); }
    /**
     * @return a {@link ByteBuffer} view of the data pointed to by the {@code Data4} field.
     *
     * @param capacity the number of elements in the returned buffer
     */
    @NativeType("UCHAR *")
    public ByteBuffer Data4(int capacity) { return nData4(address(), capacity); }

    /** Sets the specified value to the {@code Data1} field. */
    public GUID Data1(@NativeType("LONGLONG") long value) { nData1(address(), value); return this; }
    /** Sets the specified value to the {@code Data2} field. */
    public GUID Data2(@NativeType("USHORT") short value) { nData2(address(), value); return this; }
    /** Sets the specified value to the {@code Data3} field. */
    public GUID Data3(@NativeType("USHORT") short value) { nData3(address(), value); return this; }
    /** Sets the address of the specified {@link ByteBuffer} to the {@code Data4} field. */
    public GUID Data4(@NativeType("UCHAR *") ByteBuffer value) { nData4(address(), value); return this; }

    /** Initializes this struct with the specified values. */
    public GUID set(
        long Data1,
        short Data2,
        short Data3,
        ByteBuffer Data4
    ) {
        Data1(Data1);
        Data2(Data2);
        Data3(Data3);
        Data4(Data4);

        return this;
    }

    /**
     * Copies the specified struct data to this struct.
     *
     * @param src the source struct
     *
     * @return this struct
     */
    public GUID set(GUID src) {
        memCopy(src.address(), address(), SIZEOF);
        return this;
    }

    // -----------------------------------

    /** Returns a new {@code GUID} instance allocated with {@link MemoryUtil#memAlloc memAlloc}. The instance must be explicitly freed. */
    public static GUID malloc() {
        return new GUID(nmemAllocChecked(SIZEOF), null);
    }

    /** Returns a new {@code GUID} instance allocated with {@link MemoryUtil#memCalloc memCalloc}. The instance must be explicitly freed. */
    public static GUID calloc() {
        return new GUID(nmemCallocChecked(1, SIZEOF), null);
    }

    /** Returns a new {@code GUID} instance allocated with {@link BufferUtils}. */
    public static GUID create() {
        ByteBuffer container = BufferUtils.createByteBuffer(SIZEOF);
        return new GUID(memAddress(container), container);
    }

    /** Returns a new {@code GUID} instance for the specified memory address. */
    public static GUID create(long address) {
        return new GUID(address, null);
    }

    /** Like {@link #create(long) create}, but returns {@code null} if {@code address} is {@code NULL}. */
    public static GUID createSafe(long address) {
        return address == NULL ? null : new GUID(address, null);
    }

    /**
     * Returns a new {@link Buffer} instance allocated with {@link MemoryUtil#memAlloc memAlloc}. The instance must be explicitly freed.
     *
     * @param capacity the buffer capacity
     */
    public static Buffer malloc(int capacity) {
        return new Buffer(nmemAllocChecked(__checkMalloc(capacity, SIZEOF)), capacity);
    }

    /**
     * Returns a new {@link Buffer} instance allocated with {@link MemoryUtil#memCalloc memCalloc}. The instance must be explicitly freed.
     *
     * @param capacity the buffer capacity
     */
    public static Buffer calloc(int capacity) {
        return new Buffer(nmemCallocChecked(capacity, SIZEOF), capacity);
    }

    /**
     * Returns a new {@link Buffer} instance allocated with {@link BufferUtils}.
     *
     * @param capacity the buffer capacity
     */
    public static Buffer create(int capacity) {
        ByteBuffer container = __create(capacity, SIZEOF);
        return new Buffer(memAddress(container), container, -1, 0, capacity, capacity);
    }

    /**
     * Create a {@link Buffer} instance at the specified memory.
     *
     * @param address  the memory address
     * @param capacity the buffer capacity
     */
    public static Buffer create(long address, int capacity) {
        return new Buffer(address, capacity);
    }

    /** Like {@link #create(long, int) create}, but returns {@code null} if {@code address} is {@code NULL}. */
    public static Buffer createSafe(long address, int capacity) {
        return address == NULL ? null : new Buffer(address, capacity);
    }

    /**
     * Returns a new {@code GUID} instance allocated on the specified {@link MemoryStack}.
     *
     * @param stack the stack from which to allocate
     */
    public static GUID malloc(MemoryStack stack) {
        return new GUID(stack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    /**
     * Returns a new {@code GUID} instance allocated on the specified {@link MemoryStack} and initializes all its bits to zero.
     *
     * @param stack the stack from which to allocate
     */
    public static GUID calloc(MemoryStack stack) {
        return new GUID(stack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    /**
     * Returns a new {@link Buffer} instance allocated on the specified {@link MemoryStack}.
     *
     * @param stack    the stack from which to allocate
     * @param capacity the buffer capacity
     */
    public static Buffer malloc(int capacity, MemoryStack stack) {
        return new Buffer(stack.nmalloc(ALIGNOF, capacity * SIZEOF), capacity);
    }

    /**
     * Returns a new {@link Buffer} instance allocated on the specified {@link MemoryStack} and initializes all its bits to zero.
     *
     * @param stack    the stack from which to allocate
     * @param capacity the buffer capacity
     */
    public static Buffer calloc(int capacity, MemoryStack stack) {
        return new Buffer(stack.ncalloc(ALIGNOF, capacity, SIZEOF), capacity);
    }

    // -----------------------------------

    /** Unsafe version of {@link #Data1}. */
    public static long nData1(long struct) { return memGetLong(struct + GUID.DATA1); }
    /** Unsafe version of {@link #Data2}. */
    public static short nData2(long struct) { return memGetShort(struct + GUID.DATA2); }
    /** Unsafe version of {@link #Data3}. */
    public static short nData3(long struct) { return memGetShort(struct + GUID.DATA3); }
    /** Unsafe version of {@link #Data4(int) Data4}. */
    public static ByteBuffer nData4(long struct, int capacity) { return memByteBuffer(memGetAddress(struct + GUID.DATA4), capacity); }

    /** Unsafe version of {@link #Data1(long) Data1}. */
    public static void nData1(long struct, long value) { memPutLong(struct + GUID.DATA1, value); }
    /** Unsafe version of {@link #Data2(short) Data2}. */
    public static void nData2(long struct, short value) { memPutShort(struct + GUID.DATA2, value); }
    /** Unsafe version of {@link #Data3(short) Data3}. */
    public static void nData3(long struct, short value) { memPutShort(struct + GUID.DATA3, value); }
    /** Unsafe version of {@link #Data4(ByteBuffer) Data4}. */
    public static void nData4(long struct, ByteBuffer value) { memPutAddress(struct + GUID.DATA4, memAddress(value)); }

    /**
     * Validates pointer members that should not be {@code NULL}.
     *
     * @param struct the struct to validate
     */
    public static void validate(long struct) {
        check(memGetAddress(struct + GUID.DATA4));
    }

    // -----------------------------------

    /** An array of {@link GUID} structs. */
    public static class Buffer extends StructBuffer<GUID, Buffer> implements NativeResource {

        private static final GUID ELEMENT_FACTORY = GUID.create(-1L);

        /**
         * Creates a new {@code GUID.Buffer} instance backed by the specified container.
         *
         * <p>Changes to the container's content will be visible to the struct buffer instance and vice versa. The two buffers' position, limit, and mark values
         * will be independent. The new buffer's position will be zero, its capacity and its limit will be the number of bytes remaining in this buffer divided
         * by {@link GUID#SIZEOF}, and its mark will be undefined.</p>
         *
         * <p>The created buffer instance holds a strong reference to the container object.</p>
         */
        public Buffer(ByteBuffer container) {
            super(container, container.remaining() / SIZEOF);
        }

        public Buffer(long address, int cap) {
            super(address, null, -1, 0, cap, cap);
        }

        Buffer(long address, ByteBuffer container, int mark, int pos, int lim, int cap) {
            super(address, container, mark, pos, lim, cap);
        }

        @Override
        protected Buffer self() {
            return this;
        }

        @Override
        protected Buffer create(long address, ByteBuffer container, int mark, int position, int limit, int capacity) {
            return new Buffer(address, container, mark, position, limit, capacity);
        }

        @Override
        protected GUID getElementFactory() {
            return ELEMENT_FACTORY;
        }

        /** @return the value of the {@code Data1} field. */
        @NativeType("LONGLONG")
        public long Data1() { return GUID.nData1(address()); }
        /** @return the value of the {@code Data2} field. */
        @NativeType("USHORT")
        public short Data2() { return GUID.nData2(address()); }
        /** @return the value of the {@code Data3} field. */
        @NativeType("USHORT")
        public short Data3() { return GUID.nData3(address()); }
        /**
         * @return a {@link ByteBuffer} view of the data pointed to by the {@code Data4} field.
         *
         * @param capacity the number of elements in the returned buffer
         */
        @NativeType("UCHAR *")
        public ByteBuffer Data4(int capacity) { return GUID.nData4(address(), capacity); }

        /** Sets the specified value to the {@code Data1} field. */
        public Buffer Data1(@NativeType("LONGLONG") long value) { GUID.nData1(address(), value); return this; }
        /** Sets the specified value to the {@code Data2} field. */
        public Buffer Data2(@NativeType("USHORT") short value) { GUID.nData2(address(), value); return this; }
        /** Sets the specified value to the {@code Data3} field. */
        public Buffer Data3(@NativeType("USHORT") short value) { GUID.nData3(address(), value); return this; }
        /** Sets the address of the specified {@link ByteBuffer} to the {@code Data4} field. */
        public Buffer Data4(@NativeType("UCHAR *") ByteBuffer value) { GUID.nData4(address(), value); return this; }

    }

}