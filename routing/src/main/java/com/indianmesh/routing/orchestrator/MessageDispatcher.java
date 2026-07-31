/*
 * Copyright (c) 2026 Indian Mesh. All rights reserved.
 */
package com.indianmesh.routing.orchestrator;

import androidx.annotation.NonNull;

import com.indianmesh.routing.protocol.RoutingProtocol;

/**
 * Central coordinator for message dispatching.
 * Determines if a payload is destined for this node or needs to be forwarded.
 */
public class MessageDispatcher {

    /**
     * Interface for cryptographic operations needed during dispatch.
     */
    public interface CryptoProcessor {
        /**
         * Decrypts the given payload.
         *
         * @param payload The raw payload to decrypt.
         */
        void decrypt(@NonNull byte[] payload);
    }

    /**
     * Interface for parsing message headers.
     */
    public interface HeaderParser {
        /**
         * Extracts the destination ID from a payload.
         *
         * @param payload The payload to parse.
         * @return The destination ID.
         */
        @NonNull
        String getDestinationId(@NonNull byte[] payload);
    }

    private final CryptoProcessor cryptoProcessor;
    private final RoutingProtocol routingProtocol;
    private final HeaderParser headerParser;
    private final String localNodeId;

    /**
     * Constructs a MessageDispatcher.
     *
     * @param cryptoProcessor The cryptographic processor.
     * @param routingProtocol The routing protocol implementation.
     * @param headerParser The parser to extract header info from payloads.
     * @param localNodeId The ID of the local node.
     */
    public MessageDispatcher(@NonNull CryptoProcessor cryptoProcessor, 
                             @NonNull RoutingProtocol routingProtocol, 
                             @NonNull HeaderParser headerParser,
                             @NonNull String localNodeId) {
        this.cryptoProcessor = cryptoProcessor;
        this.routingProtocol = routingProtocol;
        this.headerParser = headerParser;
        this.localNodeId = localNodeId;
    }

    /**
     * Dispatches the raw payload based on its intended destination.
     * 
     * If the payload is for this node, it is sent to Crypto to decrypt.
     * Otherwise, it is sent to the RoutingProtocol to be routed further.
     *
     * @param rawPayload The raw incoming byte payload.
     */
    public void dispatch(@NonNull byte[] rawPayload) {
        if (rawPayload == null || rawPayload.length == 0) {
            return;
        }
        
        String destinationId = headerParser.getDestinationId(rawPayload);
        boolean isForLocal = localNodeId.equals(destinationId);

        if (isForLocal) {
            cryptoProcessor.decrypt(rawPayload);
        } else {
            routingProtocol.routeMessage(rawPayload, destinationId);
        }
    }
}
