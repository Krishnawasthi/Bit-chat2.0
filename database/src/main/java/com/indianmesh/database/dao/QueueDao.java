/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.indianmesh.database.entity.QueueEntity;

import java.util.List;

/**
 * Data Access Object for the Queue entity.
 * Provides methods for performing CRUD operations on the queues table.
 */
@Dao
public interface QueueDao {

    /**
     * Inserts a new queue item or replaces an existing one.
     *
     * @param queueItem The queue entity to insert.
     * @return The row ID of the newly inserted queue item.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(QueueEntity queueItem);

    /**
     * Updates an existing queue item.
     *
     * @param queueItem The queue entity to update.
     * @return The number of rows updated.
     */
    @Update
    int update(QueueEntity queueItem);

    /**
     * Deletes a queue item.
     *
     * @param queueItem The queue entity to delete.
     * @return The number of rows deleted.
     */
    @Delete
    int delete(QueueEntity queueItem);

    /**
     * Retrieves a queue item by its ID.
     *
     * @param queueId The ID of the queue item.
     * @return The queue entity, or null if not found.
     */
    @Query("SELECT * FROM queues WHERE queue_id = :queueId LIMIT 1")
    QueueEntity getQueueById(String queueId);

    /**
     * Retrieves all pending queue items ordered by priority and timestamp.
     *
     * @return A list of all pending queue entities.
     */
    @Query("SELECT * FROM queues WHERE status = 'PENDING' ORDER BY priority DESC, created_at ASC")
    List<QueueEntity> getPendingQueueItems();

    /**
     * Retrieves all queue items.
     *
     * @return A list of all queue entities.
     */
    @Query("SELECT * FROM queues")
    List<QueueEntity> getAllQueueItems();
}
