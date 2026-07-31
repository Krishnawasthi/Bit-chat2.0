/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class ConversationTest {

    @Test
    public void testHasUnread() {
        Conversation unreadConv = new Conversation.Builder().unreadCount(5).build();
        assertTrue(unreadConv.hasUnread());
        
        Conversation readConv = new Conversation.Builder().unreadCount(0).build();
        assertFalse(readConv.hasUnread());
    }

    @Test
    public void testIsDirect() {
        Conversation directConv = new Conversation.Builder().type(ConversationType.DIRECT).build();
        assertTrue(directConv.isDirect());
        
        Conversation groupConv = new Conversation.Builder().type(ConversationType.GROUP).build();
        assertFalse(groupConv.isDirect());
    }

    @Test
    public void testBuilder() {
        Conversation conv = new Conversation.Builder()
                .conversationId("c1")
                .title("Test Group")
                .type(ConversationType.GROUP)
                .build();
                
        assertEquals("c1", conv.getConversationId());
        assertEquals("Test Group", conv.getTitle());
    }
}
