/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.usecase.message;

import com.indianmesh.core.model.Result;
import com.indianmesh.core.model.MessageId;
import com.indianmesh.domain.model.Reaction;
import com.indianmesh.domain.repository.MessageRepository;

import java.util.concurrent.CompletableFuture;

/**
 * Use case for reacting to a message.
 */
public class ReactToMessageUseCase {

    private final MessageRepository messageRepository;

    /**
     * Constructs a ReactToMessageUseCase.
     *
     * @param messageRepository the message repository
     */
    public ReactToMessageUseCase(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    /**
     * Executes the use case to react to a message.
     *
     * @param messageId the ID of the message
     * @param emoji the emoji string
     * @return a CompletableFuture containing the result with the created Reaction or an exception
     */
    public CompletableFuture<Result<Reaction, Exception>> execute(MessageId messageId, String emoji) {
        if (messageId == null) {
            return CompletableFuture.completedFuture(Result.failure(new IllegalArgumentException("Message ID cannot be null")));
        }
        if (emoji == null || emoji.isEmpty()) {
            return CompletableFuture.completedFuture(Result.failure(new IllegalArgumentException("Emoji cannot be empty")));
        }
        
        return CompletableFuture.completedFuture(Result.failure(new UnsupportedOperationException("Not yet implemented")));
    }
}
