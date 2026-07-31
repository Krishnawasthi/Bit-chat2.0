/*
 * Copyright (c) 2026 Indian Mesh. All rights reserved.
 */

package com.indianmesh.crypto.signature;

import androidx.annotation.NonNull;
import com.indianmesh.crypto.exception.CryptoException;

import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

/**
 * An implementation of {@link SignatureService} that uses the SHA256withECDSA
 * algorithm for signing and verifying data.
 */
public class ECDSASignatureService implements SignatureService {

    private static final String SIGNATURE_ALGORITHM = "SHA256withECDSA";

    /**
     * Constructs a new ECDSASignatureService.
     */
    public ECDSASignatureService() {
        // Default constructor
    }

    @NonNull
    @Override
    public byte[] sign(@NonNull byte[] data, @NonNull PrivateKey privateKey) {
        try {
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initSign(privateKey);
            signature.update(data);
            return signature.sign();
        } catch (GeneralSecurityException e) {
            throw new CryptoException("Failed to sign data using ECDSA", e);
        }
    }

    @Override
    public boolean verify(@NonNull byte[] data, @NonNull byte[] signatureBytes, @NonNull PublicKey publicKey) {
        try {
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(publicKey);
            signature.update(data);
            return signature.verify(signatureBytes);
        } catch (GeneralSecurityException e) {
            throw new CryptoException("Failed to verify signature using ECDSA", e);
        }
    }
}
