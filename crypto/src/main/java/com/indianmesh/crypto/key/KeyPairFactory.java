/*
 * Copyright (c) 2026 Indian Mesh. All rights reserved.
 */
package com.indianmesh.crypto.key;

import androidx.annotation.NonNull;

import com.indianmesh.crypto.exception.CryptoException;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.ECGenParameterSpec;

/**
 * Utility class to generate raw Java KeyPairs outside of the keystore.
 * Useful for ephemeral keys.
 */
public final class KeyPairFactory {

    private static final String ALGORITHM_EC = "EC";
    private static final String CURVE_SECP256R1 = "secp256r1";

    private KeyPairFactory() {
        // Prevent instantiation
    }

    /**
     * Generates a new ephemeral EC KeyPair.
     *
     * @return the generated ephemeral KeyPair.
     * @throws CryptoException if key pair generation fails.
     */
    @NonNull
    public static KeyPair generateEphemeralKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM_EC);
            ECGenParameterSpec ecSpec = new ECGenParameterSpec(CURVE_SECP256R1);
            keyPairGenerator.initialize(ecSpec);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException e) {
            throw new CryptoException("Failed to generate ephemeral EC key pair", e);
        }
    }
}
