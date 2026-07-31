/*
 * Copyright (c) 2026 Indian Mesh. All rights reserved.
 */
package com.indianmesh.database.migration;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;

/**
 * Utility class to provide all database migrations for Room.
 */
public final class MigrationHelper {

    private MigrationHelper() {
        // Prevent instantiation
    }

    /**
     * Returns an array of all defined database migrations.
     *
     * @return an array of {@link Migration} objects to be added to the Room database builder
     */
    @NonNull
    public static Migration[] getMigrations() {
        return new Migration[]{
                new Migration_1_2()
        };
    }
}
