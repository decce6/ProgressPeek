package me.decce.transformingbase.interop.win32;

import me.decce.transformingbase.core.ProgressPeekCore;
import me.decce.transformingbase.core.ProgressStatus;
import me.decce.transformingbase.interop.ProgressOperator;
import org.lwjgl.glfw.GLFWNativeWin32;
import org.lwjgl.system.MemoryStack;

public class Win32ProgressOperator implements ProgressOperator {
    public static final String IID_ITASKBARLIST3 = "{EA1AFB91-9E28-4B86-90E9-9E9F8A5EEFAF}";
    public static final String CLSID_ITASKBARLIST3 = "{56FDF344-FD6D-11D0-958A-006097C9A090}";
    public static final int CLSCTX_INPROC_SERVER = 0x1;
    private ProgressStatus status = ProgressStatus.NONE;
    private int value;
    private long hWnd;
    private boolean initialized;
    private boolean unsupported;
    private ITaskbarList3 taskbarList3;
    private ITaskbarList3Vtbl taskbarList3Vtbl;
    private long taskbarList3Address;
    private long PFN_SETPROGRESSVALUE;
    private long PFN_SETPROGRESSSTATE;

    @Override
    public void initialize(long glfwWindow) {
        if (initialized || unsupported) {
            return;
        }
        hWnd = GLFWNativeWin32.glfwGetWin32Window(glfwWindow);
        if (hWnd == 0) {
            throw new IllegalStateException("hWnd is null!");
        }
        Ole32.CoInitialize(0);
        try (var stack = MemoryStack.stackPush()) {
            GUID IID_ITaskbarList3 = GUID.calloc(stack);
            GUID CLSID_TaskbarList = GUID.calloc(stack);
            Ole32.IIDFromString(IID_ITASKBARLIST3, IID_ITaskbarList3);
            Ole32.CLSIDFromString(CLSID_ITASKBARLIST3, CLSID_TaskbarList);
            var pointer = stack.pointers(0);
            var result = Ole32.CoCreateInstance(CLSID_TaskbarList, null, CLSCTX_INPROC_SERVER, IID_ITaskbarList3, pointer);
            if (result != 0) {
                unsupported = true;
                ProgressPeekCore.LOGGER.error("Failed to create taskbar list instance!", new Win32Exception(result));
                return;
            }
            taskbarList3Address = pointer.get(0);
            taskbarList3 = ITaskbarList3.create(taskbarList3Address);
            taskbarList3Vtbl = taskbarList3.lpVtbl();
            PFN_SETPROGRESSVALUE = taskbarList3Vtbl.SetProgressValue();
            PFN_SETPROGRESSSTATE = taskbarList3Vtbl.SetProgressState();
        }
        initialized = true;
    }

    @Override
    public void setStatus(ProgressStatus status) {
        if (initialized && status != this.status) {
            this.status = status;
            var win32Flag = switch (status) {
                // https://learn.microsoft.com/en-us/windows/win32/api/shobjidl_core/nf-shobjidl_core-itaskbarlist3-setprogressstate
                case NONE -> 0x0;
                case INDETERMINATE -> 0x1;
                case NORMAL -> 0x2;
                case ERROR -> 0x4;
                case PAUSED -> 0x8;
            };
            Explorerframe.SetProgressState(taskbarList3Address, hWnd, win32Flag, PFN_SETPROGRESSSTATE);
        }
    }

    @Override
    public void setValue(int percentage) {
        if (status == ProgressStatus.NORMAL && percentage == 0) {
            percentage = 1; // Make progress visible to avoid confusions
        }
        if (initialized && this.value != percentage) {
            this.value = percentage;
            Explorerframe.SetProgressValue(taskbarList3Address, hWnd, percentage, 100, PFN_SETPROGRESSVALUE);
        }
    }

    @Override
    public void transitionToNoProgress(boolean notify) {
        if (this.status != ProgressStatus.NONE) {
            this.setStatus(ProgressStatus.NONE);
            //TODO make flash configurable
            if (notify) {
                User32Ex.FlashWindow(hWnd, true);
            }
        }
    }
}
