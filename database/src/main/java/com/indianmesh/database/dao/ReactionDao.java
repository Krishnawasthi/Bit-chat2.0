/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.indianmesh.database.entity.ReactionEntity;

import java.util.List;

/**
 * Data Access Object for the Reaction entity.
 * Provides methods for performing CRUD operations on the reactions table.
 */
@Dao
public interface ReactionDao {

    /**
     * Inserts a new reaction or replaces an existing one.
     *
     * @param reaction The reaction entity to insert.
     * @return The row ID of the newly inserted reaction.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(ReactionEntity reaction);

    /**
     * Inserts multiple reactions.
     *
     * @param reactions The list of reaction entities to insert.
     * @return The row IDs of the newly inserted reactions.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(List<ReactionEntity> reactions);

    /**
     * Updates an existing reaction.
     *
     * @param reaction The reaction entity to update.
     * @return The number of rows updated.
     */
    @Update
    int update(ReactionEntity reaction);

    /**
     * Deletes a reaction.
     *
     * @param reaction The reaction entity to delete.
     * @return The number of rows deleted.
     */
    @Delete
    int delete(ReactionEntity reaction);

    /**
     * Retrieves a reaction by its ID.
     *
     * @param reactionId The ID of the reaction.
     * @return The reaction entity, or null if not found.
     */
    @Query("SELECT * FROM reactions WHERE reaction_id = :reactionId LIMIT 1")
    ReactionEntity getReactionById(String reactionId);

    /**
     * Retrieves all reactions for a specific message.
     *
     * @param messageId The ID of the message.
     * @return A list of reaction entities associated with the message.
     */
    @Query("SELECT * FROM reactions WHERE message_id = :messageId")
    List<ReactionEntity> getReactionsForMessage(String messageId);
}
