/*
 * Copyright (c) 2026 Indian Mesh. All rights reserved.
 */

package com.indianmesh.crypto.symmetric;

import androidx.annotation.NonNull;

/**
 * Service interface for symmetric encryption operations.
 */
public interface EncryptionService {
    
    /**
     * Encrypts the provided plaintext using the given key.
     *
     * @param plaintext the data to encrypt
     * @param key the symmetric encryption key
     * @return the encrypted ciphertext
     */
    @NonNull
    byte[] encrypt(@NonNull byte[] plaintext, @NonNull byte[] key);

    /**
     * Decrypts the provided ciphertext using the given key.
     *
     * @param ciphertext the data to decrypt
     * @param key the symmetric encryption key
     * @return the decrypted plaintext
     */
    @NonNull
    byte[] decrypt(@NonNull byte[] ciphertext, @NonNull byte[] key);
}
