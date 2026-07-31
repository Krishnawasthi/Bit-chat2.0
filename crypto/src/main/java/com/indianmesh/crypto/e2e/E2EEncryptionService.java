/*
 * Copyright (c) 2026 Indian Mesh. All rights reserved.
 */

package com.indianmesh.crypto.e2e;

import androidx.annotation.NonNull;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Service interface for End-to-End (E2E) encryption key agreement.
 */
public interface E2EEncryptionService {

    /**
     * Computes a shared secret using a local private key and a peer's public key.
     *
     * @param privateKey the local private key
     * @param peerPublicKey the peer's public key
     * @return the computed shared secret bytes
     */
    @NonNull
    byte[] computeSharedSecret(@NonNull PrivateKey privateKey, @NonNull PublicKey peerPublicKey);
}
