/*
 * Copyright (c) 2026 Indian Mesh. All rights reserved.
 */
package com.indianmesh.network.wifi;

import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.indianmesh.network.ConnectionManager;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of {@link ConnectionManager} using Wi-Fi Direct.
 */
public class WifiDirectConnectionManager implements ConnectionManager {

    @NonNull
    private final WifiP2pManager wifiP2pManager;

    @NonNull
    private final WifiP2pManager.Channel channel;

    @NonNull
    private final ConcurrentHashMap<String, Socket> activeSockets;

    @Nullable
    private ServerSocket serverSocket;

    /**
     * Constructs a new {@link WifiDirectConnectionManager}.
     *
     * @param wifiP2pManager the Wi-Fi P2P manager instance
     * @param channel        the Wi-Fi P2P channel
     */
    public WifiDirectConnectionManager(
            @NonNull WifiP2pManager wifiP2pManager,
            @NonNull WifiP2pManager.Channel channel) {
        this.wifiP2pManager = wifiP2pManager;
        this.channel = channel;
        this.activeSockets = new ConcurrentHashMap<>();
    }

    @Override
    public void connect(@NonNull String deviceId) {
        WifiP2pConfig config = new WifiP2pConfig();
        config.deviceAddress = deviceId;
        try {
            wifiP2pManager.connect(channel, config, new WifiP2pManager.ActionListener() {
                @Override
                public void onSuccess() {
                    // Connection requested successfully
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
    public void disconnect(@NonNull String deviceId) {
        wifiP2pManager.removeGroup(channel, new WifiP2pManager.ActionListener() {
            @Override
            public void onSuccess() {
                // Disconnected
            }

            @Override
            public void onFailure(int reason) {
                // Failed to disconnect
            }
        });

        Socket socket = activeSockets.remove(deviceId);
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                // Ignore
            }
        }
    }

    @Override
    public void sendData(@NonNull String deviceId, @NonNull byte[] data) {
        Socket socket = activeSockets.get(deviceId);
        if (socket != null && socket.isConnected()) {
            try {
                OutputStream os = socket.getOutputStream();
                os.write(data);
                os.flush();
            } catch (IOException e) {
                // Handle IO error (e.g., connection lost)
            }
        }
    }

    /**
     * Sets the active socket for a given device after the connection is established.
     *
     * @param deviceId the device identifier
     * @param socket   the connected socket
     */
    public void setSocket(@NonNull String deviceId, @NonNull Socket socket) {
        activeSockets.put(deviceId, socket);
    }

    /**
     * Starts a server socket on the given port to accept incoming connections.
     *
     * @param port the port to listen on
     * @throws IOException if the server socket cannot be created
     */
    public void startServer(int port) throws IOException {
        if (serverSocket == null || serverSocket.isClosed()) {
            serverSocket = new ServerSocket(port);
            // In a real application, a background thread would accept connections
            // and populate the activeSockets map with the resulting client sockets.
        }
    }

    /**
     * Stops the server socket.
     */
    public void stopServer() {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                // Ignore
            }
            serverSocket = null;
        }
    }
}
