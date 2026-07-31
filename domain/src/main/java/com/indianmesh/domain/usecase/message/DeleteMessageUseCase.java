/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.usecase.message;

import com.indianmesh.core.model.Result;
import com.indianmesh.core.model.MessageId;
import com.indianmesh.domain.repository.MessageRepository;

import java.util.concurrent.CompletableFuture;

/**
 * Use case for deleting a message.
 */
public class DeleteMessageUseCase {

    private final MessageRepository messageRepository;

    /**
     * Constructs a DeleteMessageUseCase.
     *
     * @param messageRepository the message repository
     */
    public DeleteMessageUseCase(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    /**
     * Executes the use case to delete a message.
     *
     * @param messageId the ID of the message to delete
     * @param forEveryone true if the message should be deleted for everyone
     * @return a CompletableFuture containing the result
     */
    public CompletableFuture<Result<Void, Exception>> execute(MessageId messageId, boolean forEveryone) {
        if (messageId == null) {
            return CompletableFuture.completedFuture(Result.failure(new IllegalArgumentException("Message ID cannot be null")));
        }
        
        return messageRepository.deleteMessage(messageId, forEveryone)
                .thenApply(v -> Result.<Void, Exception>success(v))
                .exceptionally(ex -> Result.failure(ex instanceof Exception ? (Exception) ex : new Exception(ex)));
    }
}
