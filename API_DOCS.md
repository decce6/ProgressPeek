# API Documentation

The Progress Peek API allows mods to set taskbar progress.

Whereas Progress Peek itself is LGPL-v3 licensed, the API is BSD-0 licensed, eliminating any concern or inconvenience using it.

## Gradle Setup

Modrinth Maven may be used to obtain the Progress Peek artifact.

```groovy
repositories {
    exclusiveContent {
        forRepository {
            maven {
                name = "Modrinth"
                url = "https://api.modrinth.com/maven"
            }
        }
        filter {
            includeGroup "maven.modrinth"
        }
    }
}

dependencies {
    modImplementation "maven.modrinth:progresspeek:$version:api"
}
```

where `$version` should be replaced with the corresponding version number found on Modrinth.

Note that this example uses the `api` artifact, which contains only the API class. In case you want access to the full mod, you can omit that classifier and use `modImplementation "maven.modrinth:progresspeek:$version"` instead. 

## API Usage

The main API class is `me.decce.progresspeek.api.ProgressPeekApi`.

### Obtaining an instance of the API

```java
import me.decce.progresspeek.api.ProgressPeekAPI;

ProgressPeekAPI progress = ProgressPeekAPI.getInstance("my_awesome_mod");
```

While it is possible to omit the parameter when calling `ProgressPeekAPI#getInstance`, it is recommended that you provide your mod id. This allows Progress Peek to correctly handle the case where multiple mods update the progress simultaneously, by calculating the average of their progress values, and using the status that is deemed to have the highest priority.

### Updating the progress

```java
progress.setStatusAndValue(ProgressPeekAPI.Status.NORMAL, 50); // Set progress to 50%

progress.setValue(90); // Set progress to 90%

progress.setStatus(ProgressPeekAPI.Status.INDETERMINATE); // In indeterminate mode, the progress value is ignored

progress.transitionToNoProgress(false); // Finish. Use true to notify the user by flashing the window in the taskbar.
```

`setStatus`, `setValue`, or `setStatusAndValue` can be used to update the progress status and value. Use `transitionToNoProgress` to end the progress and optionally notify the user.

Internally, Progress Peek caches the last status and value to minimize the number of system calls. Therefore, you do not need to implement your own cache when using the API.
