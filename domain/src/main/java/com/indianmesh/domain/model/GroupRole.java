/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.model;

/**
 * Represents the role of a user in a group.
 */
public enum GroupRole {
    /** The owner of the group. */
    OWNER(true, true, true, true),
    /** An administrator of the group. */
    ADMIN(true, true, true, true),
    /** A regular member of the group. */
    MEMBER(false, false, false, false);

    private final boolean canModifyGroup;
    private final boolean canRemoveMembers;
    private final boolean canAddMembers;
    private final boolean canDeleteMessages;

    /**
     * Constructs a GroupRole.
     *
     * @param canModifyGroup    True if the role can modify group details.
     * @param canRemoveMembers  True if the role can remove members.
     * @param canAddMembers     True if the role can add members.
     * @param canDeleteMessages True if the role can delete messages.
     */
    GroupRole(boolean canModifyGroup, boolean canRemoveMembers, boolean canAddMembers, boolean canDeleteMessages) {
        this.canModifyGroup = canModifyGroup;
        this.canRemoveMembers = canRemoveMembers;
        this.canAddMembers = canAddMembers;
        this.canDeleteMessages = canDeleteMessages;
    }

    /**
     * Checks if this role can modify group details.
     *
     * @return True if permitted.
     */
    public boolean canModifyGroup() {
        return canModifyGroup;
    }

    /**
     * Checks if this role can remove members.
     *
     * @return True if permitted.
     */
    public boolean canRemoveMembers() {
        return canRemoveMembers;
    }

    /**
     * Checks if this role can add members.
     *
     * @return True if permitted.
     */
    public boolean canAddMembers() {
        return canAddMembers;
    }

    /**
     * Checks if this role can delete messages.
     *
     * @return True if permitted.
     */
    public boolean canDeleteMessages() {
        return canDeleteMessages;
    }
}
