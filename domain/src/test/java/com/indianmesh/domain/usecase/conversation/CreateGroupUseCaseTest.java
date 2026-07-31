/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.usecase.conversation;

import com.indianmesh.core.model.Result;
import com.indianmesh.domain.model.Group;
import com.indianmesh.core.model.NodeId;
import com.indianmesh.domain.repository.ConversationRepository;
import com.indianmesh.domain.repository.GroupRepository;
import com.indianmesh.domain.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertTrue;

/**
 * Tests for CreateGroupUseCase.
 */
public class CreateGroupUseCaseTest {

    @Mock
    private GroupRepository groupRepository;
    @Mock
    private ConversationRepository conversationRepository;
    @Mock
    private UserRepository userRepository;

    private CreateGroupUseCase useCase;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        useCase = new CreateGroupUseCase(groupRepository, conversationRepository, userRepository);
    }

    @Test
    public void testEmptyNameRejected() throws Exception {
        List<NodeId> members = new ArrayList<>();
        Result<Group, Exception> result = useCase.execute("", "desc", members).get();
        assertTrue(result.isFailure());
    }

    @Test
    public void testTooManyMembersRejected() throws Exception {
        List<NodeId> members = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            members.add(new NodeId("user" + i));
        }
        Result<Group, Exception> result = useCase.execute("Group", "desc", members).get();
        assertTrue(result.isFailure());
    }
}
