/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.indianmesh.database.entity.GroupEntity;
import com.indianmesh.database.entity.GroupMemberEntity;

import java.util.List;

/**
 * Data Access Object for the Group and GroupMember entities.
 * Provides methods for performing CRUD operations on the groups and group_members tables.
 */
@Dao
public interface GroupDao {

    /**
     * Inserts a new group or replaces an existing one.
     *
     * @param group The group entity to insert.
     * @return The row ID of the newly inserted group.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertGroup(GroupEntity group);

    /**
     * Updates an existing group.
     *
     * @param group The group entity to update.
     * @return The number of rows updated.
     */
    @Update
    int updateGroup(GroupEntity group);

    /**
     * Deletes a group.
     *
     * @param group The group entity to delete.
     * @return The number of rows deleted.
     */
    @Delete
    int deleteGroup(GroupEntity group);

    /**
     * Retrieves a group by its ID.
     *
     * @param groupId The ID of the group.
     * @return The group entity, or null if not found.
     */
    @Query("SELECT * FROM groups WHERE group_id = :groupId LIMIT 1")
    GroupEntity getGroupById(String groupId);

    /**
     * Retrieves all groups.
     *
     * @return A list of all group entities.
     */
    @Query("SELECT * FROM groups")
    List<GroupEntity> getAllGroups();

    /**
     * Inserts a new group member.
     *
     * @param member The group member entity to insert.
     * @return The row ID of the newly inserted group member.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertGroupMember(GroupMemberEntity member);

    /**
     * Inserts multiple group members.
     *
     * @param members The list of group member entities to insert.
     * @return The row IDs of the newly inserted group members.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertGroupMembers(List<GroupMemberEntity> members);

    /**
     * Retrieves all members for a specific group.
     *
     * @param groupId The ID of the group.
     * @return A list of group member entities.
     */
    @Query("SELECT * FROM group_members WHERE group_id = :groupId")
    List<GroupMemberEntity> getMembersForGroup(String groupId);

    /**
     * Removes a user from a group.
     *
     * @param groupId The ID of the group.
     * @param nodeId  The ID of the user to remove.
     * @return The number of rows deleted.
     */
    @Query("DELETE FROM group_members WHERE group_id = :groupId AND node_id = :nodeId")
    int removeMemberFromGroup(String groupId, String nodeId);
}
