/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity representing a group in the mesh network.
 */
@Entity(tableName = "groups")
public class GroupEntity {

    /** The unique group identifier. */
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "group_id")
    private final String groupId;

    /** The name of the group. */
    @NonNull
    @ColumnInfo(name = "name")
    private final String name;

    /** The description of the group. */
    @Nullable
    @ColumnInfo(name = "description")
    private final String description;

    /** The avatar image data of the group. */
    @Nullable
    @ColumnInfo(name = "avatar", typeAffinity = ColumnInfo.BLOB)
    private final byte[] avatar;

    /** The timestamp when the group was created. */
    @ColumnInfo(name = "created_at")
    private final long createdAt;

    /** The creator's node identifier. */
    @NonNull
    @ColumnInfo(name = "creator_id")
    private final String creatorId;

    /**
     * Constructs a new GroupEntity.
     *
     * @param groupId     The group ID.
     * @param name        The name of the group.
     * @param description The description of the group.
     * @param avatar      The group avatar bytes.
     * @param createdAt   The creation timestamp.
     * @param creatorId   The ID of the user who created the group.
     */
    public GroupEntity(@NonNull String groupId, @NonNull String name, @Nullable String description,
                       @Nullable byte[] avatar, long createdAt, @NonNull String creatorId) {
        this.groupId = groupId;
        this.name = name;
        this.description = description;
        this.avatar = avatar;
        this.createdAt = createdAt;
        this.creatorId = creatorId;
    }

    /** @return The group ID. */
    @NonNull
    public String getGroupId() { return groupId; }

    /** @return The group name. */
    @NonNull
    public String getName() { return name; }

    /** @return The group description. */
    @Nullable
    public String getDescription() { return description; }

    /** @return The group avatar. */
    @Nullable
    public byte[] getAvatar() { return avatar; }

    /** @return The creation time. */
    public long getCreatedAt() { return createdAt; }

    /** @return The creator ID. */
    @NonNull
    public String getCreatorId() { return creatorId; }
}
