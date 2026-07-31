/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.usecase.message;

import com.indianmesh.core.model.Result;
import com.indianmesh.domain.model.Message;
import com.indianmesh.domain.repository.ConversationRepository;
import com.indianmesh.domain.repository.MessageRepository;
import com.indianmesh.domain.repository.UserRepository;

import java.util.concurrent.CompletableFuture;

/**
 * Use case for receiving a message.
 */
public class ReceiveMessageUseCase {

    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;

    /**
     * Constructs a ReceiveMessageUseCase.
     *
     * @param messageRepository the message repository
     * @param conversationRepository the conversation repository
     * @param userRepository the user repository
     */
    public ReceiveMessageUseCase(MessageRepository messageRepository, ConversationRepository conversationRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.conversationRepository = conversationRepository;
        this.userRepository = userRepository;
    }

    /**
     * Executes the use case to process an incoming message.
     *
     * @param incomingMessage the incoming message
     * @return a CompletableFuture containing the result with the processed message or an exception
     */
    public CompletableFuture<Result<Message, Exception>> execute(Message incomingMessage) {
        if (incomingMessage == null) {
            return CompletableFuture.completedFuture(Result.failure(new IllegalArgumentException("Message cannot be null")));
        }
        
        return CompletableFuture.completedFuture(Result.failure(new UnsupportedOperationException("Not yet implemented")));
    }
}
