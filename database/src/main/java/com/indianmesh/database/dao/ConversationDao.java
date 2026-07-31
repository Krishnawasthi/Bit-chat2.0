/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.indianmesh.database.entity.ConversationEntity;

import java.util.List;

/**
 * Data Access Object for the Conversation entity.
 * Provides methods for performing CRUD operations on the conversations table.
 */
@Dao
public interface ConversationDao {

    /**
     * Inserts a new conversation or replaces an existing one.
     *
     * @param conversation The conversation entity to insert.
     * @return The row ID of the newly inserted conversation.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(ConversationEntity conversation);

    /**
     * Updates an existing conversation.
     *
     * @param conversation The conversation entity to update.
     * @return The number of rows updated.
     */
    @Update
    int update(ConversationEntity conversation);

    /**
     * Deletes a conversation.
     *
     * @param conversation The conversation entity to delete.
     * @return The number of rows deleted.
     */
    @Delete
    int delete(ConversationEntity conversation);

    /**
     * Retrieves a conversation by its ID.
     *
     * @param conversationId The ID of the conversation.
     * @return The conversation entity, or null if not found.
     */
    @Query("SELECT * FROM conversations WHERE conversation_id = :conversationId LIMIT 1")
    ConversationEntity getConversationById(String conversationId);

    /**
     * Retrieves all conversations ordered by last activity descending.
     *
     * @return A list of all conversation entities.
     */
    @Query("SELECT * FROM conversations ORDER BY last_activity DESC")
    List<ConversationEntity> getAllConversations();
}
