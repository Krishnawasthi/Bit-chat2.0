/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.repository;

import com.indianmesh.core.model.NodeId;
import com.indianmesh.domain.model.User;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Repository interface for managing users.
 */
public interface UserRepository {

    /**
     * Retrieves the current self user profile.
     *
     * @return a CompletableFuture containing the self user
     */
    CompletableFuture<User> getSelf();

    /**
     * Saves the current self user profile.
     *
     * @param user the user profile to save
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> saveSelf(User user);

    /**
     * Retrieves a user by their node ID.
     *
     * @param nodeId the node ID of the user
     * @return a CompletableFuture containing the user
     */
    CompletableFuture<User> getUserByNodeId(NodeId nodeId);

    /**
     * Saves a user profile.
     *
     * @param user the user profile to save
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> saveUser(User user);

    /**
     * Retrieves a list of all known users.
     *
     * @return a CompletableFuture containing the list of all known users
     */
    CompletableFuture<List<User>> getAllKnownUsers();
}
