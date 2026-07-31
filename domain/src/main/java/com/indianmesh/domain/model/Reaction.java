/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.model;

import com.indianmesh.core.model.MessageId;
import com.indianmesh.core.model.NodeId;
import java.util.Objects;

/**
 * Represents a reaction to a message.
 */
public final class Reaction {
    private final String reactionId;
    private final MessageId messageId;
    private final NodeId userId;
    private final String emoji;
    private final long createdAt;

    /**
     * Constructs a new Reaction.
     *
     * @param reactionId The unique identifier for the reaction.
     * @param messageId  The ID of the message this reaction belongs to.
     * @param userId     The ID of the user who made the reaction.
     * @param emoji      The emoji string.
     * @param createdAt  The timestamp when the reaction was created.
     */
    public Reaction(String reactionId, MessageId messageId, NodeId userId, String emoji, long createdAt) {
        this.reactionId = reactionId;
        this.messageId = messageId;
        this.userId = userId;
        this.emoji = emoji;
        this.createdAt = createdAt;
    }

    /**
     * Gets the reaction ID.
     *
     * @return The reaction ID.
     */
    public String getReactionId() {
        return reactionId;
    }

    /**
     * Gets the message ID.
     *
     * @return The message ID.
     */
    public MessageId getMessageId() {
        return messageId;
    }

    /**
     * Gets the user ID.
     *
     * @return The user ID.
     */
    public NodeId getUserId() {
        return userId;
    }

    /**
     * Gets the emoji.
     *
     * @return The emoji.
     */
    public String getEmoji() {
        return emoji;
    }

    /**
     * Gets the creation timestamp.
     *
     * @return The creation timestamp.
     */
    public long getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reaction reaction = (Reaction) o;
        return createdAt == reaction.createdAt &&
                Objects.equals(reactionId, reaction.reactionId) &&
                Objects.equals(messageId, reaction.messageId) &&
                Objects.equals(userId, reaction.userId) &&
                Objects.equals(emoji, reaction.emoji);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reactionId, messageId, userId, emoji, createdAt);
    }

    @Override
    public String toString() {
        return "Reaction{" +
                "reactionId='" + reactionId + '\'' +
                ", messageId=" + messageId +
                ", userId=" + userId +
                ", emoji='" + emoji + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
