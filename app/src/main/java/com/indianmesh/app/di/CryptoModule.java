/* Copyright (c) 2026 Indian Mesh. All rights reserved. */
package com.indianmesh.app.di;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import javax.inject.Singleton;
import com.indianmesh.crypto.key.KeyManager;
import com.indianmesh.crypto.key.AndroidKeyStoreManager;
import com.indianmesh.crypto.symmetric.EncryptionService;
import com.indianmesh.crypto.symmetric.AESGCMEncryptionService;
import com.indianmesh.crypto.signature.SignatureService;
import com.indianmesh.crypto.signature.ECDSASignatureService;

@Module
@InstallIn(SingletonComponent.class)
public class CryptoModule {

    @Provides
    @Singleton
    public KeyManager provideKeyManager() {
        return new AndroidKeyStoreManager();
    }

    @Provides
    @Singleton
    public EncryptionService provideEncryptionService() {
        return new AESGCMEncryptionService();
    }

    @Provides
    @Singleton
    public SignatureService provideSignatureService() {
        return new ECDSASignatureService();
    }
}
