## Progress Peek

This mod adds live loading progress display to the taskbar.

![Progress display on Windows](assets/Progress_Windows.gif)

![Progress display on KDE Plasma](assets/Progress_KDE_Plasma.gif)

### Features

- Deep Integration: supports displaying progress of game loading, resource loading, world creation, etc.
- Performance Friendly: the performance impact of updating the taskbar progress is usually negligible
    - The current taskbar state and progress is cached, skipping native calls where possible
    - the native interop code fully utilizes LWJGL, which uses JNI or FFM (where supported) for optimal performance.
        - Windows: implemented via the `ITaskbarList3` COM interface
        - Linux: implemented via the Unity LauncherAPI using `libdbus`

### Platform Support

| Platform | Status                            |
|----------|-----------------------------------|
| Windows  | Supported (Windows 7+)            |
| macOS    | Currently unsupported             |
| Linux    | Supported (via Unity LauncherAPI) |
