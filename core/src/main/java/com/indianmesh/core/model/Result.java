/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.core.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.indianmesh.core.util.Preconditions;
import java.util.function.Function;

/**
 * Generic Result monad for error handling. Immutable and thread-safe.
 * @param <V> The value type.
 * @param <E> The error type.
 */
public final class Result<V, E extends Throwable> {

    @Nullable private final V value;
    @Nullable private final E error;
    private final boolean isSuccess;

    private Result(@Nullable V value, @Nullable E error, boolean isSuccess) {
        this.value = value;
        this.error = error;
        this.isSuccess = isSuccess;
    }

    /**
     * Creates a successful result.
     * @param value The value.
     * @param <V> The value type.
     * @param <E> The error type.
     * @return A success Result.
     */
    @NonNull
    public static <V, E extends Throwable> Result<V, E> success(@Nullable V value) {
        return new Result<>(value, null, true);
    }

    /**
     * Creates a failed result.
     * @param error The error.
     * @param <V> The value type.
     * @param <E> The error type.
     * @return A failure Result.
     */
    @NonNull
    public static <V, E extends Throwable> Result<V, E> failure(@NonNull E error) {
        Preconditions.checkNotNull(error, "Error cannot be null in failure result");
        return new Result<>(null, error, false);
    }

    /**
     * Checks if this result is a success.
     * @return True if success.
     */
    public boolean isSuccess() {
        return isSuccess;
    }

    /**
     * Checks if this result is a failure.
     * @return True if failure.
     */
    public boolean isFailure() {
        return !isSuccess;
    }

    /**
     * Gets the value of this result.
     * @return The value.
     * @throws IllegalStateException if this is a failure result.
     */
    @Nullable
    public V getValue() {
        Preconditions.checkState(isSuccess, "Cannot get value from a failure result");
        return value;
    }

    /**
     * Gets the error of this result.
     * @return The error.
     * @throws IllegalStateException if this is a success result.
     */
    @NonNull
    public E getError() {
        Preconditions.checkState(!isSuccess, "Cannot get error from a success result");
        return error;
    }

    /**
     * Maps the value of this result if it is a success.
     * @param mapper The mapping function.
     * @param <R> The new value type.
     * @return A new Result.
     */
    @NonNull
    public <R> Result<R, E> map(@NonNull Function<? super V, ? extends R> mapper) {
        Preconditions.checkNotNull(mapper, "Mapper cannot be null");
        if (isSuccess) {
            return Result.success(mapper.apply(value));
        } else {
            return Result.failure(error);
        }
    }

    /**
     * Flat-maps the value of this result if it is a success.
     * @param mapper The mapping function returning a Result.
     * @param <R> The new value type.
     * @return A new Result.
     */
    @NonNull
    public <R> Result<R, E> flatMap(@NonNull Function<? super V, ? extends Result<R, E>> mapper) {
        Preconditions.checkNotNull(mapper, "Mapper cannot be null");
        if (isSuccess) {
            return mapper.apply(value);
        } else {
            return Result.failure(error);
        }
    }

    /**
     * Recovers from a failure result by mapping the error to a value.
     * @param recovery The recovery function.
     * @return A success Result containing the recovered value or the original value.
     */
    @NonNull
    public Result<V, E> recover(@NonNull Function<? super E, ? extends V> recovery) {
        Preconditions.checkNotNull(recovery, "Recovery function cannot be null");
        if (isSuccess) {
            return this;
        } else {
            return Result.success(recovery.apply(error));
        }
    }

    /**
     * Returns the value if success, or the fallback value if failure.
     * @param fallback The fallback value.
     * @return The value or fallback.
     */
    @Nullable
    public V orElse(@Nullable V fallback) {
        if (isSuccess) {
            return value;
        } else {
            return fallback;
        }
    }

    /**
     * Returns the value if success, or throws the error if failure.
     * @return The value.
     * @throws E The error if failure.
     */
    @Nullable
    public V orElseThrow() throws E {
        if (isSuccess) {
            return value;
        } else {
            throw error;
        }
    }
}
