/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.core.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class NodeIdTest {

    @Test
    public void testGenerate_isUnique() {
        NodeId id1 = NodeId.generate();
        NodeId id2 = NodeId.generate();
        assertNotEquals(id1, id2);
    }

    @Test
    public void testHexRoundTrip() {
        NodeId original = NodeId.generate();
        String hex = original.toHex();
        NodeId parsed = NodeId.fromHex(hex);
        assertEquals(original, parsed);
    }

    @Test
    public void testBytesRoundTrip() {
        NodeId original = NodeId.generate();
        byte[] bytes = original.toBytes();
        NodeId parsed = NodeId.fromBytes(bytes);
        assertEquals(original, parsed);
    }

    @Test
    public void testToTruncatedLong() {
        byte[] bytes = new byte[16];
        bytes[0] = 1;
        bytes[7] = 2;
        NodeId id = NodeId.fromBytes(bytes);
        long truncated = id.toTruncatedLong();
        assertTrue(truncated != 0);
    }

    @Test
    public void testEqualsAndHashCode() {
        NodeId id1 = NodeId.generate();
        NodeId id2 = NodeId.fromBytes(id1.toBytes());
        assertEquals(id1, id2);
        assertEquals(id1.hashCode(), id2.hashCode());
    }

    @Test
    public void testCompareTo() {
        NodeId id1 = NodeId.generate();
        NodeId id2 = NodeId.fromBytes(id1.toBytes());
        assertEquals(0, id1.compareTo(id2));
    }
}
