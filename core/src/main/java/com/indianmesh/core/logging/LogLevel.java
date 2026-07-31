/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.core.logging;

import androidx.annotation.NonNull;

/**
 * Represents the log severity levels.
 */
public enum LogLevel {
    VERBOSE(2),
    DEBUG(3),
    INFO(4),
    WARNING(5),
    ERROR(6);

    /**
     * The priority of the log level, matching Android's Log priorities.
     */
    public final int priority;

    LogLevel(int priority) {
        this.priority = priority;
    }

    /**
     * Checks if this log level is at least as severe as the target level.
     * @param target The target log level.
     * @return True if this priority is >= the target's priority.
     */
    public boolean isAtLeast(@NonNull LogLevel target) {
        return this.priority >= target.priority;
    }
}
