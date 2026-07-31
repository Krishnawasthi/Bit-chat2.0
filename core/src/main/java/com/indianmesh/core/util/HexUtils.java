/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.core.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Utility class for working with hexadecimal strings.
 */
public final class HexUtils {

    private static final char[] HEX_ARRAY = "0123456789abcdef".toCharArray();

    private HexUtils() {
        // Prevent instantiation
    }

    /**
     * Converts a byte array to a lower-case hex string.
     * @param bytes The byte array.
     * @return The hex string.
     */
    @NonNull
    public static String toHex(@Nullable byte[] bytes) {
        if (bytes == null) {
            return "";
        }
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    /**
     * Converts a hex string to a byte array.
     * @param hex The hex string.
     * @return The byte array.
     * @throws IllegalArgumentException if the hex string is invalid or has an odd length.
     */
    @NonNull
    public static byte[] fromHex(@Nullable String hex) {
        if (hex == null || hex.isEmpty()) {
            return new byte[0];
        }
        Preconditions.checkArgument(hex.length() % 2 == 0, "Hex string must have an even length");
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            int high = Character.digit(hex.charAt(i * 2), 16);
            int low = Character.digit(hex.charAt(i * 2 + 1), 16);
            Preconditions.checkArgument(high != -1 && low != -1, "Invalid hex character at index " + (i * 2));
            bytes[i] = (byte) ((high << 4) + low);
        }
        return bytes;
    }

    /**
     * Checks if a string is a valid hex string.
     * @param hex The string to check.
     * @return True if it is a valid hex string, false otherwise.
     */
    public static boolean isValidHex(@Nullable String hex) {
        if (hex == null || hex.length() % 2 != 0) {
            return false;
        }
        for (int i = 0; i < hex.length(); i++) {
            if (Character.digit(hex.charAt(i), 16) == -1) {
                return false;
            }
        }
        return true;
    }
}
