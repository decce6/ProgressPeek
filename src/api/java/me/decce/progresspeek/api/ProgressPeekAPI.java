/*
 * Copyright (C) decce
 * SPDX-License-Identifier: 0BSD
 */

package me.decce.progresspeek.api;

import me.decce.transformingbase.core.ProgressPeekCore;
import me.decce.transformingbase.core.ProgressStatus;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This is the main API class for Progress Peek.
 * @since 1.1.0
 */
@SuppressWarnings("unused")
public class ProgressPeekAPI {
    private static final ProgressPeekAPI SHARED = new ProgressPeekAPI("<unnamed>");
    private static final Map<String, ProgressPeekAPI> MAP = new ConcurrentHashMap<>();
    private final String id;
    private Status status;
    private int value;
    private boolean notificationPending;

    static {
        MAP.put(SHARED.id, SHARED);
    }

    private ProgressPeekAPI(String id) {
        this.id = id;
        this.status = Status.NONE;
        this.value = 0;
    }

    /**
     * <p>
     * Retrieves the shared API instance.
     * </p>
     * <p>
     * Please note that this returns a shared instance for any user. If multiple users of the API attempt to update the
     * progress simultaneously, their values may overwrite each other. For this reason, it is recommended that you use
     * {@link ProgressPeekAPI#getInstance(String)} instead, which ensures states are aggregated by averaging values
     * across users.
     * </p>
     */
    public static ProgressPeekAPI getInstance() {
        return SHARED;
    }

    /**
     * <p>
     * Retrieves the API instance with the specific id.
     * </p>
     * @param id the id for the API instance, which is only used to distinguish the mod that is using the API.
     *           Recommended but not required to be your modid.
     */
    public static ProgressPeekAPI getInstance(String id) {
        return MAP.computeIfAbsent(id, ProgressPeekAPI::new);
    }

    /**
     * Updates the progress status.
     * @param status the new progress status
     */
    public void setStatus(Status status) {
        if (status == null) {
            throw new NullPointerException(this.id + " requested null status");
        }
        this.status = status;
        this.flush();
    }

    /**
     * Updates the current progress value.
     * @param value the new progress percentage, from 0 to 100, inclusive.
     */
    public void setValue(int value) {
        if (value > 100 || value < 0) {
            throw new IllegalArgumentException(this.id + " requested progress value " + value + ", which should be between 0 and 100 (inclusive)");
        }
        this.value = value;
        this.flush();
    }

    /**
     * Updates the current progress value.
     * @param value the new progress percentage, from 0 to 1, inclusive.
     */
    public void setValue(float value) {
        if (value > 1.0f || value < 0.0f) {
            throw new IllegalArgumentException(this.id + " requested progress value " + value + ", which should be between 0.0 and 1.0 (inclusive)");
        }
        setValue(Math.round(value * 100));
    }

    /**
     * Updates the current progress status and value.
     * @param status the new progress status
     * @param value the new progress percentage, from 0 to 100, inclusive
     */
    public void setStatusAndValue(Status status, int value) {
        setStatus(status);
        setValue(value);
        this.flush();
    }

    /**
     * <p>
     * Convenience method that sets the status to {@link Status#NONE} and resets the progress value.
     * </p>
     * <p>
     * Optionally, this notifies the user about the updated progress. This usually appears as the taskbar item flashing continuously.
     * </p>
     */
    public void transitionToNoProgress(boolean notify) {
        this.notificationPending |= notify;
        this.setStatusAndValue(Status.NONE, 0);
    }

    private void flush() {
        flushImpl();
    }

    private static void flushImpl() {
        Status status = Status.NONE;
        int total = 0;
        int num = 0;
        boolean notify = false;
        for (var v : MAP.values()) {
            status = status.combineWith(v.status);
            notify |= v.notificationPending;
            v.notificationPending = false;
            if (v.status != Status.NONE) {
                total += v.value;
                num ++;
            }
        }
        ProgressPeekCore.setStatus(status.toCoreStatus());
        if (num > 0) {
            ProgressPeekCore.setValue(clampProgress(Math.round(total * 1.0f / num)));
        }
        else {
            ProgressPeekCore.setValue(0);
        }
        if (notify) {
            ProgressPeekCore.requestAttention();
        }
    }

    private static int clampProgress(int progress) {
        return Math.max(0, Math.min(100, progress));
    }

    public enum Status {
        NONE(0),
        NORMAL(1),
        INDETERMINATE(2),
        PAUSED(3),
        ERROR(4);

        private final int priority;

        Status(int priority) {
            this.priority = priority;
        }

        private Status combineWith(Status s1) {
            return this.priority < s1.priority ? s1 : this;
        }

        private static Status fromCoreStatus(ProgressStatus ps) {
            return switch (ps) {
                case NONE -> Status.NONE;
                case NORMAL -> Status.NORMAL;
                case INDETERMINATE -> Status.INDETERMINATE;
                case PAUSED -> Status.PAUSED;
                case ERROR -> Status.ERROR;
            };
        }

        private ProgressStatus toCoreStatus() {
            return switch (this) {
                case NONE -> ProgressStatus.NONE;
                case NORMAL -> ProgressStatus.NORMAL;
                case INDETERMINATE -> ProgressStatus.INDETERMINATE;
                case PAUSED -> ProgressStatus.PAUSED;
                case ERROR -> ProgressStatus.ERROR;
            };
        }
    }
}
