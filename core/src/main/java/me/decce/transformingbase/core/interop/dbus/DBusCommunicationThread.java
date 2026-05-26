package me.decce.transformingbase.core.interop.dbus;

import me.decce.transformingbase.core.ProgressPeekCore;
import me.decce.transformingbase.core.ProgressStatus;
import me.decce.transformingbase.core.interop.NativeString;
import org.lwjgl.BufferUtils;
import org.lwjgl.PointerBuffer;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.MemoryUtil;

import java.nio.DoubleBuffer;
import java.nio.IntBuffer;

public class DBusCommunicationThread extends Thread {
    public static final NativeString S_INTERFACE = new NativeString("com.canonical.Unity.LauncherEntry");
    public static final NativeString S_SIGNAL = new NativeString("Update");
    public static final NativeString S_SIGNATURE_ARRAY_STRING_VARIANT = new NativeString("{sv}");
    public static final NativeString S_D = new NativeString("d");
    public static final NativeString S_B = new NativeString("b");
    public static final NativeString S_PROGRESS = new NativeString("progress");
    public static final NativeString S_PROGRESS_VISIBLE = new NativeString("progress-visible");
    public static NativeString S_OBJECT_PATH;
    public static NativeString S_DESKTOP_PATH;

    private final IntBuffer I_TRUE = BufferUtils.createIntBuffer(1).put(0, 1);
    private final IntBuffer I_FALSE = BufferUtils.createIntBuffer(1).put(0, 0);
    private final DoubleBuffer D_PROGRESS = BufferUtils.createDoubleBuffer(101);
    private final PointerBuffer P_ERROR = BufferUtils.createPointerBuffer(1);

    private final long itArgs = LibDBus.allocateDBusMessageIter();
    private final long itProps = LibDBus.allocateDBusMessageIter();
    private final long itKey = LibDBus.allocateDBusMessageIter();
    private final long itValue = LibDBus.allocateDBusMessageIter();

    // current* indicates the states already sent to DBus
    private ProgressStatus currentStatus;
    private int currentValue;

    private volatile ProgressStatus status = ProgressStatus.NONE;
    private volatile int value;
    private volatile boolean forceUpdate;

    private final long dbusConnection;

    public static final double INDETERMINATE_CYCLE_DURATION_SECS = 1.5d;
    public static final long INDETERMINATE_COOLDOWN_MS = 25L;
    private double indeterminateStartTime;

    private final Object sync = new Object();

    DBusCommunicationThread(String className) {
        super("ProgressPeek DBus Thread");
        super.setPriority(Thread.MIN_PRIORITY);
        super.setDaemon(true);

        S_OBJECT_PATH = new NativeString("/me/decce/progresspeek/" + ProcessHandle.current().pid());
        S_DESKTOP_PATH = new NativeString("application://" + className + ".desktop");

        LibDBus.dbus_error_init(P_ERROR.address());
        dbusConnection = LibDBus.dbus_bus_get(LibDBus.DBUS_BUS_SESSION, P_ERROR.address());
        if (LibDBus.dbus_error_is_set(P_ERROR.address()) != 0 || dbusConnection == 0L) {
            LibDBus.dbus_error_free(P_ERROR.address());
            ProgressPeekCore.LOGGER.error("Failed to establish DBus connection");
        }

        D_PROGRESS.put(0, 0.01d);
        for (int i = 1; i <= 100; i++) {
            D_PROGRESS.put(i, i * 0.01d);
        }
    }

    @Override
    public void run() {
        while (true) {
            if (this.currentStatus == ProgressStatus.INDETERMINATE) {
                this.value = updateIndeterminateValue();
            }
            if (this.forceUpdate || this.status != this.currentStatus || this.value != this.currentValue) {
                this.update();
            }
            else {
                if (this.currentStatus == ProgressStatus.INDETERMINATE) {
                    await(INDETERMINATE_COOLDOWN_MS);
                }
                else {
                    await();
                }
            }
        }
    }

    private void await() {
        synchronized (sync) {
            try {
                sync.wait();
            } catch (InterruptedException ignored) {}
        }
    }

    private void await(long timeout) {
        synchronized (sync) {
            try {
                sync.wait(timeout);
            } catch (InterruptedException ignored) {}
        }
    }

    public void set(ProgressStatus status) {
        this.set(status, status == ProgressStatus.INDETERMINATE ? 0 : this.value);
    }

