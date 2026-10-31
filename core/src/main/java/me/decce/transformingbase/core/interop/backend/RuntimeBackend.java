package me.decce.transformingbase.core.interop.backend;

public interface RuntimeBackend {
    void onLoad(LoadStage stage);
    double getTime();
    void requestWindowAttention(long handle);

    enum LoadStage {
        PRE_LIBRARY_INITIALIZE,
        PRE_WINDOW_CREATION
    }
}
