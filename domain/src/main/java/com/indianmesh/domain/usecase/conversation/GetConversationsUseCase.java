/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.usecase.conversation;

import com.indianmesh.core.model.Result;
import com.indianmesh.domain.model.Conversation;
import com.indianmesh.domain.repository.ConversationRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Use case for getting all conversations.
 */
public class GetConversationsUseCase {

    private final ConversationRepository conversationRepository;

    /**
     * Constructs a GetConversationsUseCase.
     *
     * @param conversationRepository the conversation repository
     */
    public GetConversationsUseCase(ConversationRepository conversationRepository) {
        this.conversationRepository = conversationRepository;
    }

    /**
     * Executes the use case to get all conversations.
     *
     * @return a CompletableFuture containing the result with a list of conversations or an exception
     */
    public CompletableFuture<Result<List<Conversation>, Exception>> execute() {
        return conversationRepository.getAllConversations()
                .thenApply(v -> Result.<List<Conversation>, Exception>success(v))
                .exceptionally(ex -> Result.failure(ex instanceof Exception ? (Exception) ex : new Exception(ex)));
    }
}
