/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.usecase.conversation;

import com.indianmesh.core.model.Result;
import com.indianmesh.core.model.MessageId;
import com.indianmesh.domain.repository.MessageRepository;

import java.util.concurrent.CompletableFuture;

/**
 * Use case for pinning or unpinning a message.
 */
public class PinMessageUseCase {

    private final MessageRepository messageRepository;

    /**
     * Constructs a PinMessageUseCase.
     *
     * @param messageRepository the message repository
     */
    public PinMessageUseCase(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    /**
     * Executes the use case to pin or unpin a message.
     *
     * @param messageId the ID of the message
     * @param pinned true to pin, false to unpin
     * @return a CompletableFuture containing the result
     */
    public CompletableFuture<Result<Void, Exception>> execute(MessageId messageId, boolean pinned) {
        if (messageId == null) {
            return CompletableFuture.completedFuture(Result.failure(new IllegalArgumentException("Message ID cannot be null")));
        }
        
        return CompletableFuture.completedFuture(Result.failure(new UnsupportedOperationException("Not yet implemented")));
    }
}
