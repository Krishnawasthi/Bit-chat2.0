/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.model;

import com.indianmesh.core.model.NodeId;
import java.util.Objects;

/**
 * Represents a member within a group.
 */
public final class GroupMember {
    private final String groupId;
    private final NodeId userId;
    private final GroupRole role;
    private final long joinedAt;
    private final boolean isMuted;
    private final String displayName;

    private GroupMember(Builder builder) {
        this.groupId = builder.groupId;
        this.userId = builder.userId;
        this.role = builder.role;
        this.joinedAt = builder.joinedAt;
        this.isMuted = builder.isMuted;
        this.displayName = builder.displayName;
    }

    public String getGroupId() { return groupId; }
    public NodeId getUserId() { return userId; }
    public GroupRole getRole() { return role; }
    public long getJoinedAt() { return joinedAt; }
    public boolean isMuted() { return isMuted; }
    public String getDisplayName() { return displayName; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupMember that = (GroupMember) o;
        return joinedAt == that.joinedAt &&
                isMuted == that.isMuted &&
                Objects.equals(groupId, that.groupId) &&
                Objects.equals(userId, that.userId) &&
                role == that.role &&
                Objects.equals(displayName, that.displayName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, userId, role, joinedAt, isMuted, displayName);
    }

    @Override
    public String toString() {
        return "GroupMember{" +
                "groupId='" + groupId + '\'' +
                ", userId=" + userId +
                ", role=" + role +
                '}';
    }

    public static class Builder {
        private String groupId;
        private NodeId userId;
        private GroupRole role;
        private long joinedAt;
        private boolean isMuted;
        private String displayName;

        public Builder groupId(String groupId) { this.groupId = groupId; return this; }
        public Builder userId(NodeId userId) { this.userId = userId; return this; }
        public Builder role(GroupRole role) { this.role = role; return this; }
        public Builder joinedAt(long joinedAt) { this.joinedAt = joinedAt; return this; }
        public Builder isMuted(boolean isMuted) { this.isMuted = isMuted; return this; }
        public Builder displayName(String displayName) { this.displayName = displayName; return this; }

        public GroupMember build() {
            return new GroupMember(this);
        }
    }
}
