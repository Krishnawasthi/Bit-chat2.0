/*
 * Copyright (c) 2026 Indian Mesh. All rights reserved.
 */
package com.indianmesh.crypto.exception;

import androidx.annotation.NonNull;

/**
 * Custom unchecked exception for cryptographic operations.
 */
public class CryptoException extends RuntimeException {

    /**
     * Constructs a new CryptoException with the specified detail message.
     *
     * @param message the detail message.
     */
    public CryptoException(@NonNull String message) {
        super(message);
    }

    /**
     * Constructs a new CryptoException with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause the cause of the exception.
     */
    public CryptoException(@NonNull String message, @NonNull Throwable cause) {
        super(message, cause);
    }
}
