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
 * struct ITaskbarList3Vtbl {
 *     HANDLE QueryInterface;
 *     HANDLE AddRef;
 *     HANDLE Release;
 *     HANDLE HrInit;
 *     HANDLE AddTab;
 *     HANDLE DeleteTab;
 *     HANDLE ActivateTab;
 *     HANDLE SetActiveAlt;
 *     HANDLE MarkFullscreenWindow;
 *     HANDLE SetProgressValue;
 *     HANDLE SetProgressState;
 *     HANDLE RegisterTab;
 *     HANDLE UnregisterTab;
 *     HANDLE SetTabOrder;
 *     HANDLE SetTabActive;
 *     HANDLE ThumbBarAddButtons;
 *     HANDLE ThumbBarUpdateButtons;
 *     HANDLE ThumbBarSetImageList;
 *     HANDLE SetOverlayIcon;
 *     HANDLE SetThumbnailTooltip;
 *     HANDLE SetThumbnailClip;
 * }</code></pre>
 */
public class ITaskbarList3Vtbl extends Struct<ITaskbarList3Vtbl> implements NativeResource {

    /** The struct size in bytes. */
    public static final int SIZEOF;

    /** The struct alignment in bytes. */
    public static final int ALIGNOF;

    /** The struct member offsets. */
    public static final int
        QUERYINTERFACE,
        ADDREF,
        RELEASE,
        HRINIT,
        ADDTAB,
        DELETETAB,
        ACTIVATETAB,
        SETACTIVEALT,
        MARKFULLSCREENWINDOW,
        SETPROGRESSVALUE,
        SETPROGRESSSTATE,
        REGISTERTAB,
        UNREGISTERTAB,
        SETTABORDER,
        SETTABACTIVE,
        THUMBBARADDBUTTONS,
        THUMBBARUPDATEBUTTONS,
        THUMBBARSETIMAGELIST,
        SETOVERLAYICON,
        SETTHUMBNAILTOOLTIP,
        SETTHUMBNAILCLIP;

    static {
        Layout layout = __struct(
            __member(POINTER_SIZE),
            __member(POINTER_SIZE),
            __member(POINTER_SIZE),
            __member(POINTER_SIZE),
            __member(POINTER_SIZE),
            __member(POINTER_SIZE),
            __member(POINTER_SIZE),
            __member(POINTER_SIZE),
            __member(POINTER_SIZE),
            __member(POINTER_SIZE),
            __member(POINTER_SIZE),
            __member(POINTER_SIZE),
            __member(POINTER_SIZE),
            __member(POINTER_SIZE),
            __member(POINTER_SIZE),
            __member(POINTER_SIZE),
            __member(POINTER_SIZE),
            __member(POINTER_SIZE),
            __member(POINTER_SIZE),
            __member(POINTER_SIZE),
            __member(POINTER_SIZE)
        );

        SIZEOF = layout.getSize();
        ALIGNOF = layout.getAlignment();

        QUERYINTERFACE = layout.offsetof(0);
        ADDREF = layout.offsetof(1);
        RELEASE = layout.offsetof(2);
        HRINIT = layout.offsetof(3);
        ADDTAB = layout.offsetof(4);
        DELETETAB = layout.offsetof(5);
        ACTIVATETAB = layout.offsetof(6);
        SETACTIVEALT = layout.offsetof(7);
        MARKFULLSCREENWINDOW = layout.offsetof(8);
        SETPROGRESSVALUE = layout.offsetof(9);
        SETPROGRESSSTATE = layout.offsetof(10);
        REGISTERTAB = layout.offsetof(11);
        UNREGISTERTAB = layout.offsetof(12);
        SETTABORDER = layout.offsetof(13);
        SETTABACTIVE = layout.offsetof(14);
        THUMBBARADDBUTTONS = layout.offsetof(15);
        THUMBBARUPDATEBUTTONS = layout.offsetof(16);
        THUMBBARSETIMAGELIST = layout.offsetof(17);
        SETOVERLAYICON = layout.offsetof(18);
        SETTHUMBNAILTOOLTIP = layout.offsetof(19);
        SETTHUMBNAILCLIP = layout.offsetof(20);
    }

    protected ITaskbarList3Vtbl(long address, ByteBuffer container) {
        super(address, container);
    }

    @Override
    protected ITaskbarList3Vtbl create(long address, ByteBuffer container) {
        return new ITaskbarList3Vtbl(address, container);
    }

    /**
     * Creates a {@code ITaskbarList3Vtbl} instance at the current position of the specified {@link ByteBuffer} container. Changes to the buffer's content will be
     * visible to the struct instance and vice versa.
     *
     * <p>The created instance holds a strong reference to the container object.</p>
     */
    public ITaskbarList3Vtbl(ByteBuffer container) {
        super(memAddress(container), __checkContainer(container, SIZEOF));
    }

