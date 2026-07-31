/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.model;

import com.indianmesh.core.model.MessageId;
import java.util.Objects;

/**
 * Represents a draft message.
 */
public final class Draft {
    private final String conversationId;
    private final String content;
    private final MessageId replyToId;
    private final long updatedAt;

    /**
     * Constructs a Draft.
     *
     * @param conversationId The ID of the conversation this draft belongs to.
     * @param content        The content of the draft.
     * @param replyToId      The ID of the message this draft is replying to, or null if none.
     * @param updatedAt      The timestamp when the draft was last updated.
     */
    public Draft(String conversationId, String content, MessageId replyToId, long updatedAt) {
        this.conversationId = conversationId;
        this.content = content;
        this.replyToId = replyToId;
        this.updatedAt = updatedAt;
    }

    /**
     * Gets the conversation ID.
     *
     * @return The conversation ID.
     */
    public String getConversationId() {
        return conversationId;
    }

    /**
     * Gets the content.
     *
     * @return The draft content.
     */
    public String getContent() {
        return content;
    }

    /**
     * Gets the ID of the message being replied to.
     *
     * @return The reply-to message ID, or null.
     */
    public MessageId getReplyToId() {
        return replyToId;
    }

    /**
     * Gets the last updated timestamp.
     *
     * @return The updated timestamp.
     */
    public long getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Draft draft = (Draft) o;
        return updatedAt == draft.updatedAt &&
                Objects.equals(conversationId, draft.conversationId) &&
                Objects.equals(content, draft.content) &&
                Objects.equals(replyToId, draft.replyToId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conversationId, content, replyToId, updatedAt);
    }

    @Override
    public String toString() {
        return "Draft{" +
                "conversationId='" + conversationId + '\'' +
                ", content='" + content + '\'' +
                ", replyToId=" + replyToId +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
