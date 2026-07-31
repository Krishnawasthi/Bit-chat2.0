/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.repository;

import com.indianmesh.core.model.NodeId;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Repository interface for managing cryptographic keys.
 */
public interface KeyRepository {

    /**
     * Saves a key pair.
     *
     * @param keyId the ID of the key
     * @param keyType the type of the key
     * @param publicKey the public key bytes
     * @param privateKeyAlias the alias for the private key
     * @param expiresAt the expiration timestamp
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> saveKeyPair(String keyId, String keyType, byte[] publicKey, String privateKeyAlias, long expiresAt);

    /**
     * Retrieves a public key.
     *
     * @param keyId the ID of the key
     * @return a CompletableFuture containing the public key bytes
     */
    CompletableFuture<byte[]> getPublicKey(String keyId);

    /**
     * Retrieves a private key alias.
     *
     * @param keyId the ID of the key
     * @return a CompletableFuture containing the private key alias
     */
    CompletableFuture<String> getPrivateKeyAlias(String keyId);

    /**
     * Retrieves a public key for a specific node.
     *
     * @param nodeId the node ID
     * @param keyType the type of the key
     * @return a CompletableFuture containing the public key bytes
     */
    CompletableFuture<byte[]> getPublicKeyForNode(NodeId nodeId, String keyType);

    /**
     * Retrieves expired key IDs.
     *
     * @param currentTimeMillis the current timestamp
     * @return a CompletableFuture containing the list of expired key IDs
     */
    CompletableFuture<List<String>> getExpiredKeyIds(long currentTimeMillis);

    /**
     * Deletes a key.
     *
     * @param keyId the ID of the key to delete
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> deleteKey(String keyId);

    /**
     * Saves a cryptographic session.
     *
     * @param sessionId the ID of the session
     * @param localNodeId the local node ID
     * @param remoteNodeId the remote node ID
     * @param rootKey the root key
     * @param chainKeySend the chain key for sending
     * @param chainKeyRecv the chain key for receiving
     * @param sendCounter the send counter
     * @param recvCounter the receive counter
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> saveSession(String sessionId, NodeId localNodeId, NodeId remoteNodeId, byte[] rootKey, byte[] chainKeySend, byte[] chainKeyRecv, int sendCounter, int recvCounter);

    /**
     * Updates an existing cryptographic session.
     *
     * @param sessionId the ID of the session
     * @param chainKeySend the chain key for sending
     * @param chainKeyRecv the chain key for receiving
     * @param sendCounter the send counter
     * @param recvCounter the receive counter
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> updateSession(String sessionId, byte[] chainKeySend, byte[] chainKeyRecv, int sendCounter, int recvCounter);
}
