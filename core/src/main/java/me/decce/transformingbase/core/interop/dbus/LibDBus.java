package me.decce.transformingbase.core.interop.dbus;

import org.lwjgl.system.*;
import org.lwjgl.system.libc.LibCStdlib;

public class LibDBus {
    public static final int DBUS_BUS_SESSION = 0;
    public static final int DBUS_BUS_SYSTEM = 1;
    public static final int DBUS_TYPE_STRING = 's';
    public static final int DBUS_TYPE_DOUBLE = 'd';
    public static final int DBUS_TYPE_BOOLEAN = 'b';
    public static final int DBUS_TYPE_ARRAY = 'a';
    public static final int DBUS_TYPE_VARIANT = 'v';
    public static final int DBUS_TYPE_DICT_ENTRY = 'e';

    public static final SharedLibrary LIBDBUS = tryCreateDBusLibrary();

    public static final long PFNdbus_message_new_signal = maybeGetLibDBusFunctionAddress("dbus_message_new_signal");
    public static final long PFNdbus_message_unref = maybeGetLibDBusFunctionAddress("dbus_message_unref");
    public static final long PFNdbus_message_iter_init_append = maybeGetLibDBusFunctionAddress("dbus_message_iter_init_append");
    public static final long PFNdbus_message_iter_append_basic = maybeGetLibDBusFunctionAddress("dbus_message_iter_append_basic");
    public static final long PFNdbus_message_iter_open_container = maybeGetLibDBusFunctionAddress("dbus_message_iter_open_container");
    public static final long PFNdbus_message_iter_close_container = maybeGetLibDBusFunctionAddress("dbus_message_iter_close_container");
    public static final long PFNdbus_message_set_no_reply = maybeGetLibDBusFunctionAddress("dbus_message_set_no_reply");
    public static final long PFNdbus_connection_send = maybeGetLibDBusFunctionAddress("dbus_connection_send");
    public static final long PFNdbus_connection_flush = maybeGetLibDBusFunctionAddress("dbus_connection_flush");
    public static final long PFNdbus_connection_unref = maybeGetLibDBusFunctionAddress("dbus_connection_unref");
    public static final long PFNdbus_error_init = maybeGetLibDBusFunctionAddress("dbus_error_init");
    public static final long PFNdbus_error_is_set = maybeGetLibDBusFunctionAddress("dbus_error_is_set");
    public static final long PFNdbus_error_free = maybeGetLibDBusFunctionAddress("dbus_error_free");
    public static final long PFNdbus_bus_get = maybeGetLibDBusFunctionAddress("dbus_bus_get");

    private static SharedLibrary tryCreateDBusLibrary() {
        try {
            return APIUtil.apiCreateLibrary("libdbus-1.so.3");
        }
        catch (Throwable throwable) {
            return null;
        }
    }

    private static long maybeGetLibDBusFunctionAddress(String functionName) {
        if (LIBDBUS != null) {
            return APIUtil.apiGetFunctionAddress(LIBDBUS, functionName);
        }
        return 0L;
    }

    public static long dbus_message_new_signal(long path, long _interface, long name) {
        return JNI.invokePPPP(path, _interface, name, PFNdbus_message_new_signal);
    }

    public static void dbus_message_unref(long message) {
        JNI.invokePV(message, PFNdbus_message_unref);
    }

    public static void dbus_message_iter_init_append(long message, long iter) {
        JNI.invokePPV(message, iter, PFNdbus_message_iter_init_append);
    }

    public static int dbus_message_iter_append_basic(long iter, int type, long value) {
        return JNI.invokePPI(iter, type, value, PFNdbus_message_iter_append_basic);
    }

    public static int dbus_message_iter_open_container(long iter, int type, long contained_signature, long subiter) {
        return JNI.invokePPPI(iter, type, contained_signature, subiter, PFNdbus_message_iter_open_container);
    }

    public static int dbus_message_iter_close_container(long iter, long subiter) {
        return JNI.invokePPI(iter, subiter, PFNdbus_message_iter_close_container);
    }

    public static void dbus_message_set_no_reply(long message, int no_reply) {
        JNI.invokePV(message, no_reply, PFNdbus_message_set_no_reply);
    }

    public static int dbus_connection_send(long connection, long message, long client_serial) {
        return JNI.invokePPPI(connection, message, client_serial, PFNdbus_connection_send);
    }

    public static void dbus_connection_flush(long connection) {
        JNI.invokePV(connection, PFNdbus_connection_flush);
    }

    public static void dbus_connection_unref(long connection) {
        JNI.invokePV(connection, PFNdbus_connection_unref);
    }

    public static void dbus_error_init(long error) {
        JNI.invokePV(error, PFNdbus_error_init);
    }

    public static int dbus_error_is_set(long error) {
        return JNI.invokePI(error, PFNdbus_error_is_set);
    }

    public static void dbus_error_free(long error) {
        JNI.invokePV(error, PFNdbus_error_free);
    }

    public static long dbus_bus_get(int type, long error) {
        return JNI.invokePP(type, error, PFNdbus_bus_get);
    }

    public static long allocateDBusMessageIter() {
        // DBusMessageIter is an opaque struct containing 4 pointers and 4 integers
        return LibCStdlib.ncalloc(1, 4L * Pointer.POINTER_SIZE + 4L * Integer.BYTES);
    }
}
