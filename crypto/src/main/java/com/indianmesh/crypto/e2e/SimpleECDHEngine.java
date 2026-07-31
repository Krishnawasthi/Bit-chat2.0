/*
 * Copyright (c) 2026 Indian Mesh. All rights reserved.
 */

package com.indianmesh.crypto.e2e;

import androidx.annotation.NonNull;
import com.indianmesh.crypto.exception.CryptoException;

import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.KeyAgreement;

/**
 * An implementation of {@link E2EEncryptionService} using the Elliptic Curve
 * Diffie-Hellman (ECDH) key agreement protocol.
 */
public class SimpleECDHEngine implements E2EEncryptionService {

    private static final String KEY_AGREEMENT_ALGORITHM = "ECDH";

    /**
     * Constructs a new SimpleECDHEngine.
     */
    public SimpleECDHEngine() {
        // Default constructor
    }

    @NonNull
    @Override
    public byte[] computeSharedSecret(@NonNull PrivateKey privateKey, @NonNull PublicKey peerPublicKey) {
        try {
            KeyAgreement keyAgreement = KeyAgreement.getInstance(KEY_AGREEMENT_ALGORITHM);
            keyAgreement.init(privateKey);
            keyAgreement.doPhase(peerPublicKey, true);
            return keyAgreement.generateSecret();
        } catch (GeneralSecurityException e) {
            throw new CryptoException("Failed to compute shared secret using ECDH", e);
        }
    }
}
