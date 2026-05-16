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
 * struct ITaskbarList3 {
 *     {@link ITaskbarList3Vtbl ITaskbarList3Vtbl} * lpVtbl;
 * }</code></pre>
 */
public class ITaskbarList3 extends Struct<ITaskbarList3> implements NativeResource {

    /** The struct size in bytes. */
    public static final int SIZEOF;

    /** The struct alignment in bytes. */
    public static final int ALIGNOF;

    /** The struct member offsets. */
    public static final int
        LPVTBL;

    static {
        Layout layout = __struct(
            __member(POINTER_SIZE)
        );

        SIZEOF = layout.getSize();
        ALIGNOF = layout.getAlignment();

        LPVTBL = layout.offsetof(0);
    }

    protected ITaskbarList3(long address, ByteBuffer container) {
        super(address, container);
    }

    @Override
    protected ITaskbarList3 create(long address, ByteBuffer container) {
        return new ITaskbarList3(address, container);
    }

    /**
     * Creates a {@code ITaskbarList3} instance at the current position of the specified {@link ByteBuffer} container. Changes to the buffer's content will be
     * visible to the struct instance and vice versa.
     *
     * <p>The created instance holds a strong reference to the container object.</p>
     */
    public ITaskbarList3(ByteBuffer container) {
        super(memAddress(container), __checkContainer(container, SIZEOF));
    }

    @Override
    public int sizeof() { return SIZEOF; }

    /** @return a {@link ITaskbarList3Vtbl} view of the struct pointed to by the {@code lpVtbl} field. */
    @NativeType("ITaskbarList3Vtbl *")
    public ITaskbarList3Vtbl lpVtbl() { return nlpVtbl(address()); }

    /** Sets the address of the specified {@link ITaskbarList3Vtbl} to the {@code lpVtbl} field. */
    public ITaskbarList3 lpVtbl(@NativeType("ITaskbarList3Vtbl *") ITaskbarList3Vtbl value) { nlpVtbl(address(), value); return this; }

    /**
     * Copies the specified struct data to this struct.
     *
     * @param src the source struct
     *
     * @return this struct
     */
    public ITaskbarList3 set(ITaskbarList3 src) {
        memCopy(src.address(), address(), SIZEOF);
        return this;
    }

    // -----------------------------------

    /** Returns a new {@code ITaskbarList3} instance allocated with {@link MemoryUtil#memAlloc memAlloc}. The instance must be explicitly freed. */
    public static ITaskbarList3 malloc() {
        return new ITaskbarList3(nmemAllocChecked(SIZEOF), null);
    }

    /** Returns a new {@code ITaskbarList3} instance allocated with {@link MemoryUtil#memCalloc memCalloc}. The instance must be explicitly freed. */
    public static ITaskbarList3 calloc() {
        return new ITaskbarList3(nmemCallocChecked(1, SIZEOF), null);
    }

    /** Returns a new {@code ITaskbarList3} instance allocated with {@link BufferUtils}. */
    public static ITaskbarList3 create() {
        ByteBuffer container = BufferUtils.createByteBuffer(SIZEOF);
        return new ITaskbarList3(memAddress(container), container);
    }

    /** Returns a new {@code ITaskbarList3} instance for the specified memory address. */
    public static ITaskbarList3 create(long address) {
        return new ITaskbarList3(address, null);
    }

    /** Like {@link #create(long) create}, but returns {@code null} if {@code address} is {@code NULL}. */
    public static ITaskbarList3 createSafe(long address) {
        return address == NULL ? null : new ITaskbarList3(address, null);
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
     * Returns a new {@code ITaskbarList3} instance allocated on the specified {@link MemoryStack}.
     *
     * @param stack the stack from which to allocate
     */
    public static ITaskbarList3 malloc(MemoryStack stack) {
        return new ITaskbarList3(stack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    /**
     * Returns a new {@code ITaskbarList3} instance allocated on the specified {@link MemoryStack} and initializes all its bits to zero.
     *
     * @param stack the stack from which to allocate
     */
    public static ITaskbarList3 calloc(MemoryStack stack) {
        return new ITaskbarList3(stack.ncalloc(ALIGNOF, 1, SIZEOF), null);
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

    /** Unsafe version of {@link #lpVtbl}. */
    public static ITaskbarList3Vtbl nlpVtbl(long struct) { return ITaskbarList3Vtbl.create(memGetAddress(struct + ITaskbarList3.LPVTBL)); }

    /** Unsafe version of {@link #lpVtbl(ITaskbarList3Vtbl) lpVtbl}. */
    public static void nlpVtbl(long struct, ITaskbarList3Vtbl value) { memPutAddress(struct + ITaskbarList3.LPVTBL, value.address()); }

    /**
     * Validates pointer members that should not be {@code NULL}.
     *
     * @param struct the struct to validate
     */
    public static void validate(long struct) {
        long lpVtbl = memGetAddress(struct + ITaskbarList3.LPVTBL);
        check(lpVtbl);
        ITaskbarList3Vtbl.validate(lpVtbl);
    }

    // -----------------------------------

    /** An array of {@link ITaskbarList3} structs. */
    public static class Buffer extends StructBuffer<ITaskbarList3, Buffer> implements NativeResource {

        private static final ITaskbarList3 ELEMENT_FACTORY = ITaskbarList3.create(-1L);

        /**
         * Creates a new {@code ITaskbarList3.Buffer} instance backed by the specified container.
         *
         * <p>Changes to the container's content will be visible to the struct buffer instance and vice versa. The two buffers' position, limit, and mark values
         * will be independent. The new buffer's position will be zero, its capacity and its limit will be the number of bytes remaining in this buffer divided
         * by {@link ITaskbarList3#SIZEOF}, and its mark will be undefined.</p>
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
        protected ITaskbarList3 getElementFactory() {
            return ELEMENT_FACTORY;
        }

        /** @return a {@link ITaskbarList3Vtbl} view of the struct pointed to by the {@code lpVtbl} field. */
        @NativeType("ITaskbarList3Vtbl *")
        public ITaskbarList3Vtbl lpVtbl() { return ITaskbarList3.nlpVtbl(address()); }

        /** Sets the address of the specified {@link ITaskbarList3Vtbl} to the {@code lpVtbl} field. */
        public Buffer lpVtbl(@NativeType("ITaskbarList3Vtbl *") ITaskbarList3Vtbl value) { ITaskbarList3.nlpVtbl(address(), value); return this; }

    }

}