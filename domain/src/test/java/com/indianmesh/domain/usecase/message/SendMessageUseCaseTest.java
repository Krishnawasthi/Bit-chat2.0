/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.usecase.message;

import com.indianmesh.core.model.Result;
import com.indianmesh.domain.model.Message;
import com.indianmesh.domain.model.MessagePriority;
import com.indianmesh.domain.model.MessageType;
import com.indianmesh.domain.repository.ConversationRepository;
import com.indianmesh.domain.repository.MessageRepository;
import com.indianmesh.domain.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for SendMessageUseCase.
 */
public class SendMessageUseCaseTest {

    @Mock
    private MessageRepository messageRepository;
    @Mock
    private ConversationRepository conversationRepository;
    @Mock
    private UserRepository userRepository;

    private SendMessageUseCase useCase;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        useCase = new SendMessageUseCase(messageRepository, conversationRepository, userRepository);
    }

    @Test
    public void testEmptyConversationIdFails() throws Exception {
        Result<Message, Exception> result = useCase.execute("", "Hello", MessageType.TEXT, MessagePriority.NORMAL, null).get();
        assertTrue(result.isFailure());
    }

    @Test
    public void testEmptyContentFails() throws Exception {
        Result<Message, Exception> result = useCase.execute("conv1", "", MessageType.TEXT, MessagePriority.NORMAL, null).get();
        assertTrue(result.isFailure());
    }

    // Additional tests for successful creation would go here (mocking repositories).
}
