/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.usecase.discovery;

import com.indianmesh.core.model.Result;
import com.indianmesh.domain.repository.UserRepository;

import java.util.concurrent.CompletableFuture;

/**
 * Use case for generating an invite code.
 */
public class GenerateInviteCodeUseCase {

    private final UserRepository userRepository;

    /**
     * Constructs a GenerateInviteCodeUseCase.
     *
     * @param userRepository the user repository
     */
    public GenerateInviteCodeUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Executes the use case to generate an invite code.
     *
     * @return a CompletableFuture containing the result with the generated invite code or an exception
     */
    public CompletableFuture<Result<String, Exception>> execute() {
        return CompletableFuture.completedFuture(Result.failure(new UnsupportedOperationException("Not yet implemented")));
    }
}
