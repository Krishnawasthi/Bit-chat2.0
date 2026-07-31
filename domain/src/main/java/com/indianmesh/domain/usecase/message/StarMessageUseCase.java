/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.usecase.message;

import com.indianmesh.core.model.Result;
import com.indianmesh.core.model.MessageId;
import com.indianmesh.domain.repository.MessageRepository;

import java.util.concurrent.CompletableFuture;

/**
 * Use case for starring or unstarring a message.
 */
public class StarMessageUseCase {

    private final MessageRepository messageRepository;

    /**
     * Constructs a StarMessageUseCase.
     *
     * @param messageRepository the message repository
     */
    public StarMessageUseCase(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    /**
     * Executes the use case to star or unstar a message.
     *
     * @param messageId the ID of the message
     * @param starred true to star, false to unstar
     * @return a CompletableFuture containing the result
     */
    public CompletableFuture<Result<Void, Exception>> execute(MessageId messageId, boolean starred) {
        if (messageId == null) {
            return CompletableFuture.completedFuture(Result.failure(new IllegalArgumentException("Message ID cannot be null")));
        }
        
        return messageRepository.starMessage(messageId, starred)
                .thenApply(v -> Result.<Void, Exception>success(v))
                .exceptionally(ex -> Result.failure(ex instanceof Exception ? (Exception) ex : new Exception(ex)));
    }
}
