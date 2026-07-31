/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.usecase.message;

import com.indianmesh.core.model.Result;
import com.indianmesh.core.model.MessageId;
import com.indianmesh.domain.repository.MessageRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for DeleteMessageUseCase.
 */
public class DeleteMessageUseCaseTest {

    @Mock
    private MessageRepository messageRepository;

    private DeleteMessageUseCase useCase;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        useCase = new DeleteMessageUseCase(messageRepository);
    }

    @Test
    public void testSuccessfulDeletion() throws Exception {
        when(messageRepository.deleteMessage(any(MessageId.class), anyBoolean()))
                .thenReturn(CompletableFuture.completedFuture(null));

        MessageId id = new MessageId("msg1");
        Result<Void, Exception> result = useCase.execute(id, false).get();

        assertTrue(result.isSuccess());
        verify(messageRepository).deleteMessage(id, false);
    }

    @Test
    public void testDeletionForEveryone() throws Exception {
        when(messageRepository.deleteMessage(any(MessageId.class), anyBoolean()))
                .thenReturn(CompletableFuture.completedFuture(null));

        MessageId id = new MessageId("msg2");
        Result<Void, Exception> result = useCase.execute(id, true).get();

        assertTrue(result.isSuccess());
        verify(messageRepository).deleteMessage(id, true);
    }
}
