/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.core.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Utility class for argument and state validation.
 */
public final class Preconditions {

    private Preconditions() {
        // Prevent instantiation
    }

    /**
     * Checks that the reference is not null.
     * @param reference The reference to check.
     * @param errorMessage The exception message to use if the check fails.
     * @param <T> The type of the reference.
     * @return The non-null reference.
     * @throws NullPointerException if reference is null.
     */
    @NonNull
    public static <T> T checkNotNull(@Nullable T reference, @NonNull String errorMessage) {
        if (reference == null) {
            throw new NullPointerException(errorMessage);
        }
        return reference;
    }

    /**
     * Checks that the boolean expression is true.
     * @param expression The expression to check.
     * @param errorMessage The exception message to use if the check fails.
     * @throws IllegalArgumentException if expression is false.
     */
    public static void checkArgument(boolean expression, @NonNull String errorMessage) {
        if (!expression) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    /**
     * Checks that the boolean expression representing the state of an object is true.
     * @param expression The expression to check.
     * @param errorMessage The exception message to use if the check fails.
     * @throws IllegalStateException if expression is false.
     */
    public static void checkState(boolean expression, @NonNull String errorMessage) {
        if (!expression) {
            throw new IllegalStateException(errorMessage);
        }
    }

    /**
     * Checks that the string is not null and not empty.
     * @param string The string to check.
     * @param errorMessage The exception message to use if the check fails.
     * @return The non-null, non-empty string.
     * @throws IllegalArgumentException if string is null or empty.
     */
    @NonNull
    public static String checkNotEmpty(@Nullable String string, @NonNull String errorMessage) {
        if (string == null || string.isEmpty()) {
            throw new IllegalArgumentException(errorMessage);
        }
        return string;
    }

    /**
     * Checks that the byte array is not null and not empty.
     * @param array The array to check.
     * @param errorMessage The exception message to use if the check fails.
     * @return The non-null, non-empty array.
     * @throws IllegalArgumentException if array is null or empty.
     */
    @NonNull
    public static byte[] checkNotEmpty(@Nullable byte[] array, @NonNull String errorMessage) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException(errorMessage);
        }
        return array;
    }

    /**
     * Checks that the integer value is strictly positive (> 0).
     * @param value The value to check.
     * @param errorMessage The exception message to use if the check fails.
     * @return The positive value.
     * @throws IllegalArgumentException if value is not positive.
     */
    public static int checkPositive(int value, @NonNull String errorMessage) {
        if (value <= 0) {
            throw new IllegalArgumentException(errorMessage);
        }
        return value;
    }

    /**
     * Checks that the integer value falls within the specified range (inclusive).
     * @param value The value to check.
     * @param min The minimum allowed value (inclusive).
     * @param max The maximum allowed value (inclusive).
     * @param name The name of the parameter for the error message.
     * @return The validated value.
     * @throws IllegalArgumentException if value is out of bounds.
     */
    public static int checkInRange(int value, int min, int max, @NonNull String name) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(name + " must be between " + min + " and " + max + " (inclusive)");
        }
        return value;
    }
}
