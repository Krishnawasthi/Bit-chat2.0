/*
 * Copyright (c) 2026 Indian Mesh. All rights reserved.
 */
package com.indianmesh.network;

import androidx.annotation.NonNull;

/**
 * Interface responsible for establishing and managing peer-to-peer connections.
 * This manager provides the mechanism to connect, disconnect, and send data to specific devices.
 */
public interface ConnectionManager {

    /**
     * Initiates a connection to the specified device.
     *
     * @param deviceId The unique identifier of the target device to connect to.
     */
    void connect(@NonNull String deviceId);

    /**
     * Disconnects from the specified device if currently connected.
     *
     * @param deviceId The unique identifier of the device to disconnect from.
     */
    void disconnect(@NonNull String deviceId);

    /**
     * Sends raw byte data directly to the specified device over the active connection.
     *
     * @param deviceId The unique identifier of the destination device.
     * @param data     The byte array to be sent to the device.
     */
    void sendData(@NonNull String deviceId, @NonNull byte[] data);
}
