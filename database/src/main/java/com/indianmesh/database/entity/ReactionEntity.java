/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Entity representing a reaction to a message.
 */
@Entity(tableName = "reactions",
        indices = {@Index(value = {"message_id", "sender_id", "emoji"}, unique = true)})
public class ReactionEntity {

    /** The unique reaction identifier. */
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "reaction_id")
    private final String reactionId;

    /** The message identifier being reacted to. */
    @NonNull
    @ColumnInfo(name = "message_id")
    private final String messageId;

    /** The sender's node identifier. */
    @NonNull
    @ColumnInfo(name = "sender_id")
    private final String senderId;

    /** The emoji string of the reaction. */
    @NonNull
    @ColumnInfo(name = "emoji")
    private final String emoji;

    /** The timestamp of the reaction. */
    @ColumnInfo(name = "timestamp")
    private final long timestamp;

    /**
     * Constructs a new ReactionEntity.
     *
     * @param reactionId The unique reaction ID.
     * @param messageId  The target message ID.
     * @param senderId   The user who reacted.
     * @param emoji      The emoji string.
     * @param timestamp  The timestamp.
     */
    public ReactionEntity(@NonNull String reactionId, @NonNull String messageId,
                          @NonNull String senderId, @NonNull String emoji, long timestamp) {
        this.reactionId = reactionId;
        this.messageId = messageId;
        this.senderId = senderId;
        this.emoji = emoji;
        this.timestamp = timestamp;
    }

    /** @return The reaction ID. */
    @NonNull
    public String getReactionId() { return reactionId; }

    /** @return The message ID. */
    @NonNull
    public String getMessageId() { return messageId; }

    /** @return The sender ID. */
    @NonNull
    public String getSenderId() { return senderId; }

    /** @return The emoji string. */
    @NonNull
    public String getEmoji() { return emoji; }

    /** @return The timestamp. */
    public long getTimestamp() { return timestamp; }
}
