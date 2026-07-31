/*
 * Copyright (c) 2026 Indian Mesh. All rights reserved.
 */
package com.indianmesh.network;

import androidx.annotation.NonNull;

/**
 * Interface representing the manager for higher-level payload transfers.
 * It is responsible for orchestrating the state of active transfers, handling
 * chunking, reassembly, and tracking the progress of large payload deliveries.
 */
public interface PayloadTransferManager {

    /**
     * Enqueues a payload for transfer to a specified destination.
     *
     * @param destinationId The identifier of the destination node.
     * @param payloadId     A unique identifier for the payload being transferred.
     * @param payload       The actual byte payload.
     */
    void enqueueTransfer(@NonNull String destinationId, @NonNull String payloadId, @NonNull byte[] payload);

    /**
     * Cancels an ongoing transfer for the given payload ID.
     *
     * @param payloadId The unique identifier for the payload transfer to be canceled.
     */
    void cancelTransfer(@NonNull String payloadId);
}
