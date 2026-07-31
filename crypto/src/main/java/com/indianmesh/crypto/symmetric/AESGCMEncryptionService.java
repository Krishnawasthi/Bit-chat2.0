/*
 * Copyright (c) 2026 Indian Mesh. All rights reserved.
 */

package com.indianmesh.crypto.symmetric;

import androidx.annotation.NonNull;
import com.indianmesh.crypto.exception.CryptoException;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES/GCM/NoPadding implementation of the {@link EncryptionService}.
 * <p>
 * This service generates a 12-byte IV for encryption and prepends it to the
 * resulting ciphertext. During decryption, the IV is extracted from the start
 * of the ciphertext. A 128-bit authentication tag is used.
 * </p>
 */
public class AESGCMEncryptionService implements EncryptionService {

    private static final String ALGORITHM = "AES/GCM/NoPadding";
    private static final String KEY_ALGORITHM = "AES";
    private static final int IV_LENGTH_BYTES = 12;
    private static final int TAG_LENGTH_BITS = 128;

    private final SecureRandom secureRandom;

    /**
     * Constructs a new AESGCMEncryptionService with a secure random instance.
     */
    public AESGCMEncryptionService() {
        this.secureRandom = new SecureRandom();
    }

    @NonNull
    @Override
    public byte[] encrypt(@NonNull byte[] plaintext, @NonNull byte[] key) {
        try {
            byte[] iv = new byte[IV_LENGTH_BYTES];
            secureRandom.nextBytes(iv);

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(TAG_LENGTH_BITS, iv);
            SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);

            cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmParameterSpec);
            byte[] ciphertext = cipher.doFinal(plaintext);

            // Prepend IV to ciphertext
            ByteBuffer byteBuffer = ByteBuffer.allocate(iv.length + ciphertext.length);
            byteBuffer.put(iv);
            byteBuffer.put(ciphertext);
            return byteBuffer.array();
        } catch (GeneralSecurityException e) {
            throw new CryptoException("Encryption failed", e);
        }
    }

    @NonNull
    @Override
    public byte[] decrypt(@NonNull byte[] ciphertext, @NonNull byte[] key) {
        if (ciphertext.length < IV_LENGTH_BYTES) {
            throw new CryptoException("Ciphertext too short to contain IV");
        }

        try {
            byte[] iv = new byte[IV_LENGTH_BYTES];
            System.arraycopy(ciphertext, 0, iv, 0, IV_LENGTH_BYTES);

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(TAG_LENGTH_BITS, iv);
            SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);

            cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmParameterSpec);

            // Decrypt the data following the IV
            return cipher.doFinal(ciphertext, IV_LENGTH_BYTES, ciphertext.length - IV_LENGTH_BYTES);
        } catch (GeneralSecurityException e) {
            throw new CryptoException("Decryption failed", e);
        }
    }
}
