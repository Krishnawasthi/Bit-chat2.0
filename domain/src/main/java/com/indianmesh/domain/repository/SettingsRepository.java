/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.repository;

import java.util.concurrent.CompletableFuture;

/**
 * Repository interface for managing settings.
 */
public interface SettingsRepository {

    /**
     * Retrieves a string value.
     *
     * @param key the key
     * @param defaultValue the default value
     * @return a CompletableFuture containing the value
     */
    CompletableFuture<String> getString(String key, String defaultValue);

    /**
     * Stores a string value.
     *
     * @param key the key
     * @param value the value to store
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> putString(String key, String value);

    /**
     * Retrieves a boolean value.
     *
     * @param key the key
     * @param defaultValue the default value
     * @return a CompletableFuture containing the value
     */
    CompletableFuture<Boolean> getBoolean(String key, boolean defaultValue);

    /**
     * Stores a boolean value.
     *
     * @param key the key
     * @param value the value to store
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> putBoolean(String key, boolean value);

    /**
     * Retrieves an integer value.
     *
     * @param key the key
     * @param defaultValue the default value
     * @return a CompletableFuture containing the value
     */
    CompletableFuture<Integer> getInt(String key, int defaultValue);

    /**
     * Stores an integer value.
     *
     * @param key the key
     * @param value the value to store
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> putInt(String key, int value);

    /**
     * Retrieves a long value.
     *
     * @param key the key
     * @param defaultValue the default value
     * @return a CompletableFuture containing the value
     */
    CompletableFuture<Long> getLong(String key, long defaultValue);

    /**
     * Stores a long value.
     *
     * @param key the key
     * @param value the value to store
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> putLong(String key, long value);

    /**
     * Removes a value by its key.
     *
     * @param key the key
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> remove(String key);

    /**
     * Clears all settings.
     *
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> clear();
}
