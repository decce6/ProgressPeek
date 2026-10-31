package me.decce.transformingbase.core.interop.shared;

import java.util.function.LongSupplier;

public interface NativeHandle {
    LongSupplier forWindows();
    LongSupplier forMacOS();
    LongSupplier forLinux();
}
