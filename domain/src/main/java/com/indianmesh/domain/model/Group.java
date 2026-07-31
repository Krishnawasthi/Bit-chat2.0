/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.model;

import com.indianmesh.core.model.NodeId;
import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a group in the mesh network.
 */
public final class Group {
    private final String groupId;
    private final String conversationId;
    private final String name;
    private final String description;
    private final byte[] avatar;
    private final NodeId createdBy;
    private final long createdAt;
    private final long updatedAt;
    private final int maxMembers;
    private final boolean isAnnouncementOnly;
    private final String inviteCode;

    private Group(Builder builder) {
        this.groupId = builder.groupId;
        this.conversationId = builder.conversationId;
        this.name = builder.name;
        this.description = builder.description;
        this.avatar = builder.avatar;
        this.createdBy = builder.createdBy;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
        this.maxMembers = builder.maxMembers;
        this.isAnnouncementOnly = builder.isAnnouncementOnly;
        this.inviteCode = builder.inviteCode;
    }

    public String getGroupId() { return groupId; }
    public String getConversationId() { return conversationId; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public byte[] getAvatar() { return avatar; }
    public NodeId getCreatedBy() { return createdBy; }
    public long getCreatedAt() { return createdAt; }
    public long getUpdatedAt() { return updatedAt; }
    public int getMaxMembers() { return maxMembers; }
    public boolean isAnnouncementOnly() { return isAnnouncementOnly; }
    public String getInviteCode() { return inviteCode; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return createdAt == group.createdAt &&
                updatedAt == group.updatedAt &&
                maxMembers == group.maxMembers &&
                isAnnouncementOnly == group.isAnnouncementOnly &&
                Objects.equals(groupId, group.groupId) &&
                Objects.equals(conversationId, group.conversationId) &&
                Objects.equals(name, group.name) &&
                Objects.equals(description, group.description) &&
                Arrays.equals(avatar, group.avatar) &&
                Objects.equals(createdBy, group.createdBy) &&
                Objects.equals(inviteCode, group.inviteCode);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(groupId, conversationId, name, description, createdBy, createdAt, updatedAt, maxMembers, isAnnouncementOnly, inviteCode);
        result = 31 * result + Arrays.hashCode(avatar);
        return result;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId='" + groupId + '\'' +
                ", name='" + name + '\'' +
                ", maxMembers=" + maxMembers +
                '}';
    }

    public static class Builder {
        private String groupId;
        private String conversationId;
        private String name;
        private String description;
        private byte[] avatar;
        private NodeId createdBy;
        private long createdAt;
        private long updatedAt;
        private int maxMembers;
        private boolean isAnnouncementOnly;
        private String inviteCode;

        public Builder groupId(String groupId) { this.groupId = groupId; return this; }
        public Builder conversationId(String conversationId) { this.conversationId = conversationId; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder description(String description) { this.description = description; return this; }
        public Builder avatar(byte[] avatar) { this.avatar = avatar; return this; }
        public Builder createdBy(NodeId createdBy) { this.createdBy = createdBy; return this; }
        public Builder createdAt(long createdAt) { this.createdAt = createdAt; return this; }
        public Builder updatedAt(long updatedAt) { this.updatedAt = updatedAt; return this; }
        public Builder maxMembers(int maxMembers) { this.maxMembers = maxMembers; return this; }
        public Builder isAnnouncementOnly(boolean isAnnouncementOnly) { this.isAnnouncementOnly = isAnnouncementOnly; return this; }
        public Builder inviteCode(String inviteCode) { this.inviteCode = inviteCode; return this; }

        public Group build() {
            return new Group(this);
        }
    }
}
