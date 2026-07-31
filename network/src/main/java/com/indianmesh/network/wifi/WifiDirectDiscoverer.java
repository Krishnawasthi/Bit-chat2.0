/*
 * Copyright (c) 2026 Indian Mesh. All rights reserved.
 */
package com.indianmesh.network.wifi;

import android.content.Context;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.indianmesh.network.DeviceDiscoverer;

/**
 * Implementation of {@link DeviceDiscoverer} using Wi-Fi Direct (P2P).
 */
public class WifiDirectDiscoverer implements DeviceDiscoverer, WifiP2pManager.PeerListListener {

    @NonNull
    private final WifiP2pManager wifiP2pManager;

    @NonNull
    private final WifiP2pManager.Channel channel;

    @Nullable
    private DiscoveryListener discoveryListener;

    /**
     * Constructs a new {@link WifiDirectDiscoverer}.
     *
     * @param context        the application context
     * @param wifiP2pManager the Wi-Fi P2P manager instance
     * @param channel        the Wi-Fi P2P channel
     */
    public WifiDirectDiscoverer(
            @NonNull Context context,
            @NonNull WifiP2pManager wifiP2pManager,
            @NonNull WifiP2pManager.Channel channel) {
        this.wifiP2pManager = wifiP2pManager;
        this.channel = channel;
    }

    @Override
    public void startDiscovery() {
        try {
            wifiP2pManager.discoverPeers(channel, new WifiP2pManager.ActionListener() {
                @Override
                public void onSuccess() {
                    // Discovery successfully started
                }

                @Override
                public void onFailure(int reason) {
                    // Handle failure if needed
                }
            });
        } catch (SecurityException e) {
            // Handle missing permissions gracefully
        }
    }

    @Override
    public void stopDiscovery() {
        wifiP2pManager.stopPeerDiscovery(channel, null);
    }

    @Override
    public void setDiscoveryListener(@NonNull DiscoveryListener listener) {
        this.discoveryListener = listener;
    }

    @Override
    public void onPeersAvailable(WifiP2pDeviceList peers) {
        if (discoveryListener == null) {
            return;
        }
        for (WifiP2pDevice device : peers.getDeviceList()) {
            discoveryListener.onDeviceFound(device.deviceAddress);
        }
    }
}
