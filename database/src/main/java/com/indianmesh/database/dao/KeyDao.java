/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.indianmesh.database.entity.KeyPairEntity;

import java.util.List;

/**
 * Data Access Object for the Key entity.
 * Provides methods for performing CRUD operations on the keys table.
 */
@Dao
public interface KeyDao {

    /**
     * Inserts a new key record or replaces an existing one.
     *
     * @param key The key entity to insert.
     * @return The row ID of the newly inserted key.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(KeyPairEntity key);

    /**
     * Updates an existing key record.
     *
     * @param key The key entity to update.
     * @return The number of rows updated.
     */
    @Update
    int update(KeyPairEntity key);

    /**
     * Deletes a key record.
     *
     * @param key The key entity to delete.
     * @return The number of rows deleted.
     */
    @Delete
    int delete(KeyPairEntity key);

    /**
     * Retrieves a key record by its ID.
     *
     * @param keyId The ID of the key record.
     * @return The key entity, or null if not found.
     */
    @Query("SELECT * FROM keys WHERE key_id = :keyId LIMIT 1")
    KeyPairEntity getKeyById(String keyId);

    /**
     * Retrieves all key records.
     *
     * @return A list of all key entities.
     */
    @Query("SELECT * FROM keys")
    List<KeyPairEntity> getAllKeys();
}
