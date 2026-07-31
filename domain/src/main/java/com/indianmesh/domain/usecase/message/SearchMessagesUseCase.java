/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.usecase.message;

import com.indianmesh.core.model.Result;
import com.indianmesh.domain.model.Message;
import com.indianmesh.domain.repository.MessageRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Use case for searching messages.
 */
public class SearchMessagesUseCase {

    private final MessageRepository messageRepository;

    /**
     * Constructs a SearchMessagesUseCase.
     *
     * @param messageRepository the message repository
     */
    public SearchMessagesUseCase(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    /**
     * Executes the use case to search messages.
     *
     * @param query the search query
     * @return a CompletableFuture containing the result with a list of matching messages or an exception
     */
    public CompletableFuture<Result<List<Message>, Exception>> execute(String query) {
        if (query == null || query.trim().length() < 2) {
            return CompletableFuture.completedFuture(Result.failure(new IllegalArgumentException("Query must be at least 2 characters long")));
        }
        
        return messageRepository.searchMessages(query)
                .thenApply(Result::success)
                .exceptionally(ex -> Result.failure(new Exception(ex)));
    }
}
