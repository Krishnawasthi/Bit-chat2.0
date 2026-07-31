/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.model;

import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import com.indianmesh.core.model.NodeId;
import com.indianmesh.core.model.MessageId;

public class MessageTest {

    @Test
    public void testBuilderCreatesValidMessage() {
        MessageId messageId = Mockito.mock(MessageId.class);
        Message message = new Message.Builder()
                .messageId(messageId)
                .content("Hello")
                .type(MessageType.TEXT)
                .build();
                
        assertEquals("Hello", message.getContent());
        assertEquals(MessageType.TEXT, message.getType());
        assertEquals(messageId, message.getMessageId());
    }

    @Test
    public void testIsScheduled() {
        Message scheduled = new Message.Builder().scheduledAt(System.currentTimeMillis() + 10000).build();
        assertTrue(scheduled.isScheduled());
        
        Message notScheduled = new Message.Builder().scheduledAt(0).build();
        assertFalse(notScheduled.isScheduled());
    }

    @Test
    public void testIsOutgoing() {
        NodeId selfId = Mockito.mock(NodeId.class);
        NodeId otherId = Mockito.mock(NodeId.class);
        
        Message outgoingMessage = new Message.Builder().senderId(selfId).build();
        assertTrue(outgoingMessage.isOutgoing(selfId));
        assertFalse(outgoingMessage.isOutgoing(otherId));
    }

    @Test
    public void testEqualsAndHashCode() {
        MessageId id1 = Mockito.mock(MessageId.class);
        MessageId id2 = Mockito.mock(MessageId.class);
        
        Message m1 = new Message.Builder().messageId(id1).content("A").build();
        Message m2 = new Message.Builder().messageId(id1).content("B").build();
        Message m3 = new Message.Builder().messageId(id2).content("A").build();
        
        assertEquals(m1, m2);
        assertNotEquals(m1, m3);
        assertEquals(m1.hashCode(), m2.hashCode());
    }
}
