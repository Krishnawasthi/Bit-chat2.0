/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class DeliveryStatusTest {

    @Test
    public void testValidTransitions() {
        assertTrue(DeliveryStatus.DRAFT.canTransitionTo(DeliveryStatus.QUEUED));
        assertTrue(DeliveryStatus.QUEUED.canTransitionTo(DeliveryStatus.SENDING));
        assertTrue(DeliveryStatus.SENDING.canTransitionTo(DeliveryStatus.SENT));
        assertTrue(DeliveryStatus.SENT.canTransitionTo(DeliveryStatus.DELIVERED));
        assertTrue(DeliveryStatus.DELIVERED.canTransitionTo(DeliveryStatus.READ));
    }

    @Test
    public void testInvalidTransitions() {
        assertFalse(DeliveryStatus.READ.canTransitionTo(DeliveryStatus.DRAFT));
        assertFalse(DeliveryStatus.DELIVERED.canTransitionTo(DeliveryStatus.QUEUED));
        assertFalse(DeliveryStatus.DRAFT.canTransitionTo(DeliveryStatus.READ));
    }

    @Test
    public void testTerminalStates() {
        assertTrue(DeliveryStatus.READ.isTerminal());
        assertTrue(DeliveryStatus.FAILED.isTerminal());
        assertTrue(DeliveryStatus.EXPIRED.isTerminal());
        
        assertFalse(DeliveryStatus.DRAFT.isTerminal());
        assertFalse(DeliveryStatus.SENT.isTerminal());
        
        // Terminal states cannot transition anywhere
        assertFalse(DeliveryStatus.READ.canTransitionTo(DeliveryStatus.DRAFT));
    }
}
