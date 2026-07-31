/*
 * Copyright (c) 2026 Indian Mesh. All rights reserved.
 */
package com.indianmesh.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SupportFactory;

/**
 * Singleton provider for {@link MeshDatabase}.
 * Initializes the Room database with SQLCipher encryption.
 */
public final class DatabaseProvider {

    private static volatile MeshDatabase instance;
    private static final String DATABASE_NAME = "indian_mesh.db";

    private DatabaseProvider() {
        // Prevent instantiation
    }

    /**
     * Gets the singleton instance of the {@link MeshDatabase}.
     * Initializes it if it has not been created yet using the provided passphrase for SQLCipher.
     *
     * @param context    the application context
     * @param passphrase the passphrase to decrypt/encrypt the database
     * @return the initialized {@link MeshDatabase} instance
     */
    @NonNull
    public static MeshDatabase getInstance(@NonNull Context context, @NonNull char[] passphrase) {
        if (instance == null) {
            synchronized (DatabaseProvider.class) {
                if (instance == null) {
                    byte[] passphraseBytes = SQLiteDatabase.getBytes(passphrase);
                    SupportFactory factory = new SupportFactory(passphraseBytes);

                    instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            MeshDatabase.class,
                            DATABASE_NAME
                    )
                    .openHelperFactory(factory)
                    .build();
                }
            }
        }
        return instance;
    }
}
