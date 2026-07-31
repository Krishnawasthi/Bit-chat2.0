/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.repository;

import com.indianmesh.core.model.NodeId;
import com.indianmesh.domain.model.Peer;
import com.indianmesh.domain.model.TransportType;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Repository interface for managing peers.
 */
public interface PeerRepository {

    /**
     * Saves a peer.
     *
     * @param peer the peer to save
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> savePeer(Peer peer);

    /**
     * Retrieves a peer by their node ID.
     *
     * @param nodeId the node ID of the peer
     * @return a CompletableFuture containing the peer
     */
    CompletableFuture<Peer> getPeerByNodeId(NodeId nodeId);

    /**
     * Retrieves a list of currently connected peers.
     *
     * @return a CompletableFuture containing the list of connected peers
     */
    CompletableFuture<List<Peer>> getConnectedPeers();

    /**
     * Retrieves a list of nearby peers.
     *
     * @return a CompletableFuture containing the list of nearby peers
     */
    CompletableFuture<List<Peer>> getNearbyPeers();

    /**
     * Retrieves a list of trusted peers.
     *
     * @return a CompletableFuture containing the list of trusted peers
     */
    CompletableFuture<List<Peer>> getTrustedPeers();

    /**
     * Updates the connection state of a peer.
     *
     * @param nodeId the node ID of the peer
     * @param connected true if connected, false otherwise
     * @param transport the transport type
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> updateConnectionState(NodeId nodeId, boolean connected, TransportType transport);

    /**
     * Updates the last seen timestamp of a peer.
     *
     * @param nodeId the node ID of the peer
     * @param timestamp the timestamp
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> updateLastSeen(NodeId nodeId, long timestamp);

    /**
     * Blocks or unblocks a peer.
     *
     * @param nodeId the node ID of the peer
     * @param blocked true to block, false to unblock
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> blockPeer(NodeId nodeId, boolean blocked);

    /**
     * Trusts or untrusts a peer.
     *
     * @param nodeId the node ID of the peer
     * @param trusted true to trust, false to untrust
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> trustPeer(NodeId nodeId, boolean trusted);
}
