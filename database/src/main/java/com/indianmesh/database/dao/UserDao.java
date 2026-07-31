/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.indianmesh.database.entity.UserEntity;

import java.util.List;

/**
 * Data Access Object for the User entity.
 * Provides methods for performing CRUD operations on the users table.
 */
@Dao
public interface UserDao {

    /**
     * Inserts a new user or replaces an existing one.
     *
     * @param user The user entity to insert.
     * @return The row ID of the newly inserted user.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(UserEntity user);

    /**
     * Inserts multiple users or replaces existing ones.
     *
     * @param users The list of user entities to insert.
     * @return The row IDs of the newly inserted users.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(List<UserEntity> users);

    /**
     * Updates an existing user.
     *
     * @param user The user entity to update.
     * @return The number of rows updated.
     */
    @Update
    int update(UserEntity user);

    /**
     * Deletes a user.
     *
     * @param user The user entity to delete.
     * @return The number of rows deleted.
     */
    @Delete
    int delete(UserEntity user);

    /**
     * Retrieves a user by their node ID.
     *
     * @param nodeId The node ID of the user.
     * @return The user entity, or null if not found.
     */
    @Query("SELECT * FROM users WHERE node_id = :nodeId LIMIT 1")
    UserEntity getUserById(String nodeId);

    /**
     * Retrieves all users.
     *
     * @return A list of all user entities.
     */
    @Query("SELECT * FROM users")
    List<UserEntity> getAllUsers();

    /**
     * Retrieves the self user profile.
     *
     * @return The self user entity, or null if not found.
     */
    @Query("SELECT * FROM users WHERE is_self = 1 LIMIT 1")
    UserEntity getSelfUser();
}
