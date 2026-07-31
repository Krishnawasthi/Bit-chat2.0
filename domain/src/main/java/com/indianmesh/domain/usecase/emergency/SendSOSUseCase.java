/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.usecase.emergency;

import com.indianmesh.core.model.Result;
import com.indianmesh.domain.model.Message;
import com.indianmesh.domain.repository.MessageRepository;
import com.indianmesh.domain.repository.PeerRepository;
import com.indianmesh.domain.repository.UserRepository;

import java.util.concurrent.CompletableFuture;

/**
 * Use case for sending an SOS message.
 */
public class SendSOSUseCase {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final PeerRepository peerRepository;

    /**
     * Constructs a SendSOSUseCase.
     *
     * @param messageRepository the message repository
     * @param userRepository the user repository
     * @param peerRepository the peer repository
     */
    public SendSOSUseCase(MessageRepository messageRepository, UserRepository userRepository, PeerRepository peerRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.peerRepository = peerRepository;
    }

    /**
     * Executes the use case to send an SOS.
     *
     * @param emergencyMessage the emergency message
     * @param latitude the latitude
     * @param longitude the longitude
     * @return a CompletableFuture containing the result with the created message or an exception
     */
    public CompletableFuture<Result<Message, Exception>> execute(String emergencyMessage, double latitude, double longitude) {
        if (emergencyMessage == null || emergencyMessage.isEmpty()) {
            return CompletableFuture.completedFuture(Result.failure(new IllegalArgumentException("Emergency message cannot be empty")));
        }
        
        return CompletableFuture.completedFuture(Result.failure(new UnsupportedOperationException("Not yet implemented")));
    }
}
