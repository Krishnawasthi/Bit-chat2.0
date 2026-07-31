/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.core.logging;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Interface for logging mesh operations.
 */
public interface MeshLogger {

    /**
     * Log a verbose message.
     * @param tag The tag for the message.
     * @param msg The message.
     */
    void v(@NonNull String tag, @NonNull String msg);

    /**
     * Log a debug message.
     * @param tag The tag for the message.
     * @param msg The message.
     */
    void d(@NonNull String tag, @NonNull String msg);

    /**
     * Log an info message.
     * @param tag The tag for the message.
     * @param msg The message.
     */
    void i(@NonNull String tag, @NonNull String msg);

    /**
     * Log a warning message.
     * @param tag The tag for the message.
     * @param msg The message.
     */
    void w(@NonNull String tag, @NonNull String msg);

    /**
     * Log a warning message with an exception.
     * @param tag The tag for the message.
     * @param msg The message.
     * @param t The exception.
     */
    void w(@NonNull String tag, @NonNull String msg, @Nullable Throwable t);

    /**
     * Log an error message.
     * @param tag The tag for the message.
     * @param msg The message.
     */
    void e(@NonNull String tag, @NonNull String msg);

    /**
     * Log an error message with an exception.
     * @param tag The tag for the message.
     * @param msg The message.
     * @param t The exception.
     */
    void e(@NonNull String tag, @NonNull String msg, @Nullable Throwable t);

    /**
     * Checks if the given log level is currently loggable.
     * @param level The log level to check.
     * @return True if loggable.
     */
    boolean isLoggable(@NonNull LogLevel level);
}
