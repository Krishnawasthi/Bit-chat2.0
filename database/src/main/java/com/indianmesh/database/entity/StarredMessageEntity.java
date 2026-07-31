/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity representing a starred message.
 */
@Entity(tableName = "starred_messages")
public class StarredMessageEntity {

    /** The unique message identifier. */
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "message_id")
    private final String messageId;

    /** The timestamp when the message was starred. */
    @ColumnInfo(name = "starred_at")
    private final long starredAt;

    /**
     * Constructs a new StarredMessageEntity.
     *
     * @param messageId The message ID.
     * @param starredAt The timestamp when it was starred.
     */
    public StarredMessageEntity(@NonNull String messageId, long starredAt) {
        this.messageId = messageId;
        this.starredAt = starredAt;
    }

    /** @return The message ID. */
    @NonNull
    public String getMessageId() { return messageId; }

    /** @return The starred timestamp. */
    public long getStarredAt() { return starredAt; }
}
