/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity representing a message in the outgoing queue.
 */
@Entity(tableName = "message_queue")
public class MessageQueueEntity {

    /** The unique queue entry identifier. */
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "queue_id")
    private final String queueId;

    /** The identifier of the message to be sent. */
    @NonNull
    @ColumnInfo(name = "message_id")
    private final String messageId;

    /** The destination node identifier. */
    @NonNull
    @ColumnInfo(name = "destination_id")
    private final String destinationId;

    /** The priority of the message in the queue. */
    @ColumnInfo(name = "priority")
    private final int priority;

    /** The number of times sending has been retried. */
    @ColumnInfo(name = "retry_count")
    private final int retryCount;

    /** The timestamp of the next retry attempt. */
    @ColumnInfo(name = "next_retry_time")
    private final long nextRetryTime;

    /**
     * Constructs a new MessageQueueEntity.
     *
     * @param queueId       The queue ID.
     * @param messageId     The associated message ID.
     * @param destinationId The destination node ID.
     * @param priority      The priority level.
     * @param retryCount    The current retry count.
     * @param nextRetryTime The next retry timestamp.
     */
    public MessageQueueEntity(@NonNull String queueId, @NonNull String messageId,
                              @NonNull String destinationId, int priority,
                              int retryCount, long nextRetryTime) {
        this.queueId = queueId;
        this.messageId = messageId;
        this.destinationId = destinationId;
        this.priority = priority;
        this.retryCount = retryCount;
        this.nextRetryTime = nextRetryTime;
    }

    /** @return The queue ID. */
    @NonNull
    public String getQueueId() { return queueId; }

    /** @return The associated message ID. */
    @NonNull
    public String getMessageId() { return messageId; }

    /** @return The destination node ID. */
    @NonNull
    public String getDestinationId() { return destinationId; }

    /** @return The priority. */
    public int getPriority() { return priority; }

    /** @return The retry count. */
    public int getRetryCount() { return retryCount; }

    /** @return The next retry time. */
    public long getNextRetryTime() { return nextRetryTime; }
}
