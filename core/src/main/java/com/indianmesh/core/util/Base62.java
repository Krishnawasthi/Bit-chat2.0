/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.core.util;

import androidx.annotation.NonNull;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;

/**
 * Utility class for encoding and decoding byte arrays to/from Base62 strings.
 * Used primarily for generating human-readable invite codes.
 */
public final class Base62 {

    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final BigInteger BASE = BigInteger.valueOf(62);

    private Base62() {
        // Prevent instantiation
    }

    /**
     * Encodes a byte array into a Base62 string.
     * @param data The byte array.
     * @return The encoded Base62 string.
     */
    @NonNull
    public static String encode(@NonNull byte[] data) {
        Preconditions.checkNotNull(data, "data cannot be null");
        if (data.length == 0) {
            return "";
        }
        
        BigInteger value = new BigInteger(1, data);
        StringBuilder sb = new StringBuilder();
        
        while (value.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] divRem = value.divideAndRemainder(BASE);
            sb.append(ALPHABET.charAt(divRem[1].intValue()));
            value = divRem[0];
        }
        
        // Handle leading zeros
        for (byte b : data) {
            if (b == 0) {
                sb.append(ALPHABET.charAt(0));
            } else {
                break;
            }
        }
        
        return sb.reverse().toString();
    }

    /**
     * Decodes a Base62 string back into a byte array.
     * @param base62 The Base62 string.
     * @return The decoded byte array.
     */
    @NonNull
    public static byte[] decode(@NonNull String base62) {
        Preconditions.checkNotNull(base62, "base62 cannot be null");
        if (base62.isEmpty()) {
            return new byte[0];
        }
        
        BigInteger value = BigInteger.ZERO;
        int leadingZeros = 0;
        boolean countLeadingZeros = true;
        
        for (char c : base62.toCharArray()) {
            if (countLeadingZeros && c == ALPHABET.charAt(0)) {
                leadingZeros++;
            } else {
                countLeadingZeros = false;
            }
            int digit = ALPHABET.indexOf(c);
            Preconditions.checkArgument(digit != -1, "Invalid Base62 character: " + c);
            value = value.multiply(BASE).add(BigInteger.valueOf(digit));
        }
        
        byte[] valueBytes = value.toByteArray();
        
        // Strip sign bit if present
        int startOffset = (valueBytes.length > 0 && valueBytes[0] == 0) ? 1 : 0;
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (int i = 0; i < leadingZeros; i++) {
            baos.write(0);
        }
        baos.write(valueBytes, startOffset, valueBytes.length - startOffset);
        
        return baos.toByteArray();
    }
}
