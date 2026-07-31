/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity representing a chat message.
 */
@Entity(tableName = "messages")
public class MessageEntity {

    /** The unique message identifier. */
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "message_id")
    private final String messageId;

    /** The conversation identifier this message belongs to. */
    @NonNull
    @ColumnInfo(name = "conversation_id")
    private final String conversationId;

    /** The sender's node identifier. */
    @NonNull
    @ColumnInfo(name = "sender_id")
    private final String senderId;

    /** The textual content of the message. */
    @Nullable
    @ColumnInfo(name = "content")
    private final String content;

    /** The timestamp when the message was sent. */
    @ColumnInfo(name = "timestamp")
    private final long timestamp;

    /** The delivery status of the message as a string. */
    @NonNull
    @ColumnInfo(name = "delivery_status")
    private final String deliveryStatus;

    /** The type of the message as a string. */
    @NonNull
    @ColumnInfo(name = "message_type")
    private final String messageType;

    /**
     * Constructs a new MessageEntity.
     *
     * @param messageId      The unique message ID.
     * @param conversationId The conversation ID.
     * @param senderId       The sender ID.
     * @param content        The content of the message.
     * @param timestamp      The timestamp of the message.
     * @param deliveryStatus The delivery status.
     * @param messageType    The message type.
     */
    public MessageEntity(@NonNull String messageId, @NonNull String conversationId, @NonNull String senderId,
                         @Nullable String content, long timestamp, @NonNull String deliveryStatus, @NonNull String messageType) {
        this.messageId = messageId;
        this.conversationId = conversationId;
        this.senderId = senderId;
        this.content = content;
        this.timestamp = timestamp;
        this.deliveryStatus = deliveryStatus;
        this.messageType = messageType;
    }

    /** @return The message ID. */
    @NonNull
    public String getMessageId() { return messageId; }

    /** @return The conversation ID. */
    @NonNull
    public String getConversationId() { return conversationId; }

    /** @return The sender ID. */
    @NonNull
    public String getSenderId() { return senderId; }

    /** @return The message content. */
    @Nullable
    public String getContent() { return content; }

    /** @return The timestamp. */
    public long getTimestamp() { return timestamp; }

    /** @return The delivery status string. */
    @NonNull
    public String getDeliveryStatus() { return deliveryStatus; }

    /** @return The message type string. */
    @NonNull
    public String getMessageType() { return messageType; }
}
