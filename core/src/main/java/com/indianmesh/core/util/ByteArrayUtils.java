/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.core.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Arrays;

/**
 * Utility class for working with byte arrays.
 */
public final class ByteArrayUtils {

    private ByteArrayUtils() {
        // Prevent instantiation
    }

    /**
     * Concatenates multiple byte arrays into one.
     * @param arrays The arrays to concatenate.
     * @return A new concatenated byte array.
     */
    @NonNull
    public static byte[] concat(@NonNull byte[]... arrays) {
        Preconditions.checkNotNull(arrays, "arrays cannot be null");
        int totalLength = 0;
        for (byte[] array : arrays) {
            Preconditions.checkNotNull(array, "Array in arguments cannot be null");
            totalLength += array.length;
        }
        byte[] result = new byte[totalLength];
        int offset = 0;
        for (byte[] array : arrays) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }

    /**
     * Slices a byte array.
     * @param source The source array.
     * @param offset The starting offset.
     * @param length The length of the slice.
     * @return A new byte array containing the slice.
     */
    @NonNull
    public static byte[] slice(@NonNull byte[] source, int offset, int length) {
        Preconditions.checkNotNull(source, "source cannot be null");
        Preconditions.checkArgument(offset >= 0 && offset + length <= source.length, "Invalid offset or length");
        byte[] result = new byte[length];
        System.arraycopy(source, offset, result, 0, length);
        return result;
    }

    /**
     * Converts 4 bytes from the array to an int (big-endian).
     * @param data The array.
     * @param offset The offset.
     * @return The int value.
     */
    public static int toInt(@NonNull byte[] data, int offset) {
        Preconditions.checkNotNull(data, "data cannot be null");
        Preconditions.checkArgument(offset >= 0 && offset + 4 <= data.length, "Invalid offset");
        return (data[offset] << 24) |
               ((data[offset + 1] & 0xFF) << 16) |
               ((data[offset + 2] & 0xFF) << 8) |
               (data[offset + 3] & 0xFF);
    }

    /**
     * Converts an int to a 4-byte array (big-endian).
     * @param value The int value.
     * @return A 4-byte array.
     */
    @NonNull
    public static byte[] fromInt(int value) {
        return new byte[] {
            (byte) (value >> 24),
            (byte) (value >> 16),
            (byte) (value >> 8),
            (byte) value
        };
    }

    /**
     * Converts 8 bytes from the array to a long (big-endian).
     * @param data The array.
     * @param offset The offset.
     * @return The long value.
     */
    public static long toLong(@NonNull byte[] data, int offset) {
        Preconditions.checkNotNull(data, "data cannot be null");
        Preconditions.checkArgument(offset >= 0 && offset + 8 <= data.length, "Invalid offset");
        return ((long) data[offset] << 56) |
               ((long) (data[offset + 1] & 0xFF) << 48) |
               ((long) (data[offset + 2] & 0xFF) << 40) |
               ((long) (data[offset + 3] & 0xFF) << 32) |
               ((long) (data[offset + 4] & 0xFF) << 24) |
               ((long) (data[offset + 5] & 0xFF) << 16) |
               ((long) (data[offset + 6] & 0xFF) << 8) |
               ((long) (data[offset + 7] & 0xFF));
    }

    /**
     * Converts a long to an 8-byte array (big-endian).
     * @param value The long value.
     * @return An 8-byte array.
     */
    @NonNull
    public static byte[] fromLong(long value) {
        return new byte[] {
            (byte) (value >> 56),
            (byte) (value >> 48),
            (byte) (value >> 40),
            (byte) (value >> 32),
            (byte) (value >> 24),
            (byte) (value >> 16),
            (byte) (value >> 8),
            (byte) value
        };
    }

    /**
     * Converts 2 bytes from the array to a short (big-endian).
     * @param data The array.
     * @param offset The offset.
     * @return The short value.
     */
    public static short toShort(@NonNull byte[] data, int offset) {
        Preconditions.checkNotNull(data, "data cannot be null");
        Preconditions.checkArgument(offset >= 0 && offset + 2 <= data.length, "Invalid offset");
        return (short) (((data[offset] & 0xFF) << 8) | (data[offset + 1] & 0xFF));
    }

    /**
     * Converts a short to a 2-byte array (big-endian).
     * @param value The short value.
     * @return A 2-byte array.
     */
    @NonNull
    public static byte[] fromShort(short value) {
        return new byte[] {
            (byte) (value >> 8),
            (byte) value
        };
    }

    /**
     * Performs a constant-time comparison of two byte arrays to prevent timing attacks.
     * @param a The first array.
     * @param b The second array.
     * @return True if they are equal, false otherwise.
     */
    public static boolean equals(@Nullable byte[] a, @Nullable byte[] b) {
        if (a == null || b == null) {
            return a == b;
        }
        if (a.length != b.length) {
            return false;
        }
        int result = 0;
        for (int i = 0; i < a.length; i++) {
            result |= a[i] ^ b[i];
        }
        return result == 0;
    }

    /**
     * Checks if a byte array is null or empty.
     * @param data The byte array.
     * @return True if null or empty.
     */
    public static boolean isEmpty(@Nullable byte[] data) {
        return data == null || data.length == 0;
    }
}
