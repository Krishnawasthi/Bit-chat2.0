/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class ByteArrayUtilsTest {

    @Test
    public void testConcat() {
        byte[] a = {1, 2};
        byte[] b = {3, 4};
        byte[] result = ByteArrayUtils.concat(a, b);
        assertArrayEquals(new byte[]{1, 2, 3, 4}, result);
    }

    @Test
    public void testSlice() {
        byte[] source = {1, 2, 3, 4, 5};
        byte[] slice = ByteArrayUtils.slice(source, 1, 3);
        assertArrayEquals(new byte[]{2, 3, 4}, slice);
    }

    @Test
    public void testIntRoundTrip() {
        int val = 0x12345678;
        byte[] bytes = ByteArrayUtils.fromInt(val);
        int parsed = ByteArrayUtils.toInt(bytes, 0);
        assertEquals(val, parsed);
    }

    @Test
    public void testLongRoundTrip() {
        long val = 0x1234567890ABCDEFL;
        byte[] bytes = ByteArrayUtils.fromLong(val);
        long parsed = ByteArrayUtils.toLong(bytes, 0);
        assertEquals(val, parsed);
    }

    @Test
    public void testShortRoundTrip() {
        short val = 0x1234;
        byte[] bytes = ByteArrayUtils.fromShort(val);
        short parsed = ByteArrayUtils.toShort(bytes, 0);
        assertEquals(val, parsed);
    }

    @Test
    public void testConstantTimeEquals() {
        byte[] a = {1, 2, 3};
        byte[] b = {1, 2, 3};
        byte[] c = {1, 2, 4};
        assertTrue(ByteArrayUtils.equals(a, b));
        assertFalse(ByteArrayUtils.equals(a, c));
        assertFalse(ByteArrayUtils.equals(a, null));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(ByteArrayUtils.isEmpty(null));
        assertTrue(ByteArrayUtils.isEmpty(new byte[0]));
        assertFalse(ByteArrayUtils.isEmpty(new byte[]{1}));
    }
}
