/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity representing a scheduled message to be sent later.
 */
@Entity(tableName = "scheduled_messages")
public class ScheduledMessageEntity {

    /** The unique scheduled message identifier. */
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "scheduled_id")
    private final String scheduledId;

    /** The conversation identifier. */
    @NonNull
    @ColumnInfo(name = "conversation_id")
    private final String conversationId;

    /** The textual content to send. */
    @Nullable
    @ColumnInfo(name = "content")
    private final String content;

    /** The scheduled timestamp for sending. */
    @ColumnInfo(name = "scheduled_time")
    private final long scheduledTime;

    /**
     * Constructs a new ScheduledMessageEntity.
     *
     * @param scheduledId    The scheduled message ID.
     * @param conversationId The conversation ID.
     * @param content        The message content.
     * @param scheduledTime  The timestamp when it should be sent.
     */
    public ScheduledMessageEntity(@NonNull String scheduledId, @NonNull String conversationId,
                                  @Nullable String content, long scheduledTime) {
        this.scheduledId = scheduledId;
        this.conversationId = conversationId;
        this.content = content;
        this.scheduledTime = scheduledTime;
    }

    /** @return The scheduled message ID. */
    @NonNull
    public String getScheduledId() { return scheduledId; }

    /** @return The conversation ID. */
    @NonNull
    public String getConversationId() { return conversationId; }

    /** @return The message content. */
    @Nullable
    public String getContent() { return content; }

    /** @return The scheduled time. */
    public long getScheduledTime() { return scheduledTime; }
}
