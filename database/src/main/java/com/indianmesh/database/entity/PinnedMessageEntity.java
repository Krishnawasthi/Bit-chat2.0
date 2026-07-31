/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity representing a pinned message in a conversation.
 */
@Entity(tableName = "pinned_messages")
public class PinnedMessageEntity {

    /** The unique pinned message identifier (usually same as message ID). */
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "message_id")
    private final String messageId;

    /** The conversation identifier. */
    @NonNull
    @ColumnInfo(name = "conversation_id")
    private final String conversationId;

    /** The node identifier of the user who pinned it. */
    @NonNull
    @ColumnInfo(name = "pinned_by_id")
    private final String pinnedById;

    /** The timestamp when it was pinned. */
    @ColumnInfo(name = "pinned_at")
    private final long pinnedAt;

    /**
     * Constructs a new PinnedMessageEntity.
     *
     * @param messageId      The message ID.
     * @param conversationId The conversation ID.
     * @param pinnedById     The user who pinned the message.
     * @param pinnedAt       The pinned timestamp.
     */
    public PinnedMessageEntity(@NonNull String messageId, @NonNull String conversationId,
                               @NonNull String pinnedById, long pinnedAt) {
        this.messageId = messageId;
        this.conversationId = conversationId;
        this.pinnedById = pinnedById;
        this.pinnedAt = pinnedAt;
    }

    /** @return The message ID. */
    @NonNull
    public String getMessageId() { return messageId; }

    /** @return The conversation ID. */
    @NonNull
    public String getConversationId() { return conversationId; }

    /** @return The node ID of the user who pinned it. */
    @NonNull
    public String getPinnedById() { return pinnedById; }

    /** @return The timestamp it was pinned. */
    public long getPinnedAt() { return pinnedAt; }
}
