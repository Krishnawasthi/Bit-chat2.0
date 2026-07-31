/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class PreconditionsTest {

    @Test
    public void testCheckNotNull() {
        assertNotNull(Preconditions.checkNotNull(new Object(), "msg"));
    }

    @Test(expected = NullPointerException.class)
    public void testCheckNotNull_Throws() {
        Preconditions.checkNotNull(null, "msg");
    }

    @Test
    public void testCheckArgument() {
        Preconditions.checkArgument(true, "msg");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckArgument_Throws() {
        Preconditions.checkArgument(false, "msg");
    }

    @Test
    public void testCheckState() {
        Preconditions.checkState(true, "msg");
    }

    @Test(expected = IllegalStateException.class)
    public void testCheckState_Throws() {
        Preconditions.checkState(false, "msg");
    }

    @Test
    public void testCheckNotEmpty_String() {
        assertEquals("test", Preconditions.checkNotEmpty("test", "msg"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckNotEmpty_StringThrows() {
        Preconditions.checkNotEmpty("", "msg");
    }

    @Test
    public void testCheckPositive() {
        assertEquals(5, Preconditions.checkPositive(5, "msg"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckPositive_Throws() {
        Preconditions.checkPositive(0, "msg");
    }

    @Test
    public void testCheckInRange() {
        assertEquals(5, Preconditions.checkInRange(5, 1, 10, "val"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckInRange_Throws() {
        Preconditions.checkInRange(11, 1, 10, "val");
    }
}
