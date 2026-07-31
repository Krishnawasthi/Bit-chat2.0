/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.usecase.discovery;

import com.indianmesh.core.model.Result;
import com.indianmesh.core.model.NodeId;
import com.indianmesh.domain.repository.PeerRepository;
import com.indianmesh.domain.repository.UserRepository;

import java.util.concurrent.CompletableFuture;

/**
 * Use case for sending a friend request.
 */
public class SendFriendRequestUseCase {

    private final PeerRepository peerRepository;
    private final UserRepository userRepository;

    /**
     * Constructs a SendFriendRequestUseCase.
     *
     * @param peerRepository the peer repository
     * @param userRepository the user repository
     */
    public SendFriendRequestUseCase(PeerRepository peerRepository, UserRepository userRepository) {
        this.peerRepository = peerRepository;
        this.userRepository = userRepository;
    }

    /**
     * Executes the use case to send a friend request.
     *
     * @param targetNodeId the node ID of the target peer
     * @return a CompletableFuture containing the result
     */
    public CompletableFuture<Result<Void, Exception>> execute(NodeId targetNodeId) {
        if (targetNodeId == null) {
            return CompletableFuture.completedFuture(Result.failure(new IllegalArgumentException("Target node ID cannot be null")));
        }
        
        return CompletableFuture.completedFuture(Result.failure(new UnsupportedOperationException("Not yet implemented")));
    }
}