    @Override
    public int sizeof() { return SIZEOF; }

    /** @return the value of the {@code QueryInterface} field. */
    @NativeType("HANDLE")
    public long QueryInterface() { return nQueryInterface(address()); }
    /** @return the value of the {@code AddRef} field. */
    @NativeType("HANDLE")
    public long AddRef() { return nAddRef(address()); }
    /** @return the value of the {@code Release} field. */
    @NativeType("HANDLE")
    public long Release() { return nRelease(address()); }
    /** @return the value of the {@code HrInit} field. */
    @NativeType("HANDLE")
    public long HrInit() { return nHrInit(address()); }
    /** @return the value of the {@code AddTab} field. */
    @NativeType("HANDLE")
    public long AddTab() { return nAddTab(address()); }
    /** @return the value of the {@code DeleteTab} field. */
    @NativeType("HANDLE")
    public long DeleteTab() { return nDeleteTab(address()); }
    /** @return the value of the {@code ActivateTab} field. */
    @NativeType("HANDLE")
    public long ActivateTab() { return nActivateTab(address()); }
    /** @return the value of the {@code SetActiveAlt} field. */
    @NativeType("HANDLE")
    public long SetActiveAlt() { return nSetActiveAlt(address()); }
    /** @return the value of the {@code MarkFullscreenWindow} field. */
    @NativeType("HANDLE")
    public long MarkFullscreenWindow() { return nMarkFullscreenWindow(address()); }
    /** @return the value of the {@code SetProgressValue} field. */
    @NativeType("HANDLE")
    public long SetProgressValue() { return nSetProgressValue(address()); }
    /** @return the value of the {@code SetProgressState} field. */
    @NativeType("HANDLE")
    public long SetProgressState() { return nSetProgressState(address()); }
    /** @return the value of the {@code RegisterTab} field. */
    @NativeType("HANDLE")
    public long RegisterTab() { return nRegisterTab(address()); }
    /** @return the value of the {@code UnregisterTab} field. */
    @NativeType("HANDLE")
    public long UnregisterTab() { return nUnregisterTab(address()); }
    /** @return the value of the {@code SetTabOrder} field. */
    @NativeType("HANDLE")
    public long SetTabOrder() { return nSetTabOrder(address()); }
    /** @return the value of the {@code SetTabActive} field. */
    @NativeType("HANDLE")
    public long SetTabActive() { return nSetTabActive(address()); }
    /** @return the value of the {@code ThumbBarAddButtons} field. */
    @NativeType("HANDLE")
    public long ThumbBarAddButtons() { return nThumbBarAddButtons(address()); }
    /** @return the value of the {@code ThumbBarUpdateButtons} field. */
    @NativeType("HANDLE")
    public long ThumbBarUpdateButtons() { return nThumbBarUpdateButtons(address()); }
    /** @return the value of the {@code ThumbBarSetImageList} field. */
    @NativeType("HANDLE")
    public long ThumbBarSetImageList() { return nThumbBarSetImageList(address()); }
    /** @return the value of the {@code SetOverlayIcon} field. */
    @NativeType("HANDLE")
    public long SetOverlayIcon() { return nSetOverlayIcon(address()); }
    /** @return the value of the {@code SetThumbnailTooltip} field. */
    @NativeType("HANDLE")
    public long SetThumbnailTooltip() { return nSetThumbnailTooltip(address()); }
    /** @return the value of the {@code SetThumbnailClip} field. */
    @NativeType("HANDLE")
    public long SetThumbnailClip() { return nSetThumbnailClip(address()); }

