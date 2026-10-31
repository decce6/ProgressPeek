package me.decce.transformingbase.core.interop.backend;

import me.decce.transformingbase.core.interop.shared.NativeHandle;

public interface RuntimeBackend {
    void onLoad(LoadStage stage);
    double getTime();
    void requestWindowAttention(NativeHandle handle);

    enum LoadStage {
        PRE_LIBRARY_INITIALIZATION,
        PRE_WINDOW_CREATION
    }
}