    public void set(int value) {
        this.set(this.status, value);
    }

    public void set(ProgressStatus status, int value) {
        this.set(status, value, false);
    }

    public void set(ProgressStatus status, int value, boolean force) {
        if (this.status != status || this.value != value || force) {
            this.status = status;
            if (status != ProgressStatus.INDETERMINATE) {
                this.value = value;
            }

            if (force) {
                this.forceUpdate = true;
            }
            synchronized (sync) {
                sync.notify();
            }
        }
    }

    private void update() {
        ProgressStatus targetStatus = this.status;
        int targetValue = this.value;

        this.forceUpdate = false;

        long msg = LibDBus.dbus_message_new_signal(S_OBJECT_PATH.address(), S_INTERFACE.address(), S_SIGNAL.address());
        LibDBus.dbus_message_set_no_reply(msg, 1);
        LibDBus.dbus_message_iter_init_append(msg, itArgs);
        LibDBus.dbus_message_iter_append_basic(itArgs, LibDBus.DBUS_TYPE_STRING, S_DESKTOP_PATH.pointer());
        LibDBus.dbus_message_iter_open_container(itArgs, LibDBus.DBUS_TYPE_ARRAY, S_SIGNATURE_ARRAY_STRING_VARIANT.address(), itProps);

        // progress
        if (this.currentValue != targetValue) {
            this.currentValue = targetValue;
            LibDBus.dbus_message_iter_open_container(itProps, LibDBus.DBUS_TYPE_DICT_ENTRY, 0, itKey);
            LibDBus.dbus_message_iter_append_basic(itKey, LibDBus.DBUS_TYPE_STRING, S_PROGRESS.pointer());
            LibDBus.dbus_message_iter_open_container(itKey, LibDBus.DBUS_TYPE_VARIANT, S_D.address(), itValue);
            LibDBus.dbus_message_iter_append_basic(itValue, LibDBus.DBUS_TYPE_DOUBLE, MemoryUtil.memAddress(D_PROGRESS, targetValue));
            LibDBus.dbus_message_iter_close_container(itKey, itValue);
            LibDBus.dbus_message_iter_close_container(itProps, itKey);
        }

        // progress-visible
        if (this.currentStatus != targetStatus) {
            if (targetStatus == ProgressStatus.INDETERMINATE) {
                indeterminateStartTime = GLFW.glfwGetTime();
            }
            this.currentStatus = targetStatus;
            LibDBus.dbus_message_iter_open_container(itProps, LibDBus.DBUS_TYPE_DICT_ENTRY, 0, itKey);
            LibDBus.dbus_message_iter_append_basic(itKey, LibDBus.DBUS_TYPE_STRING, S_PROGRESS_VISIBLE.pointer());
            LibDBus.dbus_message_iter_open_container(itKey, LibDBus.DBUS_TYPE_VARIANT, S_B.address(), itValue);
            LibDBus.dbus_message_iter_append_basic(itValue, LibDBus.DBUS_TYPE_BOOLEAN, MemoryUtil.memAddress(targetStatus == ProgressStatus.NONE ? I_FALSE : I_TRUE));
            LibDBus.dbus_message_iter_close_container(itKey, itValue);
            LibDBus.dbus_message_iter_close_container(itProps, itKey);
        }

        LibDBus.dbus_message_iter_close_container(itArgs, itProps);

        LibDBus.dbus_connection_send(dbusConnection, msg,0);

        if (ProgressPeekCore.config.linuxExplicitFlush) {
            LibDBus.dbus_connection_flush(dbusConnection);
        }

        LibDBus.dbus_message_unref(msg);
    }

    private int updateIndeterminateValue() {
        double factor = ((GLFW.glfwGetTime() - indeterminateStartTime) % INDETERMINATE_CYCLE_DURATION_SECS) / INDETERMINATE_CYCLE_DURATION_SECS;
        return (int) (100.0d * cubicEaseInOut(factor < 0.5d ? factor * 2d : (1.0d - factor) * 2d));
    }

    private static double cubicEaseInOut(double t) {
        if (t <= 0.0d) return 0.0d;
        if (t >= 1.0d) return 1.0d;

        if (t < 0.5d) {
            return 4.0d * t * t * t;
        } else {
            double f = -2.0d * t + 2.0d;
            return 1.0d - (f * f * f) / 2.0d;
        }
    }
}
