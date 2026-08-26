package me.decce.transformingbase.service;

import me.decce.transformingbase.core.ProgressPeekCore;
import me.decce.transformingbase.core.interop.ProgressOperator;

public class PostBootstrapper {
    public static void bootstrap() {
        initConfig();
        ProgressPeekCore.operator = ProgressOperator.create();
    }

    private static void initConfig() {
        ProgressPeekCore.config = ConfigLoader.load();
        ConfigLoader.save(ProgressPeekCore.config);
    }
}
