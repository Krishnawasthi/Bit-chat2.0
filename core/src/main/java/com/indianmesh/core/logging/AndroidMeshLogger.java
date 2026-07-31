/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.core.logging;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.indianmesh.core.util.Preconditions;

/**
 * Implementation of MeshLogger that delegates to android.util.Log.
 */
public class AndroidMeshLogger implements MeshLogger {

    private final LogLevel minLevel;

    /**
     * Constructor for AndroidMeshLogger.
     * @param minLevel The minimum log level to emit.
     */
    public AndroidMeshLogger(@NonNull LogLevel minLevel) {
        this.minLevel = Preconditions.checkNotNull(minLevel, "minLevel cannot be null");
    }

    @Override
    public void v(@NonNull String tag, @NonNull String msg) {
        if (isLoggable(LogLevel.VERBOSE)) {
            Log.v(tag, msg);
        }
    }

    @Override
    public void d(@NonNull String tag, @NonNull String msg) {
        if (isLoggable(LogLevel.DEBUG)) {
            Log.d(tag, msg);
        }
    }

    @Override
    public void i(@NonNull String tag, @NonNull String msg) {
        if (isLoggable(LogLevel.INFO)) {
            Log.i(tag, msg);
        }
    }

    @Override
    public void w(@NonNull String tag, @NonNull String msg) {
        if (isLoggable(LogLevel.WARNING)) {
            Log.w(tag, msg);
        }
    }

    @Override
    public void w(@NonNull String tag, @NonNull String msg, @Nullable Throwable t) {
        if (isLoggable(LogLevel.WARNING)) {
            Log.w(tag, msg, t);
        }
    }

    @Override
    public void e(@NonNull String tag, @NonNull String msg) {
        if (isLoggable(LogLevel.ERROR)) {
            Log.e(tag, msg);
        }
    }

    @Override
    public void e(@NonNull String tag, @NonNull String msg, @Nullable Throwable t) {
        if (isLoggable(LogLevel.ERROR)) {
            Log.e(tag, msg, t);
        }
    }

    @Override
    public boolean isLoggable(@NonNull LogLevel level) {
        return level.isAtLeast(minLevel);
    }
}
