/*
 * Copyright (c) 2026 Indian Mesh. All rights reserved.
 */
package com.indianmesh.crypto.key;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.security.KeyPair;

/**
 * Interface defining operations for key management.
 */
public interface KeyManager {

    /**
     * Generates a new KeyPair with the given alias.
     *
     * @param alias the alias to be used for the key pair in the store.
     */
    void generateKeyPair(@NonNull String alias);

    /**
     * Retrieves the KeyPair associated with the given alias.
     *
     * @param alias the alias of the key pair.
     * @return the KeyPair if found, or null otherwise.
     */
    @Nullable
    KeyPair getKeyPair(@NonNull String alias);

    /**
     * Deletes the key associated with the given alias.
     *
     * @param alias the alias of the key to delete.
     */
    void deleteKey(@NonNull String alias);

    /**
     * Retrieves the public key bytes for the given alias.
     *
     * @param alias the alias of the key.
     * @return a byte array representing the public key, or null if not found.
     */
    @Nullable
    byte[] getPublicKeyBytes(@NonNull String alias);
}
