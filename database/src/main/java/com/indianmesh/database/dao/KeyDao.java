/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.indianmesh.database.entity.KeyEntity;

import java.util.List;

/**
 * Data Access Object for the Key entity.
 * Provides methods for performing CRUD operations on the keys table.
 */
@Dao
public interface KeyDao {

    /**
     * Inserts a new key or replaces an existing one.
     *
     * @param key The key entity to insert.
     * @return The row ID of the newly inserted key.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(KeyEntity key);

    /**
     * Updates an existing key.
     *
     * @param key The key entity to update.
     * @return The number of rows updated.
     */
    @Update
    int update(KeyEntity key);

    /**
     * Deletes a key.
     *
     * @param key The key entity to delete.
     * @return The number of rows deleted.
     */
    @Delete
    int delete(KeyEntity key);

    /**
     * Retrieves a key by its ID.
     *
     * @param keyId The ID of the key.
     * @return The key entity, or null if not found.
     */
    @Query("SELECT * FROM keys WHERE key_id = :keyId LIMIT 1")
    KeyEntity getKeyById(String keyId);

    /**
     * Retrieves all keys.
     *
     * @return A list of all key entities.
     */
    @Query("SELECT * FROM keys")
    List<KeyEntity> getAllKeys();
}
