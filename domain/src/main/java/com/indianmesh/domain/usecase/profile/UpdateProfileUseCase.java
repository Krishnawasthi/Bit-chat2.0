/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.usecase.profile;

import com.indianmesh.core.model.Result;
import com.indianmesh.domain.model.User;
import com.indianmesh.domain.repository.UserRepository;

import java.util.concurrent.CompletableFuture;

/**
 * Use case for updating the user profile.
 */
public class UpdateProfileUseCase {

    private final UserRepository userRepository;

    /**
     * Constructs an UpdateProfileUseCase.
     *
     * @param userRepository the user repository
     */
    public UpdateProfileUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Executes the use case to update the profile.
     *
     * @param displayName the display name
     * @param statusText the status text
     * @param about the about text
     * @param avatar the avatar bytes
     * @return a CompletableFuture containing the result with the updated User or an exception
     */
    public CompletableFuture<Result<User, Exception>> execute(String displayName, String statusText, String about, byte[] avatar) {
        if (displayName == null || displayName.trim().isEmpty()) {
            return CompletableFuture.completedFuture(Result.failure(new IllegalArgumentException("Display name cannot be empty")));
        }
        
        return CompletableFuture.completedFuture(Result.failure(new UnsupportedOperationException("Not yet implemented")));
    }
}
