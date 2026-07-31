/*
 * Copyright (c) 2026 Indian Mesh. All rights reserved.
 */
package com.indianmesh.crypto.key;

import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.indianmesh.crypto.exception.CryptoException;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

/**
 * Implementation of KeyManager using the AndroidKeyStore provider.
 */
public class AndroidKeyStoreManager implements KeyManager {

    private static final String ANDROID_KEYSTORE = "AndroidKeyStore";
    
    private final KeyStore keyStore;

    /**
     * Constructs a new AndroidKeyStoreManager and initializes the KeyStore.
     * 
     * @throws CryptoException if the AndroidKeyStore cannot be initialized.
     */
    public AndroidKeyStoreManager() {
        try {
            keyStore = KeyStore.getInstance(ANDROID_KEYSTORE);
            keyStore.load(null);
        } catch (KeyStoreException | CertificateException | IOException | NoSuchAlgorithmException e) {
            throw new CryptoException("Failed to initialize AndroidKeyStore", e);
        }
    }

    /**
     * Generates a new KeyPair with the given alias within the Android KeyStore.
     *
     * @param alias the alias to be used for the key pair in the store.
     * @throws CryptoException if generating the key pair fails.
     */
    @Override
    public void generateKeyPair(@NonNull String alias) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(
                    KeyProperties.KEY_ALGORITHM_EC, ANDROID_KEYSTORE);
            
            KeyGenParameterSpec spec = new KeyGenParameterSpec.Builder(
                    alias,
                    KeyProperties.PURPOSE_SIGN | KeyProperties.PURPOSE_VERIFY)
                    .setDigests(KeyProperties.DIGEST_SHA256, KeyProperties.DIGEST_SHA512)
                    .build();
            
            keyPairGenerator.initialize(spec);
            keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException | NoSuchProviderException | InvalidAlgorithmParameterException e) {
            throw new CryptoException("Failed to generate key pair for alias: " + alias, e);
        }
    }

    /**
     * Retrieves the KeyPair associated with the given alias.
     *
     * @param alias the alias of the key pair.
     * @return the KeyPair if found, or null otherwise.
     * @throws CryptoException if retrieving the key pair fails.
     */
    @Nullable
    @Override
    public KeyPair getKeyPair(@NonNull String alias) {
        try {
            if (!keyStore.containsAlias(alias)) {
                return null;
            }
            PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, null);
            PublicKey publicKey = keyStore.getCertificate(alias).getPublicKey();
            
            if (privateKey == null || publicKey == null) {
                return null;
            }
            
            return new KeyPair(publicKey, privateKey);
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new CryptoException("Failed to retrieve key pair for alias: " + alias, e);
        }
    }

    /**
     * Deletes the key associated with the given alias.
     *
     * @param alias the alias of the key to delete.
     * @throws CryptoException if deleting the key fails.
     */
    @Override
    public void deleteKey(@NonNull String alias) {
        try {
            if (keyStore.containsAlias(alias)) {
                keyStore.deleteEntry(alias);
            }
        } catch (KeyStoreException e) {
            throw new CryptoException("Failed to delete key for alias: " + alias, e);
        }
    }

    /**
     * Retrieves the public key bytes for the given alias.
     *
     * @param alias the alias of the key.
     * @return a byte array representing the public key, or null if not found.
     * @throws CryptoException if retrieving the public key fails.
     */
    @Nullable
    @Override
    public byte[] getPublicKeyBytes(@NonNull String alias) {
        try {
            if (!keyStore.containsAlias(alias)) {
                return null;
            }
            PublicKey publicKey = keyStore.getCertificate(alias).getPublicKey();
            if (publicKey != null) {
                return publicKey.getEncoded();
            }
            return null;
        } catch (KeyStoreException e) {
            throw new CryptoException("Failed to retrieve public key bytes for alias: " + alias, e);
        }
    }
}