    /** Sets the specified value to the {@code QueryInterface} field. */
    public ITaskbarList3Vtbl QueryInterface(@NativeType("HANDLE") long value) { nQueryInterface(address(), value); return this; }
    /** Sets the specified value to the {@code AddRef} field. */
    public ITaskbarList3Vtbl AddRef(@NativeType("HANDLE") long value) { nAddRef(address(), value); return this; }
    /** Sets the specified value to the {@code Release} field. */
    public ITaskbarList3Vtbl Release(@NativeType("HANDLE") long value) { nRelease(address(), value); return this; }
    /** Sets the specified value to the {@code HrInit} field. */
    public ITaskbarList3Vtbl HrInit(@NativeType("HANDLE") long value) { nHrInit(address(), value); return this; }
    /** Sets the specified value to the {@code AddTab} field. */
    public ITaskbarList3Vtbl AddTab(@NativeType("HANDLE") long value) { nAddTab(address(), value); return this; }
    /** Sets the specified value to the {@code DeleteTab} field. */
    public ITaskbarList3Vtbl DeleteTab(@NativeType("HANDLE") long value) { nDeleteTab(address(), value); return this; }
    /** Sets the specified value to the {@code ActivateTab} field. */
    public ITaskbarList3Vtbl ActivateTab(@NativeType("HANDLE") long value) { nActivateTab(address(), value); return this; }
    /** Sets the specified value to the {@code SetActiveAlt} field. */
    public ITaskbarList3Vtbl SetActiveAlt(@NativeType("HANDLE") long value) { nSetActiveAlt(address(), value); return this; }
    /** Sets the specified value to the {@code MarkFullscreenWindow} field. */
    public ITaskbarList3Vtbl MarkFullscreenWindow(@NativeType("HANDLE") long value) { nMarkFullscreenWindow(address(), value); return this; }
    /** Sets the specified value to the {@code SetProgressValue} field. */
    public ITaskbarList3Vtbl SetProgressValue(@NativeType("HANDLE") long value) { nSetProgressValue(address(), value); return this; }
    /** Sets the specified value to the {@code SetProgressState} field. */
    public ITaskbarList3Vtbl SetProgressState(@NativeType("HANDLE") long value) { nSetProgressState(address(), value); return this; }
    /** Sets the specified value to the {@code RegisterTab} field. */
    public ITaskbarList3Vtbl RegisterTab(@NativeType("HANDLE") long value) { nRegisterTab(address(), value); return this; }
    /** Sets the specified value to the {@code UnregisterTab} field. */
    public ITaskbarList3Vtbl UnregisterTab(@NativeType("HANDLE") long value) { nUnregisterTab(address(), value); return this; }
    /** Sets the specified value to the {@code SetTabOrder} field. */
    public ITaskbarList3Vtbl SetTabOrder(@NativeType("HANDLE") long value) { nSetTabOrder(address(), value); return this; }
    /** Sets the specified value to the {@code SetTabActive} field. */
    public ITaskbarList3Vtbl SetTabActive(@NativeType("HANDLE") long value) { nSetTabActive(address(), value); return this; }
    /** Sets the specified value to the {@code ThumbBarAddButtons} field. */
    public ITaskbarList3Vtbl ThumbBarAddButtons(@NativeType("HANDLE") long value) { nThumbBarAddButtons(address(), value); return this; }
    /** Sets the specified value to the {@code ThumbBarUpdateButtons} field. */
    public ITaskbarList3Vtbl ThumbBarUpdateButtons(@NativeType("HANDLE") long value) { nThumbBarUpdateButtons(address(), value); return this; }
    /** Sets the specified value to the {@code ThumbBarSetImageList} field. */
    public ITaskbarList3Vtbl ThumbBarSetImageList(@NativeType("HANDLE") long value) { nThumbBarSetImageList(address(), value); return this; }
    /** Sets the specified value to the {@code SetOverlayIcon} field. */
    public ITaskbarList3Vtbl SetOverlayIcon(@NativeType("HANDLE") long value) { nSetOverlayIcon(address(), value); return this; }
    /** Sets the specified value to the {@code SetThumbnailTooltip} field. */
    public ITaskbarList3Vtbl SetThumbnailTooltip(@NativeType("HANDLE") long value) { nSetThumbnailTooltip(address(), value); return this; }
    /** Sets the specified value to the {@code SetThumbnailClip} field. */
    public ITaskbarList3Vtbl SetThumbnailClip(@NativeType("HANDLE") long value) { nSetThumbnailClip(address(), value); return this; }

    /** Initializes this struct with the specified values. */
    public ITaskbarList3Vtbl set(
        long QueryInterface,
        long AddRef,
        long Release,
        long HrInit,
        long AddTab,
        long DeleteTab,
        long ActivateTab,
        long SetActiveAlt,
        long MarkFullscreenWindow,
        long SetProgressValue,
        long SetProgressState,
        long RegisterTab,
        long UnregisterTab,
        long SetTabOrder,
        long SetTabActive,
        long ThumbBarAddButtons,
        long ThumbBarUpdateButtons,
        long ThumbBarSetImageList,
        long SetOverlayIcon,
        long SetThumbnailTooltip,
        long SetThumbnailClip
    ) {
        QueryInterface(QueryInterface);
        AddRef(AddRef);
        Release(Release);
        HrInit(HrInit);
        AddTab(AddTab);
        DeleteTab(DeleteTab);
        ActivateTab(ActivateTab);
        SetActiveAlt(SetActiveAlt);
        MarkFullscreenWindow(MarkFullscreenWindow);
        SetProgressValue(SetProgressValue);
        SetProgressState(SetProgressState);
        RegisterTab(RegisterTab);
        UnregisterTab(UnregisterTab);
        SetTabOrder(SetTabOrder);
        SetTabActive(SetTabActive);
        ThumbBarAddButtons(ThumbBarAddButtons);
        ThumbBarUpdateButtons(ThumbBarUpdateButtons);
        ThumbBarSetImageList(ThumbBarSetImageList);
        SetOverlayIcon(SetOverlayIcon);
        SetThumbnailTooltip(SetThumbnailTooltip);
        SetThumbnailClip(SetThumbnailClip);

        return this;
    }

