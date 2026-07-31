/* Copyright (c) 2026 Indian Mesh. All rights reserved. */
package com.indianmesh.app.di;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;
import com.indianmesh.network.MeshNetworkManager;
import com.indianmesh.network.DeviceDiscoverer;
import com.indianmesh.network.ConnectionManager;
import com.indianmesh.network.ble.BleDiscoverer;
import com.indianmesh.network.ble.BleConnectionManager;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    @Provides
    @Singleton
    public DeviceDiscoverer provideDeviceDiscoverer(@ApplicationContext Context context) {
        android.bluetooth.BluetoothManager bluetoothManager = (android.bluetooth.BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        android.bluetooth.BluetoothAdapter bluetoothAdapter = bluetoothManager != null ? bluetoothManager.getAdapter() : null;
        return new BleDiscoverer(bluetoothAdapter);
    }

    @Provides
    @Singleton
    public ConnectionManager provideConnectionManager(@ApplicationContext Context context) {
        android.bluetooth.BluetoothManager bluetoothManager = (android.bluetooth.BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        return new BleConnectionManager(context, bluetoothManager);
    }

    @Provides
    @Singleton
    public MeshNetworkManager provideMeshNetworkManager(DeviceDiscoverer discoverer, ConnectionManager connectionManager) {
        return new MeshNetworkManager() {
            @Override
            public void start() { }
            @Override
            public void stop() { }
            @Override
            public void sendPayload(byte[] payload, String destinationNodeId) { }
        };
    }
}
