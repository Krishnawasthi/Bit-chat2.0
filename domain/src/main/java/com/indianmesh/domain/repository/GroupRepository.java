/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.repository;

import com.indianmesh.domain.model.Group;
import com.indianmesh.domain.model.GroupMember;
import com.indianmesh.domain.model.GroupRole;
import com.indianmesh.core.model.NodeId;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Repository interface for managing groups.
 */
public interface GroupRepository {

    /**
     * Creates a new group.
     *
     * @param name the name of the group
     * @param description the description of the group
     * @param memberIds the initial members of the group
     * @return a CompletableFuture containing the newly created group
     */
    CompletableFuture<Group> createGroup(String name, String description, List<NodeId> memberIds);

    /**
     * Retrieves a group by its ID.
     *
     * @param groupId the ID of the group
     * @return a CompletableFuture containing the group
     */
    CompletableFuture<Group> getGroupById(String groupId);

    /**
     * Retrieves a group associated with a specific conversation ID.
     *
     * @param conversationId the conversation ID
     * @return a CompletableFuture containing the group
     */
    CompletableFuture<Group> getGroupByConversationId(String conversationId);

    /**
     * Updates an existing group.
     *
     * @param group the group to update
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> updateGroup(Group group);

    /**
     * Deletes a group.
     *
     * @param groupId the ID of the group to delete
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> deleteGroup(String groupId);

    /**
     * Retrieves the members of a group.
     *
     * @param groupId the ID of the group
     * @return a CompletableFuture containing the list of group members
     */
    CompletableFuture<List<GroupMember>> getGroupMembers(String groupId);

    /**
     * Adds a member to a group.
     *
     * @param groupId the ID of the group
     * @param userId the ID of the user to add
     * @param role the role of the new member
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> addMember(String groupId, NodeId userId, GroupRole role);

    /**
     * Removes a member from a group.
     *
     * @param groupId the ID of the group
     * @param userId the ID of the user to remove
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> removeMember(String groupId, NodeId userId);

    /**
     * Updates the role of a group member.
     *
     * @param groupId the ID of the group
     * @param userId the ID of the user
     * @param role the new role
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> updateMemberRole(String groupId, NodeId userId, GroupRole role);

    /**
     * Retrieves a group by its invite code.
     *
     * @param inviteCode the invite code
     * @return a CompletableFuture containing the group
     */
    CompletableFuture<Group> getGroupByInviteCode(String inviteCode);
}
