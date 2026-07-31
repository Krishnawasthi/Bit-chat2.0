/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.model;

/**
 * Represents the delivery status of a message.
 */
public enum DeliveryStatus {
    /** Draft message not yet queued. */
    DRAFT("Draft", false),
    /** Message is queued for sending. */
    QUEUED("Queued", false),
    /** Message is currently being sent. */
    SENDING("Sending", false),
    /** Message has been sent to the network. */
    SENT("Sent", false),
    /** Message has been delivered to the recipient. */
    DELIVERED("Delivered", false),
    /** Message has been read by the recipient. */
    READ("Read", true),
    /** Message delivery failed. */
    FAILED("Failed", true),
    /** Message expired before delivery. */
    EXPIRED("Expired", true);

    private final String displayName;
    private final boolean isTerminal;

    /**
     * Constructs a DeliveryStatus.
     *
     * @param displayName The display name of the status.
     * @param isTerminal  Whether this is a terminal state.
     */
    DeliveryStatus(String displayName, boolean isTerminal) {
        this.displayName = displayName;
        this.isTerminal = isTerminal;
    }

    /**
     * Gets the display name.
     *
     * @return The display name.
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Checks if the status is terminal.
     *
     * @return True if terminal, false otherwise.
     */
    public boolean isTerminal() {
        return isTerminal;
    }

    /**
     * Checks if this status can transition to the given next status.
     *
     * @param next The next status to transition to.
     * @return True if the transition is valid, false otherwise.
     */
    public boolean canTransitionTo(DeliveryStatus next) {
        if (next == null) {
            return false;
        }
        if (this == next) {
            return true;
        }
        if (this.isTerminal) {
            return false;
        }
        switch (this) {
            case DRAFT:
                return next == QUEUED || next == FAILED || next == EXPIRED;
            case QUEUED:
                return next == SENDING || next == FAILED || next == EXPIRED;
            case SENDING:
                return next == SENT || next == FAILED || next == EXPIRED;
            case SENT:
                return next == DELIVERED || next == READ || next == FAILED || next == EXPIRED;
            case DELIVERED:
                return next == READ;
            default:
                return false;
        }
    }
}
