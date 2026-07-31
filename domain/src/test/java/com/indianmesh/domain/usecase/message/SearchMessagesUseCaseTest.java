/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.usecase.message;

import com.indianmesh.core.model.Result;
import com.indianmesh.domain.model.Message;
import com.indianmesh.domain.repository.MessageRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Tests for SearchMessagesUseCase.
 */
public class SearchMessagesUseCaseTest {

    @Mock
    private MessageRepository messageRepository;

    private SearchMessagesUseCase useCase;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        useCase = new SearchMessagesUseCase(messageRepository);
    }

    @Test
    public void testSuccessfulSearch() throws Exception {
        List<Message> expectedList = new ArrayList<>();
        when(messageRepository.searchMessages(anyString()))
                .thenReturn(CompletableFuture.completedFuture(expectedList));

        Result<List<Message>, Exception> result = useCase.execute("hello").get();
        assertTrue(result.isSuccess());
        assertEquals(expectedList, result.getOrNull());
    }

    @Test
    public void testEmptyQueryRejected() throws Exception {
        Result<List<Message>, Exception> result = useCase.execute("").get();
        assertTrue(result.isFailure());
    }

    @Test
    public void testTooShortQueryRejected() throws Exception {
        Result<List<Message>, Exception> result = useCase.execute("a").get();
        assertTrue(result.isFailure());
    }
}
