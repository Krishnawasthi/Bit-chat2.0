/*
 * Copyright (c) 2026 Indian Mesh. All rights reserved.
 */
package com.indianmesh.network;

import androidx.annotation.NonNull;

/**
 * Interface representing the primary manager for the mesh network.
 * It is responsible for starting and stopping the mesh capabilities,
 * as well as dispatching payloads across the network to specific nodes.
 */
public interface MeshNetworkManager {

    /**
     * Starts the mesh network manager, initializing underlying protocols
     * and beginning to participate in the mesh network.
     */
    void start();

    /**
     * Stops the mesh network manager, tearing down connections and
     * releasing network resources.
     */
    void stop();

    /**
     * Sends a raw byte payload to the specified destination node ID.
     *
     * @param payload           The byte array representing the payload data to be sent. Must not be null.
     * @param destinationNodeId The string identifier of the destination node. Must not be null.
     */
    void sendPayload(@NonNull byte[] payload, @NonNull String destinationNodeId);
}
