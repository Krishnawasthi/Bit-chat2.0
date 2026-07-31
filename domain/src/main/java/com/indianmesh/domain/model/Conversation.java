/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.model;

import com.indianmesh.core.model.MessageId;
import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a conversation between users.
 */
public final class Conversation {
    private final String conversationId;
    private final ConversationType type;
    private final String title;
    private final byte[] avatar;
    private final long createdAt;
    private final long updatedAt;
    private final MessageId lastMessageId;
    private final String lastMessagePreview;
    private final int unreadCount;
    private final boolean isPinned;
    private final boolean isMuted;
    private final boolean isArchived;
    private final String draftText;

    private Conversation(Builder builder) {
        this.conversationId = builder.conversationId;
        this.type = builder.type;
        this.title = builder.title;
        this.avatar = builder.avatar;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
        this.lastMessageId = builder.lastMessageId;
        this.lastMessagePreview = builder.lastMessagePreview;
        this.unreadCount = builder.unreadCount;
        this.isPinned = builder.isPinned;
        this.isMuted = builder.isMuted;
        this.isArchived = builder.isArchived;
        this.draftText = builder.draftText;
    }

    public String getConversationId() { return conversationId; }
    public ConversationType getType() { return type; }
    public String getTitle() { return title; }
    public byte[] getAvatar() { return avatar; }
    public long getCreatedAt() { return createdAt; }
    public long getUpdatedAt() { return updatedAt; }
    public MessageId getLastMessageId() { return lastMessageId; }
    public String getLastMessagePreview() { return lastMessagePreview; }
    public int getUnreadCount() { return unreadCount; }
    public boolean isPinned() { return isPinned; }
    public boolean isMuted() { return isMuted; }
    public boolean isArchived() { return isArchived; }
    public String getDraftText() { return draftText; }

    /**
     * Checks if the conversation has unread messages.
     *
     * @return True if there are unread messages.
     */
    public boolean hasUnread() {
        return unreadCount > 0;
    }

    /**
     * Checks if this is a direct conversation.
     *
     * @return True if it is direct.
     */
    public boolean isDirect() {
        return type == ConversationType.DIRECT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conversation that = (Conversation) o;
        return Objects.equals(conversationId, that.conversationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conversationId);
    }

    @Override
    public String toString() {
        return "Conversation{" +
                "conversationId='" + conversationId + '\'' +
                ", type=" + type +
                ", unreadCount=" + unreadCount +
                '}';
    }

    public static class Builder {
        private String conversationId;
        private ConversationType type;
        private String title;
        private byte[] avatar;
        private long createdAt;
        private long updatedAt;
        private MessageId lastMessageId;
        private String lastMessagePreview;
        private int unreadCount;
        private boolean isPinned;
        private boolean isMuted;
        private boolean isArchived;
        private String draftText;

        public Builder conversationId(String conversationId) { this.conversationId = conversationId; return this; }
        public Builder type(ConversationType type) { this.type = type; return this; }
        public Builder title(String title) { this.title = title; return this; }
        public Builder avatar(byte[] avatar) { this.avatar = avatar; return this; }
        public Builder createdAt(long createdAt) { this.createdAt = createdAt; return this; }
        public Builder updatedAt(long updatedAt) { this.updatedAt = updatedAt; return this; }
        public Builder lastMessageId(MessageId lastMessageId) { this.lastMessageId = lastMessageId; return this; }
        public Builder lastMessagePreview(String lastMessagePreview) { this.lastMessagePreview = lastMessagePreview; return this; }
        public Builder unreadCount(int unreadCount) { this.unreadCount = unreadCount; return this; }
        public Builder isPinned(boolean isPinned) { this.isPinned = isPinned; return this; }
        public Builder isMuted(boolean isMuted) { this.isMuted = isMuted; return this; }
        public Builder isArchived(boolean isArchived) { this.isArchived = isArchived; return this; }
        public Builder draftText(String draftText) { this.draftText = draftText; return this; }

        public Conversation build() {
            return new Conversation(this);
        }
    }
}
