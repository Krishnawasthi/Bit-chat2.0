/*
 * Copyright (c) 2026 Indian Mesh. All rights reserved.
 */
package com.indianmesh.routing.protocol;

import androidx.annotation.NonNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An implementation of {@link RoutingProtocol} using an epidemic (store-and-forward) strategy.
 * If the destination is not the local node, it holds the message in a cache and attempts
 * to forward it to available peers.
 */
public final class EpidemicRoutingProtocol implements RoutingProtocol {

    private static final Logger LOGGER = Logger.getLogger(EpidemicRoutingProtocol.class.getName());

    @NonNull
    private final String localPeerId;

    @NonNull
    private final RoutingTableManager routingTableManager;

    @NonNull
    private final DestinationExtractor destinationExtractor;

    @NonNull
    private final MessageForwarder messageForwarder;

    /**
     * A thread-safe cache to store payload hashes that have already been processed or forwarded.
     */
    @NonNull
    private final Set<String> messageCache;

    /**
     * Constructs an {@link EpidemicRoutingProtocol}.
     *
     * @param localPeerId          The unique identifier of the local peer.
     * @param routingTableManager  The manager for the routing table.
     * @param destinationExtractor Extracts the destination ID from raw payloads.
     * @param messageForwarder     Responsible for transmitting the payload over the network.
     */
    public EpidemicRoutingProtocol(
            @NonNull String localPeerId,
            @NonNull RoutingTableManager routingTableManager,
            @NonNull DestinationExtractor destinationExtractor,
            @NonNull MessageForwarder messageForwarder) {
        this.localPeerId = localPeerId;
        this.routingTableManager = routingTableManager;
        this.destinationExtractor = destinationExtractor;
        this.messageForwarder = messageForwarder;
        this.messageCache = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    /**
     * Handles an incoming message payload. If the message is intended for this peer,
     * it processes it. Otherwise, it stores and forwards it.
     *
     * @param payload  The raw byte array representing the message data.
     * @param senderId The unique identifier of the peer who sent the message.
     */
    @Override
    public void handleIncomingMessage(@NonNull byte[] payload, @NonNull String senderId) {
        String messageHash = hashPayload(payload);

        if (!messageCache.add(messageHash)) {
            LOGGER.log(Level.FINE, "Message already seen, discarding. Hash: {0}", messageHash);
            return;
        }

        LOGGER.log(Level.INFO, "Received incoming message from {0}", senderId);
        
        String destinationId = destinationExtractor.extract(payload);

        if (localPeerId.equals(destinationId)) {
            processLocalMessage(payload);
        } else {
            routeMessage(payload, destinationId);
        }
    }

    /**
     * Routes a message payload to the specified destination. Uses the routing table
     * to determine the next hop. If a route exists, forwards it; otherwise, may broadcast.
     *
     * @param payload       The raw byte array representing the message data.
     * @param destinationId The unique identifier of the peer intended to receive the message.
     */
    @Override
    public void routeMessage(@NonNull byte[] payload, @NonNull String destinationId) {
        if (localPeerId.equals(destinationId)) {
            LOGGER.log(Level.WARNING, "Attempted to route a message to self.");
            return;
        }

        String nextHopId = routingTableManager.getNextHop(destinationId);
        if (nextHopId != null) {
            LOGGER.log(Level.INFO, "Forwarding message to known next hop: {0}", nextHopId);
            messageForwarder.forward(payload, nextHopId);
        } else {
            LOGGER.log(Level.INFO, "No known route to {0}. Broadcasting to available peers.", destinationId);
            messageForwarder.broadcast(payload);
        }
    }

    /**
     * Processes a message that is intended for the local peer.
     *
     * @param payload The raw byte array representing the message data.
     */
    private void processLocalMessage(@NonNull byte[] payload) {
        LOGGER.log(Level.INFO, "Processing message intended for local peer.");
        messageForwarder.deliverToApplication(payload);
    }

    /**
     * Generates a hash for the message payload to uniquely identify it in the cache.
     *
     * @param payload The raw byte array representing the message data.
     * @return A hexadecimal string representation of the hash.
     */
    @NonNull
    private String hashPayload(@NonNull byte[] payload) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(payload);
            StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.log(Level.SEVERE, "SHA-256 not found, falling back to basic hashcode", e);
            return String.valueOf(java.util.Arrays.hashCode(payload));
        }
    }

    /**
     * Contract for extracting the destination identifier from a raw message payload.
     */
    public interface DestinationExtractor {
        /**
         * Extracts the destination ID.
         *
         * @param payload The raw byte array representing the message data.
         * @return The extracted destination identifier.
         */
        @NonNull
        String extract(@NonNull byte[] payload);
    }

    /**
     * Contract for forwarding or delivering messages over the network or to the local application.
     */
    public interface MessageForwarder {
        /**
         * Forwards a message to a specific peer.
         *
         * @param payload The raw byte array.
         * @param peerId  The target peer ID.
         */
        void forward(@NonNull byte[] payload, @NonNull String peerId);

        /**
         * Broadcasts a message to all connected peers.
         *
         * @param payload The raw byte array.
         */
        void broadcast(@NonNull byte[] payload);

        /**
         * Delivers the payload to the local application layer.
         *
         * @param payload The raw byte array.
         */
        void deliverToApplication(@NonNull byte[] payload);
    }
}