    /**
     * Copies the specified struct data to this struct.
     *
     * @param src the source struct
     *
     * @return this struct
     */
    public ITaskbarList3Vtbl set(ITaskbarList3Vtbl src) {
        memCopy(src.address(), address(), SIZEOF);
        return this;
    }

    // -----------------------------------

    /** Returns a new {@code ITaskbarList3Vtbl} instance allocated with {@link MemoryUtil#memAlloc memAlloc}. The instance must be explicitly freed. */
    public static ITaskbarList3Vtbl malloc() {
        return new ITaskbarList3Vtbl(nmemAllocChecked(SIZEOF), null);
    }

    /** Returns a new {@code ITaskbarList3Vtbl} instance allocated with {@link MemoryUtil#memCalloc memCalloc}. The instance must be explicitly freed. */
    public static ITaskbarList3Vtbl calloc() {
        return new ITaskbarList3Vtbl(nmemCallocChecked(1, SIZEOF), null);
    }

    /** Returns a new {@code ITaskbarList3Vtbl} instance allocated with {@link BufferUtils}. */
    public static ITaskbarList3Vtbl create() {
        ByteBuffer container = BufferUtils.createByteBuffer(SIZEOF);
        return new ITaskbarList3Vtbl(memAddress(container), container);
    }

    /** Returns a new {@code ITaskbarList3Vtbl} instance for the specified memory address. */
    public static ITaskbarList3Vtbl create(long address) {
        return new ITaskbarList3Vtbl(address, null);
    }

