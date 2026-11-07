## 1.2.2

- Fixed errors at startup on Windows

## 1.2.1

- Fixed a crash that might occur while loading level

## 1.2.0

- Fixed game crash when libdbus is not available on Linux (the mod will be automatically disabled instead.)
- Updated to 26.3 with SDL support

## 1.1.0

- Added an API. The API can be used by mods to set the progress status and value. The API is designed so multiple mods can use it to update the progress simultaneously. Documentation can be found [here](https://github.com/decce6/ProgressPeek/blob/main/API_DOCS.md).
- Linux: Changed default app name in the generated desktop file to "Minecraft"
- Linux: Fixed multiple instances sharing progress
- Added `iconFile` and `bannerFile` in NeoForge mod manifest

## 1.0.3

- Fixed progress occasionally not being reset after switching level.

## 1.0.2

Added 26.2 support.

## 1.0.1

- Fixed crash on startup on specific environments
- Fixed missing mod icon
- Added config for enabling/disabling mod on specific platform

## 1.0.0

- Initial release
