/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.model;

import com.indianmesh.core.model.MessageId;
import com.indianmesh.core.model.NodeId;
import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a message in a conversation.
 */
public final class Message {
    private final MessageId messageId;
    private final String conversationId;
    private final NodeId senderId;
    private final String content;
    private final MessageType type;
    private final DeliveryStatus status;
    private final long createdAt;
    private final long sentAt;
    private final long deliveredAt;
    private final long readAt;
    private final MessageId replyToId;
    private final MessageId forwardedFromId;
    private final boolean isDeleted;
    private final boolean isDeletedForEveryone;
    private final boolean isStarred;
    private final boolean isPinned;
    private final int hopCount;
    private final int ttl;
    private final NodeId originNodeId;
    private final long scheduledAt;
    private final byte[] encryptedContent;
    private final byte[] signature;
    private final long sequenceNumber;
    private final MessagePriority priority;

    private Message(Builder builder) {
        this.messageId = builder.messageId;
        this.conversationId = builder.conversationId;
        this.senderId = builder.senderId;
        this.content = builder.content;
        this.type = builder.type;
        this.status = builder.status;
        this.createdAt = builder.createdAt;
        this.sentAt = builder.sentAt;
        this.deliveredAt = builder.deliveredAt;
        this.readAt = builder.readAt;
        this.replyToId = builder.replyToId;
        this.forwardedFromId = builder.forwardedFromId;
        this.isDeleted = builder.isDeleted;
        this.isDeletedForEveryone = builder.isDeletedForEveryone;
        this.isStarred = builder.isStarred;
        this.isPinned = builder.isPinned;
        this.hopCount = builder.hopCount;
        this.ttl = builder.ttl;
        this.originNodeId = builder.originNodeId;
        this.scheduledAt = builder.scheduledAt;
        this.encryptedContent = builder.encryptedContent;
        this.signature = builder.signature;
        this.sequenceNumber = builder.sequenceNumber;
        this.priority = builder.priority;
    }

    public MessageId getMessageId() { return messageId; }
    public String getConversationId() { return conversationId; }
    public NodeId getSenderId() { return senderId; }
    public String getContent() { return content; }
    public MessageType getType() { return type; }
    public DeliveryStatus getStatus() { return status; }
    public long getCreatedAt() { return createdAt; }
    public long getSentAt() { return sentAt; }
    public long getDeliveredAt() { return deliveredAt; }
    public long getReadAt() { return readAt; }
    public MessageId getReplyToId() { return replyToId; }
    public MessageId getForwardedFromId() { return forwardedFromId; }
    public boolean isDeleted() { return isDeleted; }
    public boolean isDeletedForEveryone() { return isDeletedForEveryone; }
    public boolean isStarred() { return isStarred; }
    public boolean isPinned() { return isPinned; }
    public int getHopCount() { return hopCount; }
    public int getTtl() { return ttl; }
    public NodeId getOriginNodeId() { return originNodeId; }
    public long getScheduledAt() { return scheduledAt; }
    public byte[] getEncryptedContent() { return encryptedContent; }
    public byte[] getSignature() { return signature; }
    public long getSequenceNumber() { return sequenceNumber; }
    public MessagePriority getPriority() { return priority; }

    /**
     * Checks if the message is scheduled for future delivery.
     *
     * @return True if scheduled.
     */
    public boolean isScheduled() {
        return scheduledAt > 0;
    }

    /**
     * Checks if the message was sent by the local user.
     *
     * @param selfId The local user's NodeId.
     * @return True if outgoing.
     */
    public boolean isOutgoing(NodeId selfId) {
        return selfId != null && selfId.equals(senderId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(messageId, message.messageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId);
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", type=" + type +
                ", status=" + status +
                '}';
    }

    public static class Builder {
        private MessageId messageId;
        private String conversationId;
        private NodeId senderId;
        private String content;
        private MessageType type;
        private DeliveryStatus status;
        private long createdAt;
        private long sentAt;
        private long deliveredAt;
        private long readAt;
        private MessageId replyToId;
        private MessageId forwardedFromId;
        private boolean isDeleted;
        private boolean isDeletedForEveryone;
        private boolean isStarred;
        private boolean isPinned;
        private int hopCount;
        private int ttl;
        private NodeId originNodeId;
        private long scheduledAt;
        private byte[] encryptedContent;
        private byte[] signature;
        private long sequenceNumber;
        private MessagePriority priority;

        public Builder messageId(MessageId messageId) { this.messageId = messageId; return this; }
        public Builder conversationId(String conversationId) { this.conversationId = conversationId; return this; }
        public Builder senderId(NodeId senderId) { this.senderId = senderId; return this; }
        public Builder content(String content) { this.content = content; return this; }
        public Builder type(MessageType type) { this.type = type; return this; }
        public Builder status(DeliveryStatus status) { this.status = status; return this; }
        public Builder createdAt(long createdAt) { this.createdAt = createdAt; return this; }
        public Builder sentAt(long sentAt) { this.sentAt = sentAt; return this; }
        public Builder deliveredAt(long deliveredAt) { this.deliveredAt = deliveredAt; return this; }
        public Builder readAt(long readAt) { this.readAt = readAt; return this; }
        public Builder replyToId(MessageId replyToId) { this.replyToId = replyToId; return this; }
        public Builder forwardedFromId(MessageId forwardedFromId) { this.forwardedFromId = forwardedFromId; return this; }
        public Builder isDeleted(boolean isDeleted) { this.isDeleted = isDeleted; return this; }
        public Builder isDeletedForEveryone(boolean isDeletedForEveryone) { this.isDeletedForEveryone = isDeletedForEveryone; return this; }
        public Builder isStarred(boolean isStarred) { this.isStarred = isStarred; return this; }
        public Builder isPinned(boolean isPinned) { this.isPinned = isPinned; return this; }
        public Builder hopCount(int hopCount) { this.hopCount = hopCount; return this; }
        public Builder ttl(int ttl) { this.ttl = ttl; return this; }
        public Builder originNodeId(NodeId originNodeId) { this.originNodeId = originNodeId; return this; }
        public Builder scheduledAt(long scheduledAt) { this.scheduledAt = scheduledAt; return this; }
        public Builder encryptedContent(byte[] encryptedContent) { this.encryptedContent = encryptedContent; return this; }
        public Builder signature(byte[] signature) { this.signature = signature; return this; }
        public Builder sequenceNumber(long sequenceNumber) { this.sequenceNumber = sequenceNumber; return this; }
        public Builder priority(MessagePriority priority) { this.priority = priority; return this; }

        public Message build() {
            return new Message(this);
        }
    }
}
