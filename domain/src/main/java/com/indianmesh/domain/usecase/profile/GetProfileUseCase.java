/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.usecase.profile;

import com.indianmesh.core.model.Result;
import com.indianmesh.core.model.NodeId;
import com.indianmesh.domain.model.UserProfile;
import com.indianmesh.domain.repository.UserRepository;

import java.util.concurrent.CompletableFuture;

/**
 * Use case for retrieving a user profile.
 */
public class GetProfileUseCase {

    private final UserRepository userRepository;

    /**
     * Constructs a GetProfileUseCase.
     *
     * @param userRepository the user repository
     */
    public GetProfileUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Executes the use case to retrieve a user profile.
     *
     * @param nodeId the node ID of the user
     * @return a CompletableFuture containing the result with the user profile or an exception
     */
    public CompletableFuture<Result<UserProfile, Exception>> execute(NodeId nodeId) {
        if (nodeId == null) {
            return CompletableFuture.completedFuture(Result.failure(new IllegalArgumentException("Node ID cannot be null")));
        }
        
        return CompletableFuture.completedFuture(Result.failure(new UnsupportedOperationException("Not yet implemented")));
    }
}
