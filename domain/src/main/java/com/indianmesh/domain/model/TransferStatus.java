/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.model;

/**
 * Represents the status of a file or data transfer.
 */
public enum TransferStatus {
    /** Transfer is pending. */
    PENDING,
    /** Preparing the transfer. */
    PREPARING,
    /** Actively transferring data. */
    TRANSFERRING,
    /** Transfer is paused. */
    PAUSED,
    /** Transfer completed successfully. */
    COMPLETE,
    /** Transfer failed. */
    FAILED,
    /** Transfer was cancelled. */
    CANCELLED;

    /**
     * Checks if this status is a terminal state.
     *
     * @return True if terminal, false otherwise.
     */
    public boolean isTerminal() {
        return this == COMPLETE || this == FAILED || this == CANCELLED;
    }
}
