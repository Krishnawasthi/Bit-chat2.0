/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class PeerTest {

    @Test
    public void testIsNearby() {
        long now = System.currentTimeMillis();
        Peer nearbyPeer = new Peer.Builder().lastSeenAt(now - 10000).build();
        assertTrue(nearbyPeer.isNearby());
        
        Peer distantPeer = new Peer.Builder().lastSeenAt(now - 40000).build();
        assertFalse(distantPeer.isNearby());
    }

    @Test
    public void testIsDirectlyReachable() {
        Peer directPeer = new Peer.Builder().hopsAway(1).build();
        assertTrue(directPeer.isDirectlyReachable());
        
        Peer multiHopPeer = new Peer.Builder().hopsAway(2).build();
        assertFalse(multiHopPeer.isDirectlyReachable());
    }

    @Test
    public void testBuilder() {
        Peer peer = new Peer.Builder()
                .displayName("Alice")
                .hopsAway(1)
                .build();
                
        assertEquals("Alice", peer.getDisplayName());
        assertEquals(1, peer.getHopsAway());
    }
}
