/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.core.util;

import androidx.annotation.NonNull;
import java.util.zip.CRC32;

/**
 * Utility class for calculating and verifying CRC32 checksums.
 */
public final class CRC32Utils {

    private CRC32Utils() {
        // Prevent instantiation
    }

    /**
     * Computes the CRC32 checksum for a byte array.
     * @param data The byte array.
     * @return The computed checksum as an int.
     */
    public static int compute(@NonNull byte[] data) {
        Preconditions.checkNotNull(data, "data cannot be null");
        return compute(data, 0, data.length);
    }

    /**
     * Computes the CRC32 checksum for a portion of a byte array.
     * @param data The byte array.
     * @param offset The starting offset.
     * @param length The length of data to process.
     * @return The computed checksum as an int.
     */
    public static int compute(@NonNull byte[] data, int offset, int length) {
        Preconditions.checkNotNull(data, "data cannot be null");
        Preconditions.checkArgument(offset >= 0 && length >= 0 && offset + length <= data.length,
                "Invalid offset or length");
        CRC32 crc = new CRC32();
        crc.update(data, offset, length);
        return (int) crc.getValue();
    }

    /**
     * Verifies that the data matches the expected CRC32 checksum.
     * @param data The byte array.
     * @param expected The expected checksum as an int.
     * @return True if the checksum matches, false otherwise.
     */
    public static boolean verify(@NonNull byte[] data, int expected) {
        Preconditions.checkNotNull(data, "data cannot be null");
        return compute(data) == expected;
    }
}
