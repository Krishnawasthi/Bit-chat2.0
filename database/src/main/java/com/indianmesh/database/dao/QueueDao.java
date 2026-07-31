/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.indianmesh.database.entity.MessageQueueEntity;

import java.util.List;

/**
 * Data Access Object for the Message Queue.
 * Provides methods for performing CRUD operations on the message_queue table.
 */
@Dao
public interface QueueDao {

    /**
     * Inserts a new queue item or replaces an existing one.
     *
     * @param queueItem The queue item to insert.
     * @return The row ID of the newly inserted queue item.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(MessageQueueEntity queueItem);

    /**
     * Updates an existing queue item.
     *
     * @param queueItem The queue item to update.
     * @return The number of rows updated.
     */
    @Update
    int update(MessageQueueEntity queueItem);

    /**
     * Deletes a queue item.
     *
     * @param queueItem The queue item to delete.
     * @return The number of rows deleted.
     */
    @Delete
    int delete(MessageQueueEntity queueItem);

    /**
     * Retrieves a queue item by its ID.
     *
     * @param queueId The ID of the queue item.
     * @return The queue entity, or null if not found.
     */
    @Query("SELECT * FROM message_queue WHERE id = :queueId LIMIT 1")
    MessageQueueEntity getQueueById(String queueId);

    /**
     * Retrieves all pending queue items.
     *
     * @return A list of all pending queue entities.
     */
    @Query("SELECT * FROM message_queue WHERE status = 'PENDING' ORDER BY created_at ASC")
    List<MessageQueueEntity> getPendingQueueItems();

    /**
     * Retrieves all queue items.
     *
     * @return A list of all queue entities.
     */
    @Query("SELECT * FROM message_queue ORDER BY created_at ASC")
    List<MessageQueueEntity> getAllQueueItems();
}