    /** Like {@link #create(long) create}, but returns {@code null} if {@code address} is {@code NULL}. */
    public static ITaskbarList3Vtbl createSafe(long address) {
        return address == NULL ? null : new ITaskbarList3Vtbl(address, null);
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
     * Returns a new {@code ITaskbarList3Vtbl} instance allocated on the specified {@link MemoryStack}.
     *
     * @param stack the stack from which to allocate
     */
    public static ITaskbarList3Vtbl malloc(MemoryStack stack) {
        return new ITaskbarList3Vtbl(stack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    /**
     * Returns a new {@code ITaskbarList3Vtbl} instance allocated on the specified {@link MemoryStack} and initializes all its bits to zero.
     *
     * @param stack the stack from which to allocate
     */
    public static ITaskbarList3Vtbl calloc(MemoryStack stack) {
        return new ITaskbarList3Vtbl(stack.ncalloc(ALIGNOF, 1, SIZEOF), null);
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

    /** Unsafe version of {@link #QueryInterface}. */
    public static long nQueryInterface(long struct) { return memGetAddress(struct + ITaskbarList3Vtbl.QUERYINTERFACE); }
    /** Unsafe version of {@link #AddRef}. */
    public static long nAddRef(long struct) { return memGetAddress(struct + ITaskbarList3Vtbl.ADDREF); }
    /** Unsafe version of {@link #Release}. */
    public static long nRelease(long struct) { return memGetAddress(struct + ITaskbarList3Vtbl.RELEASE); }
    /** Unsafe version of {@link #HrInit}. */
    public static long nHrInit(long struct) { return memGetAddress(struct + ITaskbarList3Vtbl.HRINIT); }
    /** Unsafe version of {@link #AddTab}. */
    public static long nAddTab(long struct) { return memGetAddress(struct + ITaskbarList3Vtbl.ADDTAB); }
    /** Unsafe version of {@link #DeleteTab}. */
    public static long nDeleteTab(long struct) { return memGetAddress(struct + ITaskbarList3Vtbl.DELETETAB); }
    /** Unsafe version of {@link #ActivateTab}. */
    public static long nActivateTab(long struct) { return memGetAddress(struct + ITaskbarList3Vtbl.ACTIVATETAB); }
    /** Unsafe version of {@link #SetActiveAlt}. */
    public static long nSetActiveAlt(long struct) { return memGetAddress(struct + ITaskbarList3Vtbl.SETACTIVEALT); }
    /** Unsafe version of {@link #MarkFullscreenWindow}. */
    public static long nMarkFullscreenWindow(long struct) { return memGetAddress(struct + ITaskbarList3Vtbl.MARKFULLSCREENWINDOW); }
    /** Unsafe version of {@link #SetProgressValue}. */
    public static long nSetProgressValue(long struct) { return memGetAddress(struct + ITaskbarList3Vtbl.SETPROGRESSVALUE); }
    /** Unsafe version of {@link #SetProgressState}. */
    public static long nSetProgressState(long struct) { return memGetAddress(struct + ITaskbarList3Vtbl.SETPROGRESSSTATE); }
    /** Unsafe version of {@link #RegisterTab}. */
    public static long nRegisterTab(long struct) { return memGetAddress(struct + ITaskbarList3Vtbl.REGISTERTAB); }
    /** Unsafe version of {@link #UnregisterTab}. */
    public static long nUnregisterTab(long struct) { return memGetAddress(struct + ITaskbarList3Vtbl.UNREGISTERTAB); }
    /** Unsafe version of {@link #SetTabOrder}. */
    public static long nSetTabOrder(long struct) { return memGetAddress(struct + ITaskbarList3Vtbl.SETTABORDER); }
    /** Unsafe version of {@link #SetTabActive}. */
    public static long nSetTabActive(long struct) { return memGetAddress(struct + ITaskbarList3Vtbl.SETTABACTIVE); }
    /** Unsafe version of {@link #ThumbBarAddButtons}. */
    public static long nThumbBarAddButtons(long struct) { return memGetAddress(struct + ITaskbarList3Vtbl.THUMBBARADDBUTTONS); }
    /** Unsafe version of {@link #ThumbBarUpdateButtons}. */
    public static long nThumbBarUpdateButtons(long struct) { return memGetAddress(struct + ITaskbarList3Vtbl.THUMBBARUPDATEBUTTONS); }
    /** Unsafe version of {@link #ThumbBarSetImageList}. */
    public static long nThumbBarSetImageList(long struct) { return memGetAddress(struct + ITaskbarList3Vtbl.THUMBBARSETIMAGELIST); }
    /** Unsafe version of {@link #SetOverlayIcon}. */
    public static long nSetOverlayIcon(long struct) { return memGetAddress(struct + ITaskbarList3Vtbl.SETOVERLAYICON); }
    /** Unsafe version of {@link #SetThumbnailTooltip}. */
    public static long nSetThumbnailTooltip(long struct) { return memGetAddress(struct + ITaskbarList3Vtbl.SETTHUMBNAILTOOLTIP); }
    /** Unsafe version of {@link #SetThumbnailClip}. */
    public static long nSetThumbnailClip(long struct) { return memGetAddress(struct + ITaskbarList3Vtbl.SETTHUMBNAILCLIP); }

    /** Unsafe version of {@link #QueryInterface(long) QueryInterface}. */
    public static void nQueryInterface(long struct, long value) { memPutAddress(struct + ITaskbarList3Vtbl.QUERYINTERFACE, check(value)); }
    /** Unsafe version of {@link #AddRef(long) AddRef}. */
    public static void nAddRef(long struct, long value) { memPutAddress(struct + ITaskbarList3Vtbl.ADDREF, check(value)); }
    /** Unsafe version of {@link #Release(long) Release}. */
    public static void nRelease(long struct, long value) { memPutAddress(struct + ITaskbarList3Vtbl.RELEASE, check(value)); }
    /** Unsafe version of {@link #HrInit(long) HrInit}. */
    public static void nHrInit(long struct, long value) { memPutAddress(struct + ITaskbarList3Vtbl.HRINIT, check(value)); }
    /** Unsafe version of {@link #AddTab(long) AddTab}. */
    public static void nAddTab(long struct, long value) { memPutAddress(struct + ITaskbarList3Vtbl.ADDTAB, check(value)); }
    /** Unsafe version of {@link #DeleteTab(long) DeleteTab}. */
    public static void nDeleteTab(long struct, long value) { memPutAddress(struct + ITaskbarList3Vtbl.DELETETAB, check(value)); }
    /** Unsafe version of {@link #ActivateTab(long) ActivateTab}. */
    public static void nActivateTab(long struct, long value) { memPutAddress(struct + ITaskbarList3Vtbl.ACTIVATETAB, check(value)); }
    /** Unsafe version of {@link #SetActiveAlt(long) SetActiveAlt}. */
    public static void nSetActiveAlt(long struct, long value) { memPutAddress(struct + ITaskbarList3Vtbl.SETACTIVEALT, check(value)); }
    /** Unsafe version of {@link #MarkFullscreenWindow(long) MarkFullscreenWindow}. */
    public static void nMarkFullscreenWindow(long struct, long value) { memPutAddress(struct + ITaskbarList3Vtbl.MARKFULLSCREENWINDOW, check(value)); }
    /** Unsafe version of {@link #SetProgressValue(long) SetProgressValue}. */
    public static void nSetProgressValue(long struct, long value) { memPutAddress(struct + ITaskbarList3Vtbl.SETPROGRESSVALUE, check(value)); }
    /** Unsafe version of {@link #SetProgressState(long) SetProgressState}. */
    public static void nSetProgressState(long struct, long value) { memPutAddress(struct + ITaskbarList3Vtbl.SETPROGRESSSTATE, check(value)); }
    /** Unsafe version of {@link #RegisterTab(long) RegisterTab}. */
    public static void nRegisterTab(long struct, long value) { memPutAddress(struct + ITaskbarList3Vtbl.REGISTERTAB, check(value)); }
    /** Unsafe version of {@link #UnregisterTab(long) UnregisterTab}. */
    public static void nUnregisterTab(long struct, long value) { memPutAddress(struct + ITaskbarList3Vtbl.UNREGISTERTAB, check(value)); }
    /** Unsafe version of {@link #SetTabOrder(long) SetTabOrder}. */
    public static void nSetTabOrder(long struct, long value) { memPutAddress(struct + ITaskbarList3Vtbl.SETTABORDER, check(value)); }
    /** Unsafe version of {@link #SetTabActive(long) SetTabActive}. */
    public static void nSetTabActive(long struct, long value) { memPutAddress(struct + ITaskbarList3Vtbl.SETTABACTIVE, check(value)); }
    /** Unsafe version of {@link #ThumbBarAddButtons(long) ThumbBarAddButtons}. */
    public static void nThumbBarAddButtons(long struct, long value) { memPutAddress(struct + ITaskbarList3Vtbl.THUMBBARADDBUTTONS, check(value)); }
    /** Unsafe version of {@link #ThumbBarUpdateButtons(long) ThumbBarUpdateButtons}. */
    public static void nThumbBarUpdateButtons(long struct, long value) { memPutAddress(struct + ITaskbarList3Vtbl.THUMBBARUPDATEBUTTONS, check(value)); }
    /** Unsafe version of {@link #ThumbBarSetImageList(long) ThumbBarSetImageList}. */
    public static void nThumbBarSetImageList(long struct, long value) { memPutAddress(struct + ITaskbarList3Vtbl.THUMBBARSETIMAGELIST, check(value)); }
    /** Unsafe version of {@link #SetOverlayIcon(long) SetOverlayIcon}. */
    public static void nSetOverlayIcon(long struct, long value) { memPutAddress(struct + ITaskbarList3Vtbl.SETOVERLAYICON, check(value)); }
    /** Unsafe version of {@link #SetThumbnailTooltip(long) SetThumbnailTooltip}. */
    public static void nSetThumbnailTooltip(long struct, long value) { memPutAddress(struct + ITaskbarList3Vtbl.SETTHUMBNAILTOOLTIP, check(value)); }
    /** Unsafe version of {@link #SetThumbnailClip(long) SetThumbnailClip}. */
    public static void nSetThumbnailClip(long struct, long value) { memPutAddress(struct + ITaskbarList3Vtbl.SETTHUMBNAILCLIP, check(value)); }

    /**
     * Validates pointer members that should not be {@code NULL}.
     *
     * @param struct the struct to validate
     */
    public static void validate(long struct) {
        check(memGetAddress(struct + ITaskbarList3Vtbl.QUERYINTERFACE));
        check(memGetAddress(struct + ITaskbarList3Vtbl.ADDREF));
        check(memGetAddress(struct + ITaskbarList3Vtbl.RELEASE));
        check(memGetAddress(struct + ITaskbarList3Vtbl.HRINIT));
        check(memGetAddress(struct + ITaskbarList3Vtbl.ADDTAB));
        check(memGetAddress(struct + ITaskbarList3Vtbl.DELETETAB));
        check(memGetAddress(struct + ITaskbarList3Vtbl.ACTIVATETAB));
        check(memGetAddress(struct + ITaskbarList3Vtbl.SETACTIVEALT));
        check(memGetAddress(struct + ITaskbarList3Vtbl.MARKFULLSCREENWINDOW));
        check(memGetAddress(struct + ITaskbarList3Vtbl.SETPROGRESSVALUE));
        check(memGetAddress(struct + ITaskbarList3Vtbl.SETPROGRESSSTATE));
        check(memGetAddress(struct + ITaskbarList3Vtbl.REGISTERTAB));
        check(memGetAddress(struct + ITaskbarList3Vtbl.UNREGISTERTAB));
        check(memGetAddress(struct + ITaskbarList3Vtbl.SETTABORDER));
        check(memGetAddress(struct + ITaskbarList3Vtbl.SETTABACTIVE));
        check(memGetAddress(struct + ITaskbarList3Vtbl.THUMBBARADDBUTTONS));
        check(memGetAddress(struct + ITaskbarList3Vtbl.THUMBBARUPDATEBUTTONS));
        check(memGetAddress(struct + ITaskbarList3Vtbl.THUMBBARSETIMAGELIST));
        check(memGetAddress(struct + ITaskbarList3Vtbl.SETOVERLAYICON));
        check(memGetAddress(struct + ITaskbarList3Vtbl.SETTHUMBNAILTOOLTIP));
        check(memGetAddress(struct + ITaskbarList3Vtbl.SETTHUMBNAILCLIP));
    }

    // -----------------------------------

    /** An array of {@link ITaskbarList3Vtbl} structs. */
    public static class Buffer extends StructBuffer<ITaskbarList3Vtbl, Buffer> implements NativeResource {

        private static final ITaskbarList3Vtbl ELEMENT_FACTORY = ITaskbarList3Vtbl.create(-1L);

        /**
         * Creates a new {@code ITaskbarList3Vtbl.Buffer} instance backed by the specified container.
         *
         * <p>Changes to the container's content will be visible to the struct buffer instance and vice versa. The two buffers' position, limit, and mark values
         * will be independent. The new buffer's position will be zero, its capacity and its limit will be the number of bytes remaining in this buffer divided
         * by {@link ITaskbarList3Vtbl#SIZEOF}, and its mark will be undefined.</p>
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
        protected ITaskbarList3Vtbl getElementFactory() {
            return ELEMENT_FACTORY;
        }

        /** @return the value of the {@code QueryInterface} field. */
        @NativeType("HANDLE")
        public long QueryInterface() { return ITaskbarList3Vtbl.nQueryInterface(address()); }
        /** @return the value of the {@code AddRef} field. */
        @NativeType("HANDLE")
        public long AddRef() { return ITaskbarList3Vtbl.nAddRef(address()); }
        /** @return the value of the {@code Release} field. */
        @NativeType("HANDLE")
        public long Release() { return ITaskbarList3Vtbl.nRelease(address()); }
        /** @return the value of the {@code HrInit} field. */
        @NativeType("HANDLE")
        public long HrInit() { return ITaskbarList3Vtbl.nHrInit(address()); }
        /** @return the value of the {@code AddTab} field. */
        @NativeType("HANDLE")
        public long AddTab() { return ITaskbarList3Vtbl.nAddTab(address()); }
        /** @return the value of the {@code DeleteTab} field. */
        @NativeType("HANDLE")
        public long DeleteTab() { return ITaskbarList3Vtbl.nDeleteTab(address()); }
        /** @return the value of the {@code ActivateTab} field. */
        @NativeType("HANDLE")
        public long ActivateTab() { return ITaskbarList3Vtbl.nActivateTab(address()); }
        /** @return the value of the {@code SetActiveAlt} field. */
        @NativeType("HANDLE")
        public long SetActiveAlt() { return ITaskbarList3Vtbl.nSetActiveAlt(address()); }
        /** @return the value of the {@code MarkFullscreenWindow} field. */
        @NativeType("HANDLE")
        public long MarkFullscreenWindow() { return ITaskbarList3Vtbl.nMarkFullscreenWindow(address()); }
        /** @return the value of the {@code SetProgressValue} field. */
        @NativeType("HANDLE")
        public long SetProgressValue() { return ITaskbarList3Vtbl.nSetProgressValue(address()); }
        /** @return the value of the {@code SetProgressState} field. */
        @NativeType("HANDLE")
        public long SetProgressState() { return ITaskbarList3Vtbl.nSetProgressState(address()); }
        /** @return the value of the {@code RegisterTab} field. */
        @NativeType("HANDLE")
        public long RegisterTab() { return ITaskbarList3Vtbl.nRegisterTab(address()); }
        /** @return the value of the {@code UnregisterTab} field. */
        @NativeType("HANDLE")
        public long UnregisterTab() { return ITaskbarList3Vtbl.nUnregisterTab(address()); }
        /** @return the value of the {@code SetTabOrder} field. */
        @NativeType("HANDLE")
        public long SetTabOrder() { return ITaskbarList3Vtbl.nSetTabOrder(address()); }
        /** @return the value of the {@code SetTabActive} field. */
        @NativeType("HANDLE")
        public long SetTabActive() { return ITaskbarList3Vtbl.nSetTabActive(address()); }
        /** @return the value of the {@code ThumbBarAddButtons} field. */
        @NativeType("HANDLE")
        public long ThumbBarAddButtons() { return ITaskbarList3Vtbl.nThumbBarAddButtons(address()); }
        /** @return the value of the {@code ThumbBarUpdateButtons} field. */
        @NativeType("HANDLE")
        public long ThumbBarUpdateButtons() { return ITaskbarList3Vtbl.nThumbBarUpdateButtons(address()); }
        /** @return the value of the {@code ThumbBarSetImageList} field. */
        @NativeType("HANDLE")
        public long ThumbBarSetImageList() { return ITaskbarList3Vtbl.nThumbBarSetImageList(address()); }
        /** @return the value of the {@code SetOverlayIcon} field. */
        @NativeType("HANDLE")
        public long SetOverlayIcon() { return ITaskbarList3Vtbl.nSetOverlayIcon(address()); }
        /** @return the value of the {@code SetThumbnailTooltip} field. */
        @NativeType("HANDLE")
        public long SetThumbnailTooltip() { return ITaskbarList3Vtbl.nSetThumbnailTooltip(address()); }
        /** @return the value of the {@code SetThumbnailClip} field. */
        @NativeType("HANDLE")
        public long SetThumbnailClip() { return ITaskbarList3Vtbl.nSetThumbnailClip(address()); }

        /** Sets the specified value to the {@code QueryInterface} field. */
        public Buffer QueryInterface(@NativeType("HANDLE") long value) { ITaskbarList3Vtbl.nQueryInterface(address(), value); return this; }
        /** Sets the specified value to the {@code AddRef} field. */
        public Buffer AddRef(@NativeType("HANDLE") long value) { ITaskbarList3Vtbl.nAddRef(address(), value); return this; }
        /** Sets the specified value to the {@code Release} field. */
        public Buffer Release(@NativeType("HANDLE") long value) { ITaskbarList3Vtbl.nRelease(address(), value); return this; }
        /** Sets the specified value to the {@code HrInit} field. */
        public Buffer HrInit(@NativeType("HANDLE") long value) { ITaskbarList3Vtbl.nHrInit(address(), value); return this; }
        /** Sets the specified value to the {@code AddTab} field. */
        public Buffer AddTab(@NativeType("HANDLE") long value) { ITaskbarList3Vtbl.nAddTab(address(), value); return this; }
        /** Sets the specified value to the {@code DeleteTab} field. */
        public Buffer DeleteTab(@NativeType("HANDLE") long value) { ITaskbarList3Vtbl.nDeleteTab(address(), value); return this; }
        /** Sets the specified value to the {@code ActivateTab} field. */
        public Buffer ActivateTab(@NativeType("HANDLE") long value) { ITaskbarList3Vtbl.nActivateTab(address(), value); return this; }
        /** Sets the specified value to the {@code SetActiveAlt} field. */
        public Buffer SetActiveAlt(@NativeType("HANDLE") long value) { ITaskbarList3Vtbl.nSetActiveAlt(address(), value); return this; }
        /** Sets the specified value to the {@code MarkFullscreenWindow} field. */
        public Buffer MarkFullscreenWindow(@NativeType("HANDLE") long value) { ITaskbarList3Vtbl.nMarkFullscreenWindow(address(), value); return this; }
        /** Sets the specified value to the {@code SetProgressValue} field. */
        public Buffer SetProgressValue(@NativeType("HANDLE") long value) { ITaskbarList3Vtbl.nSetProgressValue(address(), value); return this; }
        /** Sets the specified value to the {@code SetProgressState} field. */
        public Buffer SetProgressState(@NativeType("HANDLE") long value) { ITaskbarList3Vtbl.nSetProgressState(address(), value); return this; }
        /** Sets the specified value to the {@code RegisterTab} field. */
        public Buffer RegisterTab(@NativeType("HANDLE") long value) { ITaskbarList3Vtbl.nRegisterTab(address(), value); return this; }
        /** Sets the specified value to the {@code UnregisterTab} field. */
        public Buffer UnregisterTab(@NativeType("HANDLE") long value) { ITaskbarList3Vtbl.nUnregisterTab(address(), value); return this; }
        /** Sets the specified value to the {@code SetTabOrder} field. */
        public Buffer SetTabOrder(@NativeType("HANDLE") long value) { ITaskbarList3Vtbl.nSetTabOrder(address(), value); return this; }
        /** Sets the specified value to the {@code SetTabActive} field. */
        public Buffer SetTabActive(@NativeType("HANDLE") long value) { ITaskbarList3Vtbl.nSetTabActive(address(), value); return this; }
        /** Sets the specified value to the {@code ThumbBarAddButtons} field. */
        public Buffer ThumbBarAddButtons(@NativeType("HANDLE") long value) { ITaskbarList3Vtbl.nThumbBarAddButtons(address(), value); return this; }
        /** Sets the specified value to the {@code ThumbBarUpdateButtons} field. */
        public Buffer ThumbBarUpdateButtons(@NativeType("HANDLE") long value) { ITaskbarList3Vtbl.nThumbBarUpdateButtons(address(), value); return this; }
        /** Sets the specified value to the {@code ThumbBarSetImageList} field. */
        public Buffer ThumbBarSetImageList(@NativeType("HANDLE") long value) { ITaskbarList3Vtbl.nThumbBarSetImageList(address(), value); return this; }
        /** Sets the specified value to the {@code SetOverlayIcon} field. */
        public Buffer SetOverlayIcon(@NativeType("HANDLE") long value) { ITaskbarList3Vtbl.nSetOverlayIcon(address(), value); return this; }
        /** Sets the specified value to the {@code SetThumbnailTooltip} field. */
        public Buffer SetThumbnailTooltip(@NativeType("HANDLE") long value) { ITaskbarList3Vtbl.nSetThumbnailTooltip(address(), value); return this; }
        /** Sets the specified value to the {@code SetThumbnailClip} field. */
        public Buffer SetThumbnailClip(@NativeType("HANDLE") long value) { ITaskbarList3Vtbl.nSetThumbnailClip(address(), value); return this; }

    }

}