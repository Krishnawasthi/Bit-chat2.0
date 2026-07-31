/*
 * Copyright (c) 2026 Indian Mesh. All rights reserved.
 */
package com.indianmesh.database.migration;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * Migration schema implementation from database version 1 to 2.
 */
public final class Migration_1_2 extends Migration {

    /**
     * Constructs a new {@link Migration_1_2} instance.
     */
    public Migration_1_2() {
        super(1, 2);
    }

    /**
     * Executes the necessary SQL statements to migrate from version 1 to 2.
     *
     * @param database the database instance to run queries against
     */
    @Override
    public void migrate(@NonNull SupportSQLiteDatabase database) {
        // Provide the specific DDL statements required for migrating to version 2
        // Example: database.execSQL("ALTER TABLE users ADD COLUMN is_premium INTEGER NOT NULL DEFAULT 0");
    }
}
