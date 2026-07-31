/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity representing an unsent message draft.
 */
@Entity(tableName = "drafts")
public class DraftEntity {

    /** The conversation identifier for this draft. */
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "conversation_id")
    private final String conversationId;

    /** The textual content of the draft. */
    @Nullable
    @ColumnInfo(name = "content")
    private final String content;

    /** The timestamp when the draft was last updated. */
    @ColumnInfo(name = "updated_at")
    private final long updatedAt;

    /**
     * Constructs a new DraftEntity.
     *
     * @param conversationId The conversation ID.
     * @param content        The text content.
     * @param updatedAt      The last updated timestamp.
     */
    public DraftEntity(@NonNull String conversationId, @Nullable String content, long updatedAt) {
        this.conversationId = conversationId;
        this.content = content;
        this.updatedAt = updatedAt;
    }

    /** @return The conversation ID. */
    @NonNull
    public String getConversationId() { return conversationId; }

    /** @return The textual content. */
    @Nullable
    public String getContent() { return content; }

    /** @return The last updated timestamp. */
    public long getUpdatedAt() { return updatedAt; }
}
