/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class CRC32UtilsTest {

    @Test
    public void testCompute() {
        byte[] data = "test".getBytes();
        int expected = (int) 3632233996L;
        assertEquals(expected, CRC32Utils.compute(data));
    }

    @Test
    public void testVerifyCorrect() {
        byte[] data = "test".getBytes();
        int expected = (int) 3632233996L;
        assertTrue(CRC32Utils.verify(data, expected));
    }

    @Test
    public void testVerifyIncorrect() {
        byte[] data = "test".getBytes();
        assertFalse(CRC32Utils.verify(data, 12345));
    }
}
