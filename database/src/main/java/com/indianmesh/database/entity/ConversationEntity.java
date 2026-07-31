/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity representing a conversation.
 */
@Entity(tableName = "conversations")
public class ConversationEntity {

    /** The unique conversation identifier. */
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "conversation_id")
    private final String conversationId;

    /** The type of the conversation (e.g., DIRECT, GROUP). */
    @NonNull
    @ColumnInfo(name = "type")
    private final String type;

    /** The title of the conversation. */
    @Nullable
    @ColumnInfo(name = "title")
    private final String title;

    /** Timestamp of the last activity in this conversation. */
    @ColumnInfo(name = "last_activity")
    private final long lastActivity;

    /** Number of unread messages. */
    @ColumnInfo(name = "unread_count")
    private final int unreadCount;

    /**
     * Constructs a new ConversationEntity.
     *
     * @param conversationId The conversation ID.
     * @param type           The conversation type.
     * @param title          The title of the conversation.
     * @param lastActivity   The timestamp of last activity.
     * @param unreadCount    The unread message count.
     */
    public ConversationEntity(@NonNull String conversationId, @NonNull String type,
                              @Nullable String title, long lastActivity, int unreadCount) {
        this.conversationId = conversationId;
        this.type = type;
        this.title = title;
        this.lastActivity = lastActivity;
        this.unreadCount = unreadCount;
    }

    /** @return The conversation ID. */
    @NonNull
    public String getConversationId() { return conversationId; }

    /** @return The conversation type. */
    @NonNull
    public String getType() { return type; }

    /** @return The title. */
    @Nullable
    public String getTitle() { return title; }

    /** @return The last activity timestamp. */
    public long getLastActivity() { return lastActivity; }

    /** @return The unread count. */
    public int getUnreadCount() { return unreadCount; }
}
