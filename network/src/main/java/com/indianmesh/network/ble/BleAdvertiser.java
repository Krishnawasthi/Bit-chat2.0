/*
 * Copyright (c) 2026 Indian Mesh. All rights reserved.
 */
package com.indianmesh.network.ble;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.os.ParcelUuid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Handles advertising the device's presence over Bluetooth Low Energy (BLE),
 * allowing it to be discovered by other peers in the mesh network.
 */
public class BleAdvertiser {

    @Nullable
    private final BluetoothLeAdvertiser advertiser;

    private boolean isAdvertising;

    @NonNull
    private final AdvertiseCallback advertiseCallback = new AdvertiseCallback() {
        @Override
        public void onStartSuccess(AdvertiseSettings settingsInEffect) {
            super.onStartSuccess(settingsInEffect);
            // Successfully started advertising
        }

        @Override
        public void onStartFailure(int errorCode) {
            super.onStartFailure(errorCode);
            isAdvertising = false;
        }
    };

    /**
     * Constructs a new BleAdvertiser.
     *
     * @param bluetoothAdapter The system's BluetoothAdapter.
     */
    public BleAdvertiser(@Nullable BluetoothAdapter bluetoothAdapter) {
        if (bluetoothAdapter != null) {
            this.advertiser = bluetoothAdapter.getBluetoothLeAdvertiser();
        } else {
            this.advertiser = null;
        }
    }

    /**
     * Starts advertising the device's presence to nearby BLE scanners.
     */
    @SuppressLint("MissingPermission")
    public void startAdvertising() {
        if (advertiser == null || isAdvertising) {
            return;
        }

        AdvertiseSettings settings = new AdvertiseSettings.Builder()
                .setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_LOW_LATENCY)
                .setConnectable(true)
                .setTimeout(0)
                .setTxPowerLevel(AdvertiseSettings.ADVERTISE_TX_POWER_HIGH)
                .build();

        AdvertiseData data = new AdvertiseData.Builder()
                .setIncludeDeviceName(false)
                .setIncludeTxPowerLevel(false)
                .addServiceUuid(new ParcelUuid(BleConstants.SERVICE_UUID))
                .build();

        advertiser.startAdvertising(settings, data, advertiseCallback);
        isAdvertising = true;
    }

    /**
     * Stops advertising the device's presence.
     */
    @SuppressLint("MissingPermission")
    public void stopAdvertising() {
        if (advertiser == null || !isAdvertising) {
            return;
        }
        advertiser.stopAdvertising(advertiseCallback);
        isAdvertising = false;
    }
}
