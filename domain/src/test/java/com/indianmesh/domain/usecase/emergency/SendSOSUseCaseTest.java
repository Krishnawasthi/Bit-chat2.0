/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.usecase.emergency;

import com.indianmesh.core.model.Result;
import com.indianmesh.domain.model.Message;
import com.indianmesh.domain.repository.MessageRepository;
import com.indianmesh.domain.repository.PeerRepository;
import com.indianmesh.domain.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertTrue;

/**
 * Tests for SendSOSUseCase.
 */
public class SendSOSUseCaseTest {

    @Mock
    private MessageRepository messageRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PeerRepository peerRepository;

    private SendSOSUseCase useCase;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        useCase = new SendSOSUseCase(messageRepository, userRepository, peerRepository);
    }

    @Test
    public void testEmptyMessageRejected() throws Exception {
        Result<Message, Exception> result = useCase.execute("", 0.0, 0.0).get();
        assertTrue(result.isFailure());
    }

    // Additional tests for SOS creation would go here.
}
