/*
 * Copyright (c) 2026 Indian Mesh. All rights reserved.
 */
package com.indianmesh.network.ble;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattServer;
import android.bluetooth.BluetoothGattServerCallback;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.indianmesh.network.ConnectionManager;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of {@link ConnectionManager} that manages Bluetooth Low Energy (BLE)
 * connections. It handles both GATT client connections to other peers and acting
 * as a GATT server to receive incoming connections.
 */
public class BleConnectionManager implements ConnectionManager {

    @NonNull
    private final Context context;

    @Nullable
    private final BluetoothAdapter bluetoothAdapter;

    @Nullable
    private BluetoothGattServer gattServer;

    @NonNull
    private final Map<String, BluetoothGatt> connectedGatts;

    @NonNull
    private final BluetoothGattCallback gattClientCallback = new BluetoothGattCallback() {
        @Override
        @SuppressLint("MissingPermission")
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                gatt.discoverServices();
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                gatt.close();
                if (gatt.getDevice() != null) {
                    connectedGatts.remove(gatt.getDevice().getAddress());
                }
            }
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                // Connection fully established and ready for I/O
            }
        }
    };

    @NonNull
    private final BluetoothGattServerCallback gattServerCallback = new BluetoothGattServerCallback() {
        @Override
        public void onConnectionStateChange(BluetoothDevice device, int status, int newState) {
            // Handle remote devices connecting to our server
        }

        @Override
        @SuppressLint("MissingPermission")
        public void onCharacteristicWriteRequest(BluetoothDevice device, int requestId,
                                                 BluetoothGattCharacteristic characteristic,
                                                 boolean preparedWrite, boolean responseNeeded,
                                                 int offset, byte[] value) {
            if (gattServer != null) {
                if (responseNeeded) {
                    gattServer.sendResponse(device, requestId, BluetoothGatt.GATT_SUCCESS, offset, value);
                }
                // Typically route the received data to a higher-level handler here
            }
        }
    };

    /**
     * Constructs a new BleConnectionManager.
     *
     * @param context          The application context.
     * @param bluetoothManager The system's BluetoothManager.
     */
    public BleConnectionManager(@NonNull Context context, @Nullable BluetoothManager bluetoothManager) {
        this.context = context.getApplicationContext();
        if (bluetoothManager != null) {
            this.bluetoothAdapter = bluetoothManager.getAdapter();
        } else {
            this.bluetoothAdapter = null;
        }
        this.connectedGatts = new ConcurrentHashMap<>();
    }

    /**
     * Starts the GATT server to allow incoming peer connections.
     *
     * @param bluetoothManager The system's BluetoothManager.
     */
    @SuppressLint("MissingPermission")
    public void startServer(@NonNull BluetoothManager bluetoothManager) {
        gattServer = bluetoothManager.openGattServer(context, gattServerCallback);
        if (gattServer != null) {
            BluetoothGattService service = new BluetoothGattService(
                    BleConstants.SERVICE_UUID,
                    BluetoothGattService.SERVICE_TYPE_PRIMARY);

            BluetoothGattCharacteristic writeCharacteristic = new BluetoothGattCharacteristic(
                    BleConstants.CHARACTERISTIC_WRITE_UUID,
                    BluetoothGattCharacteristic.PROPERTY_WRITE,
                    BluetoothGattCharacteristic.PERMISSION_WRITE);

            BluetoothGattCharacteristic readCharacteristic = new BluetoothGattCharacteristic(
                    BleConstants.CHARACTERISTIC_READ_UUID,
                    BluetoothGattCharacteristic.PROPERTY_READ | BluetoothGattCharacteristic.PROPERTY_NOTIFY,
                    BluetoothGattCharacteristic.PERMISSION_READ);

            service.addCharacteristic(writeCharacteristic);
            service.addCharacteristic(readCharacteristic);

            gattServer.addService(service);
        }
    }

    /**
     * Stops the GATT server.
     */
    @SuppressLint("MissingPermission")
    public void stopServer() {
        if (gattServer != null) {
            gattServer.close();
            gattServer = null;
        }
    }

    @Override
    @SuppressLint("MissingPermission")
    public void connect(@NonNull String deviceId) {
        if (bluetoothAdapter == null || connectedGatts.containsKey(deviceId)) {
            return;
        }

        BluetoothDevice device = bluetoothAdapter.getRemoteDevice(deviceId);
        if (device == null) {
            return;
        }

        BluetoothGatt gatt;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            gatt = device.connectGatt(context, false, gattClientCallback, BluetoothDevice.TRANSPORT_LE);
        } else {
            gatt = device.connectGatt(context, false, gattClientCallback);
        }

        if (gatt != null) {
            connectedGatts.put(deviceId, gatt);
        }
    }

    @Override
    @SuppressLint("MissingPermission")
    public void disconnect(@NonNull String deviceId) {
        BluetoothGatt gatt = connectedGatts.remove(deviceId);
        if (gatt != null) {
            gatt.disconnect();
            // close() is handled in onConnectionStateChange callback
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    @SuppressLint("MissingPermission")
    public void sendData(@NonNull String deviceId, @NonNull byte[] data) {
        BluetoothGatt gatt = connectedGatts.get(deviceId);
        if (gatt == null) {
            return;
        }

        BluetoothGattService service = gatt.getService(BleConstants.SERVICE_UUID);
        if (service == null) {
            return;
        }

        BluetoothGattCharacteristic characteristic = service.getCharacteristic(BleConstants.CHARACTERISTIC_WRITE_UUID);
        if (characteristic == null) {
            return;
        }

        if (Build.VERSION.SDK_INT >= 33) {
            gatt.writeCharacteristic(characteristic, data, BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT);
        } else {
            characteristic.setValue(data);
            gatt.writeCharacteristic(characteristic);
        }
    }
}
