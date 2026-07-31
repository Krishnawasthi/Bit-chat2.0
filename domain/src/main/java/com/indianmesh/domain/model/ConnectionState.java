/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.model;

/**
 * Represents the connection state of a peer.
 */
public enum ConnectionState {
    /** Disconnected from the peer. */
    DISCONNECTED,
    /** Connecting to the peer. */
    CONNECTING,
    /** Connected to the peer, but not yet authenticated. */
    CONNECTED,
    /** Connected and authenticated. */
    AUTHENTICATED,
    /** In the process of disconnecting. */
    DISCONNECTING,
    /** Connection failed. */
    FAILED;

    /**
     * Checks if the connection is currently active (connected or authenticated).
     *
     * @return True if active.
     */
    public boolean isActive() {
        return this == CONNECTED || this == AUTHENTICATED;
    }
}
