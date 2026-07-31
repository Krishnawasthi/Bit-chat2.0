/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.model;

/**
 * Represents the transport type used for communication.
 */
public enum TransportType {
    /** Bluetooth Low Energy. */
    BLE(10240, 512, 1),
    /** WiFi Direct. */
    WIFI_DIRECT(10485760, 65535, 0),
    /** Local Area Network. */
    LAN(10485760, 65535, 2),
    /** Bluetooth Classic. */
    BLUETOOTH_CLASSIC(102400, 1024, 3);

    private final int estimatedBandwidthBytesPerSecond;
    private final int maxMtuBytes;
    private final int priorityOrder;

    /**
     * Constructs a TransportType.
     *
     * @param estimatedBandwidthBytesPerSecond The estimated bandwidth in bytes per second.
     * @param maxMtuBytes                      The maximum MTU in bytes.
     * @param priorityOrder                    The priority order (lower is preferred).
     */
    TransportType(int estimatedBandwidthBytesPerSecond, int maxMtuBytes, int priorityOrder) {
        this.estimatedBandwidthBytesPerSecond = estimatedBandwidthBytesPerSecond;
        this.maxMtuBytes = maxMtuBytes;
        this.priorityOrder = priorityOrder;
    }

    /**
     * Gets the estimated bandwidth in bytes per second.
     *
     * @return The estimated bandwidth.
     */
    public int getEstimatedBandwidthBytesPerSecond() {
        return estimatedBandwidthBytesPerSecond;
    }

    /**
     * Gets the maximum MTU in bytes.
     *
     * @return The maximum MTU.
     */
    public int getMaxMtuBytes() {
        return maxMtuBytes;
    }

    /**
     * Gets the priority order (lower means preferred).
     *
     * @return The priority order.
     */
    public int getPriorityOrder() {
        return priorityOrder;
    }
}
