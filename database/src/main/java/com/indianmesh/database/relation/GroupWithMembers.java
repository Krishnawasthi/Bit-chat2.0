/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.indianmesh.database.entity.GroupEntity;
import com.indianmesh.database.entity.GroupMemberEntity;

import java.util.List;

/**
 * Relation class representing a Group with all its associated Members.
 */
public class GroupWithMembers {

    /**
     * The group entity.
     */
    @Embedded
    public GroupEntity group;

    /**
     * The list of members associated with this group.
     * The relationship is based on the group_id column in both tables.
     */
    @Relation(
            parentColumn = "group_id",
            entityColumn = "group_id"
    )
    public List<GroupMemberEntity> members;
}
