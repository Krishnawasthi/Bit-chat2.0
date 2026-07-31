/*
 * Copyright (c) 2026 Indian Mesh. All rights reserved.
 */
package com.indianmesh.network.ble;

import androidx.annotation.NonNull;

import java.util.UUID;

/**
 * Constants used for Bluetooth Low Energy (BLE) communication within the Indian Mesh network.
 * This class provides standard UUIDs for the mesh service and its read/write characteristics.
 */
public final class BleConstants {

    /**
     * The UUID for the main Indian Mesh BLE Service.
     */
    @NonNull
    public static final UUID SERVICE_UUID = UUID.fromString("11223344-5566-7788-9900-aabbccddeeff");

    /**
     * The UUID for the characteristic used to write data to a remote device.
     */
    @NonNull
    public static final UUID CHARACTERISTIC_WRITE_UUID = UUID.fromString("22334455-6677-8899-00aa-bbccddeeff11");

    /**
     * The UUID for the characteristic used to read data from a remote device.
     */
    @NonNull
    public static final UUID CHARACTERISTIC_READ_UUID = UUID.fromString("33445566-7788-9900-aabb-ccddeeff1122");

    private BleConstants() {
        // Prevent instantiation
    }
}
