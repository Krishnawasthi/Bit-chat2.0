/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class HexUtilsTest {

    @Test
    public void testRoundTrip() {
        byte[] original = {0x01, (byte)0xAB, (byte)0xFF, 0x00};
        String hex = HexUtils.toHex(original);
        assertEquals("01abff00", hex);
        byte[] parsed = HexUtils.fromHex(hex);
        assertArrayEquals(original, parsed);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidHexStrings() {
        HexUtils.fromHex("123");
    }

    @Test
    public void testEmptyAndNullInput() {
        assertEquals("", HexUtils.toHex(null));
        assertArrayEquals(new byte[0], HexUtils.fromHex(null));
        assertArrayEquals(new byte[0], HexUtils.fromHex(""));
    }
}
