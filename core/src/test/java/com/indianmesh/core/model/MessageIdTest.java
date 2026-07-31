/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.core.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageIdTest {

    @Test
    public void testGenerate_isUnique() {
        MessageId id1 = MessageId.generate();
        MessageId id2 = MessageId.generate();
        assertNotEquals(id1, id2);
    }

    @Test
    public void testStringRoundTrip() {
        MessageId original = MessageId.generate();
        String uuidStr = original.toString();
        MessageId parsed = MessageId.fromString(uuidStr);
        assertEquals(original, parsed);
    }

    @Test
    public void testBytesRoundTrip() {
        MessageId original = MessageId.generate();
        byte[] bytes = original.toBytes();
        MessageId parsed = MessageId.fromBytes(bytes);
        assertEquals(original, parsed);
    }

    @Test
    public void testEqualsAndHashCode() {
        MessageId id1 = MessageId.generate();
        MessageId id2 = MessageId.fromString(id1.toString());
        assertEquals(id1, id2);
        assertEquals(id1.hashCode(), id2.hashCode());
    }
}
