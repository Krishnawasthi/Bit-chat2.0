/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;

/**
 * Entity representing a membership in a group.
 */
@Entity(tableName = "group_members",
        primaryKeys = {"group_id", "node_id"},
        indices = {@Index("node_id")})
public class GroupMemberEntity {

    /** The group identifier. */
    @NonNull
    @ColumnInfo(name = "group_id")
    private final String groupId;

    /** The node identifier of the member. */
    @NonNull
    @ColumnInfo(name = "node_id")
    private final String nodeId;

    /** The role of the member in the group. */
    @NonNull
    @ColumnInfo(name = "role")
    private final String role;

    /** The timestamp when the member joined. */
    @ColumnInfo(name = "joined_at")
    private final long joinedAt;

    /**
     * Constructs a new GroupMemberEntity.
     *
     * @param groupId  The group ID.
     * @param nodeId   The member's node ID.
     * @param role     The member's role.
     * @param joinedAt The time the member joined.
     */
    public GroupMemberEntity(@NonNull String groupId, @NonNull String nodeId,
                             @NonNull String role, long joinedAt) {
        this.groupId = groupId;
        this.nodeId = nodeId;
        this.role = role;
        this.joinedAt = joinedAt;
    }

    /** @return The group ID. */
    @NonNull
    public String getGroupId() { return groupId; }

    /** @return The member's node ID. */
    @NonNull
    public String getNodeId() { return nodeId; }

    /** @return The member's role. */
    @NonNull
    public String getRole() { return role; }

    /** @return The joined timestamp. */
    public long getJoinedAt() { return joinedAt; }
}
