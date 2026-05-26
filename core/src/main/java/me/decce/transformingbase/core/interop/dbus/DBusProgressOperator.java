package me.decce.transformingbase.core.interop.dbus;

import me.decce.transformingbase.core.ProgressPeekCore;
import me.decce.transformingbase.core.ProgressStatus;
import me.decce.transformingbase.core.interop.NativeString;
import me.decce.transformingbase.core.interop.ProgressOperator;
import org.lwjgl.BufferUtils;
import org.lwjgl.PointerBuffer;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.MemoryUtil;

import java.io.IOException;
import java.nio.DoubleBuffer;
import java.nio.IntBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

public class DBusProgressOperator implements ProgressOperator {
    public static final String DEFAULT_APP_NAME = "Minecraft with ProgressPeek";
    public static final String DEFAULT_CLASS_NAME = "me.decce.progresspeek";

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
    private long glfwWindow;
    private String className;
    private String appName;
    private boolean desktopFileGenerated;
    private long dbusConnection;

    private ProgressStatus status = ProgressStatus.NONE;
    private int value;

    @Override
    public void preInitialize() {
        appName = getAppName();
        className = getClassName();
        if (!desktopFileGenerated) {
            desktopFileGenerated = true;

            String xdgDataHome = System.getenv("XDG_DATA_HOME");
            Path path;
            if (xdgDataHome != null && !xdgDataHome.isEmpty()) {
                path = Paths.get(xdgDataHome, "applications");
            } else {
                path = Paths.get(System.getProperty("user.home"), ".local", "share", "applications");
            }
            var baseName = className + ".desktop";
            var tempPath = path.resolve(baseName + ".tmp");
            var finalPath = path.resolve(baseName);
            try {
                // Instead of directly writing to the target path, we write to a temp file first, then move it to the target, which works more reliably
                Files.writeString(tempPath, generateDummyDesktopContent(), StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
                Files.move(tempPath, finalPath, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
                finalPath.toFile().deleteOnExit();
            } catch (IOException e) {
                ProgressPeekCore.LOGGER.error("Failed to create desktop file!", e);
            }
        }

        GLFW.glfwWindowHintString(GLFW.GLFW_X11_CLASS_NAME, className);
        GLFW.glfwWindowHintString(GLFW.GLFW_X11_INSTANCE_NAME, className);
        GLFW.glfwWindowHintString(GLFW.GLFW_WAYLAND_APP_ID, className);

        S_OBJECT_PATH = new NativeString("/me/decce/progresspeek/" + ProcessHandle.current().pid());
        S_DESKTOP_PATH = new NativeString("application://" + className + ".desktop");
    }

    @Override
    public void initialize(long glfwWindow) {
        if (dbusConnection != 0L) {
            return;
        }
        if (!desktopFileGenerated) {
            throw new IllegalStateException("preInitialize was not called before initialize!");
        }
        this.glfwWindow = glfwWindow;

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

        set(ProgressStatus.NONE, 0, true); // Progress is persisted across launches - reset to 0
    }

    @Override
    public void setStatus(ProgressStatus status) {
        set(status, this.value);
    }

    @Override
    public void setValue(int percentage) {
        set(this.status, percentage);
    }

    @Override
    public void transitionToNoProgress(boolean notify) {
        setStatus(ProgressStatus.NONE);
        //TODO notify
    }

    private String getAppName() {
        var raw = ProgressPeekCore.config.linuxAppName;
        if (raw.isEmpty()) {
            return DEFAULT_APP_NAME;
        }
        return raw;
    }

    private String getClassName() {
        var raw = ProgressPeekCore.config.linuxAppId;
        if (raw.isEmpty()) {
            return DEFAULT_CLASS_NAME;
        }
        return raw;
    }

    private void set(ProgressStatus status, int value) {
        this.set(status, value, false);
    }

    private void set(ProgressStatus status, int value, boolean force) {
        if (this.status == status && this.value == value && !force) {
            return;
        }
        long msg = LibDBus.dbus_message_new_signal(S_OBJECT_PATH.address(), S_INTERFACE.address(), S_SIGNAL.address());
        LibDBus.dbus_message_set_no_reply(msg, 1);
        LibDBus.dbus_message_iter_init_append(msg, itArgs);
        LibDBus.dbus_message_iter_append_basic(itArgs, LibDBus.DBUS_TYPE_STRING, S_DESKTOP_PATH.pointer());
        LibDBus.dbus_message_iter_open_container(itArgs, LibDBus.DBUS_TYPE_ARRAY, S_SIGNATURE_ARRAY_STRING_VARIANT.address(), itProps);

        // progress
        if (this.value != value || force) {
            this.value = value;
            LibDBus.dbus_message_iter_open_container(itProps, LibDBus.DBUS_TYPE_DICT_ENTRY, 0, itKey);
            LibDBus.dbus_message_iter_append_basic(itKey, LibDBus.DBUS_TYPE_STRING, S_PROGRESS.pointer());
            LibDBus.dbus_message_iter_open_container(itKey, LibDBus.DBUS_TYPE_VARIANT, S_D.address(), itValue);
            LibDBus.dbus_message_iter_append_basic(itValue, LibDBus.DBUS_TYPE_DOUBLE, MemoryUtil.memAddress(D_PROGRESS, value));
            LibDBus.dbus_message_iter_close_container(itKey, itValue);
            LibDBus.dbus_message_iter_close_container(itProps, itKey);
        }

        // progress-visible
        if (this.status != status || force) {
            this.status = status;
            LibDBus.dbus_message_iter_open_container(itProps, LibDBus.DBUS_TYPE_DICT_ENTRY, 0, itKey);
            LibDBus.dbus_message_iter_append_basic(itKey, LibDBus.DBUS_TYPE_STRING, S_PROGRESS_VISIBLE.pointer());
            LibDBus.dbus_message_iter_open_container(itKey, LibDBus.DBUS_TYPE_VARIANT, S_B.address(), itValue);
            LibDBus.dbus_message_iter_append_basic(itValue, LibDBus.DBUS_TYPE_BOOLEAN, MemoryUtil.memAddress(status == ProgressStatus.NONE ? I_FALSE : I_TRUE));
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

    private String generateDummyDesktopContent() {
        return """
            # Autogenerated by Progress Peek mod for progress display
            [Desktop Entry]
            Type=Application
            Version=1.0
            NoDisplay=true
            StartupWMClass=""" + className +
            "\nName=" + appName;
    }
}
