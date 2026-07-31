/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.model;

/**
 * Represents the type of a conversation.
 */
public enum ConversationType {
    /** Direct one-to-one conversation. */
    DIRECT(false, 2),
    /** Group conversation. */
    GROUP(true, 1024),
    /** Broadcast conversation. */
    BROADCAST(true, 10000);

    private final boolean supportsMultipleMembers;
    private final int maxMembers;

    /**
     * Constructs a ConversationType.
     *
     * @param supportsMultipleMembers True if multiple members are supported.
     * @param maxMembers              The maximum number of members allowed.
     */
    ConversationType(boolean supportsMultipleMembers, int maxMembers) {
        this.supportsMultipleMembers = supportsMultipleMembers;
        this.maxMembers = maxMembers;
    }

    /**
     * Checks if the conversation supports multiple members.
     *
     * @return True if multiple members are supported.
     */
    public boolean supportsMultipleMembers() {
        return supportsMultipleMembers;
    }

    /**
     * Gets the maximum number of members.
     *
     * @return The maximum number of members.
     */
    public int getMaxMembers() {
        return maxMembers;
    }
}
