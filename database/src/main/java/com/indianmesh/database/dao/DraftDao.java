/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.indianmesh.database.entity.DraftEntity;

import java.util.List;

/**
 * Data Access Object for the Draft entity.
 * Provides methods for performing CRUD operations on the drafts table.
 */
@Dao
public interface DraftDao {

    /**
     * Inserts a new draft or replaces an existing one.
     *
     * @param draft The draft entity to insert.
     * @return The row ID of the newly inserted draft.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(DraftEntity draft);

    /**
     * Updates an existing draft.
     *
     * @param draft The draft entity to update.
     * @return The number of rows updated.
     */
    @Update
    int update(DraftEntity draft);

    /**
     * Deletes a draft.
     *
     * @param draft The draft entity to delete.
     * @return The number of rows deleted.
     */
    @Delete
    int delete(DraftEntity draft);

    /**
     * Retrieves a draft by its ID.
     *
     * @param draftId The ID of the draft.
     * @return The draft entity, or null if not found.
     */
    @Query("SELECT * FROM drafts WHERE draft_id = :draftId LIMIT 1")
    DraftEntity getDraftById(String draftId);

    /**
     * Retrieves the draft for a specific conversation.
     *
     * @param conversationId The ID of the conversation.
     * @return The draft entity for the conversation, or null if not found.
     */
    @Query("SELECT * FROM drafts WHERE conversation_id = :conversationId LIMIT 1")
    DraftEntity getDraftForConversation(String conversationId);

    /**
     * Retrieves all drafts.
     *
     * @return A list of all draft entities.
     */
    @Query("SELECT * FROM drafts")
    List<DraftEntity> getAllDrafts();
}
