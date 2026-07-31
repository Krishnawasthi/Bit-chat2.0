/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.indianmesh.database.entity.AttachmentEntity;

import java.util.List;

/**
 * Data Access Object for the Attachment entity.
 * Provides methods for performing CRUD operations on the attachments table.
 */
@Dao
public interface AttachmentDao {

    /**
     * Inserts a new attachment or replaces an existing one.
     *
     * @param attachment The attachment entity to insert.
     * @return The row ID of the newly inserted attachment.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(AttachmentEntity attachment);

    /**
     * Inserts multiple attachments.
     *
     * @param attachments The list of attachment entities to insert.
     * @return The row IDs of the newly inserted attachments.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(List<AttachmentEntity> attachments);

    /**
     * Updates an existing attachment.
     *
     * @param attachment The attachment entity to update.
     * @return The number of rows updated.
     */
    @Update
    int update(AttachmentEntity attachment);

    /**
     * Deletes an attachment.
     *
     * @param attachment The attachment entity to delete.
     * @return The number of rows deleted.
     */
    @Delete
    int delete(AttachmentEntity attachment);

    /**
     * Retrieves an attachment by its ID.
     *
     * @param attachmentId The ID of the attachment.
     * @return The attachment entity, or null if not found.
     */
    @Query("SELECT * FROM attachments WHERE attachment_id = :attachmentId LIMIT 1")
    AttachmentEntity getAttachmentById(String attachmentId);

    /**
     * Retrieves all attachments for a specific message.
     *
     * @param messageId The ID of the message.
     * @return A list of attachment entities associated with the message.
     */
    @Query("SELECT * FROM attachments WHERE message_id = :messageId")
    List<AttachmentEntity> getAttachmentsForMessage(String messageId);
}
