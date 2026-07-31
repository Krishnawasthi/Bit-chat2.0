/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.indianmesh.database.entity.PollEntity;

import java.util.List;

/**
 * Data Access Object for the Poll entity.
 * Provides methods for performing CRUD operations on the polls table.
 */
@Dao
public interface PollDao {

    /**
     * Inserts a new poll or replaces an existing one.
     *
     * @param poll The poll entity to insert.
     * @return The row ID of the newly inserted poll.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(PollEntity poll);

    /**
     * Updates an existing poll.
     *
     * @param poll The poll entity to update.
     * @return The number of rows updated.
     */
    @Update
    int update(PollEntity poll);

    /**
     * Deletes a poll.
     *
     * @param poll The poll entity to delete.
     * @return The number of rows deleted.
     */
    @Delete
    int delete(PollEntity poll);

    /**
     * Retrieves a poll by its ID.
     *
     * @param pollId The ID of the poll.
     * @return The poll entity, or null if not found.
     */
    @Query("SELECT * FROM polls WHERE poll_id = :pollId LIMIT 1")
    PollEntity getPollById(String pollId);

    /**
     * Retrieves all polls.
     *
     * @return A list of all poll entities.
     */
    @Query("SELECT * FROM polls")
    List<PollEntity> getAllPolls();
}
