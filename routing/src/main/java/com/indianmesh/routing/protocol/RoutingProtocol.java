/*
 * Copyright (c) 2026 Indian Mesh. All rights reserved.
 */
package com.indianmesh.routing.protocol;

import androidx.annotation.NonNull;

/**
 * Defines the contract for a routing protocol within the mesh network.
 * Implementations are responsible for determining how messages are handled
 * upon receipt and how they are routed to their ultimate destinations.
 */
public interface RoutingProtocol {

    /**
     * Handles an incoming message payload from a specified sender.
     *
     * @param payload  The raw byte array representing the message data.
     * @param senderId The unique identifier of the peer who sent the message.
     */
    void handleIncomingMessage(@NonNull byte[] payload, @NonNull String senderId);

    /**
     * Routes a message payload to the specified destination.
     *
     * @param payload       The raw byte array representing the message data.
     * @param destinationId The unique identifier of the peer intended to receive the message.
     */
    void routeMessage(@NonNull byte[] payload, @NonNull String destinationId);
}
