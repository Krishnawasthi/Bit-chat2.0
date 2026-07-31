/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.usecase.sync;

import com.indianmesh.core.model.Result;
import com.indianmesh.domain.repository.MessageRepository;

import java.util.concurrent.CompletableFuture;

/**
 * Use case for retrying failed messages.
 */
public class RetryFailedMessagesUseCase {

    private final MessageRepository messageRepository;

    /**
     * Constructs a RetryFailedMessagesUseCase.
     *
     * @param messageRepository the message repository
     */
    public RetryFailedMessagesUseCase(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    /**
     * Executes the use case to retry failed messages.
     *
     * @return a CompletableFuture containing the result with the count of retried messages or an exception
     */
    public CompletableFuture<Result<Integer, Exception>> execute() {
        return CompletableFuture.completedFuture(Result.failure(new UnsupportedOperationException("Not yet implemented")));
    }
}
