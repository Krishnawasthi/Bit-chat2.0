/*
 * Copyright (c) 2026 Indian Mesh. All rights reserved.
 */

package com.indianmesh.crypto.signature;

import androidx.annotation.NonNull;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Service interface for generating and verifying digital signatures.
 */
public interface SignatureService {

    /**
     * Signs the provided data using the specified private key.
     *
     * @param data the data to sign
     * @param privateKey the private key to use for signing
     * @return the digital signature
     */
    @NonNull
    byte[] sign(@NonNull byte[] data, @NonNull PrivateKey privateKey);

    /**
     * Verifies the provided signature against the data using the specified public key.
     *
     * @param data the original data that was signed
     * @param signature the digital signature to verify
     * @param publicKey the public key corresponding to the signer's private key
     * @return true if the signature is valid, false otherwise
     */
    boolean verify(@NonNull byte[] data, @NonNull byte[] signature, @NonNull PublicKey publicKey);
}
