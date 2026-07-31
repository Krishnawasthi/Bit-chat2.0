/*
 * Copyright (c) 2026 Indian Mesh. All rights reserved.
 */
package com.indianmesh.network;

import androidx.annotation.NonNull;

/**
 * Interface for discovering nearby devices participating in the mesh network.
 * Implementations will handle scanning for devices via various mediums (e.g., Bluetooth, WiFi Direct).
 */
public interface DeviceDiscoverer {

    /**
     * Starts the discovery process for finding nearby mesh nodes.
     */
    void startDiscovery();

    /**
     * Stops the discovery process.
     */
    void stopDiscovery();

    /**
     * Sets the listener that will receive callbacks when devices are found or lost.
     *
     * @param listener The listener to be notified of discovery events.
     */
    void setDiscoveryListener(@NonNull DiscoveryListener listener);

    /**
     * Listener interface to receive device discovery events.
     */
    interface DiscoveryListener {

        /**
         * Called when a new device is discovered.
         *
         * @param deviceId The unique identifier of the discovered device.
         */
        void onDeviceFound(@NonNull String deviceId);

        /**
         * Called when a previously discovered device is lost (e.g., moved out of range).
         *
         * @param deviceId The unique identifier of the lost device.
         */
        void onDeviceLost(@NonNull String deviceId);
    }
}
