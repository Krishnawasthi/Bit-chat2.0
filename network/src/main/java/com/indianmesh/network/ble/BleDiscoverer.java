/*
 * Copyright (c) 2026 Indian Mesh. All rights reserved.
 */
package com.indianmesh.network.ble;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.os.ParcelUuid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.indianmesh.network.DeviceDiscoverer;

import java.util.Collections;
import java.util.List;

/**
 * Implementation of {@link DeviceDiscoverer} for discovering nearby devices
 * over Bluetooth Low Energy (BLE). This class scans for devices advertising
 * the specific Indian Mesh service UUID.
 */
public class BleDiscoverer implements DeviceDiscoverer {

    @Nullable
    private final BluetoothLeScanner scanner;

    @Nullable
    private DiscoveryListener listener;

    private boolean isScanning;

    @NonNull
    private final ScanCallback scanCallback = new ScanCallback() {
        @Override
        @SuppressLint("MissingPermission")
        public void onScanResult(int callbackType, @NonNull ScanResult result) {
            super.onScanResult(callbackType, result);
            if (listener != null && result.getDevice() != null) {
                String address = result.getDevice().getAddress();
                if (address != null) {
                    listener.onDeviceFound(address);
                }
            }
        }

        @Override
        @SuppressLint("MissingPermission")
        public void onBatchScanResults(@NonNull List<ScanResult> results) {
            super.onBatchScanResults(results);
            if (listener != null) {
                for (ScanResult result : results) {
                    if (result.getDevice() != null) {
                        String address = result.getDevice().getAddress();
                        if (address != null) {
                            listener.onDeviceFound(address);
                        }
                    }
                }
            }
        }

        @Override
        public void onScanFailed(int errorCode) {
            super.onScanFailed(errorCode);
            // Handling scan failure in complete implementation
        }
    };

    /**
     * Constructs a new BleDiscoverer.
     *
     * @param bluetoothAdapter The system's BluetoothAdapter.
     */
    public BleDiscoverer(@Nullable BluetoothAdapter bluetoothAdapter) {
        if (bluetoothAdapter != null) {
            this.scanner = bluetoothAdapter.getBluetoothLeScanner();
        } else {
            this.scanner = null;
        }
    }

    @Override
    @SuppressLint("MissingPermission")
    public void startDiscovery() {
        if (scanner == null || isScanning) {
            return;
        }

        ScanFilter filter = new ScanFilter.Builder()
                .setServiceUuid(new ParcelUuid(BleConstants.SERVICE_UUID))
                .build();

        ScanSettings settings = new ScanSettings.Builder()
                .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY)
                .build();

        scanner.startScan(Collections.singletonList(filter), settings, scanCallback);
        isScanning = true;
    }

    @Override
    @SuppressLint("MissingPermission")
    public void stopDiscovery() {
        if (scanner == null || !isScanning) {
            return;
        }
        scanner.stopScan(scanCallback);
        isScanning = false;
    }

    @Override
    public void setDiscoveryListener(@NonNull DiscoveryListener listener) {
        this.listener = listener;
    }
}
