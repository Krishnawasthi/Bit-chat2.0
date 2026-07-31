/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity representing a user contact.
 */
@Entity(tableName = "contacts")
public class ContactEntity {

    /** The node identifier of the contact. */
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "node_id")
    private final String nodeId;

    /** A custom alias name assigned by the local user. */
    @Nullable
    @ColumnInfo(name = "alias_name")
    private final String aliasName;

    /** Whether this contact is blocked. */
    @ColumnInfo(name = "is_blocked")
    private final boolean isBlocked;

    /** Whether this contact is marked as a favorite. */
    @ColumnInfo(name = "is_favorite")
    private final boolean isFavorite;

    /**
     * Constructs a new ContactEntity.
     *
     * @param nodeId     The contact's node ID.
     * @param aliasName  The custom alias name.
     * @param isBlocked  True if blocked.
     * @param isFavorite True if favorite.
     */
    public ContactEntity(@NonNull String nodeId, @Nullable String aliasName,
                         boolean isBlocked, boolean isFavorite) {
        this.nodeId = nodeId;
        this.aliasName = aliasName;
        this.isBlocked = isBlocked;
        this.isFavorite = isFavorite;
    }

    /** @return The node ID. */
    @NonNull
    public String getNodeId() { return nodeId; }

    /** @return The custom alias name. */
    @Nullable
    public String getAliasName() { return aliasName; }

    /** @return True if blocked. */
    public boolean isBlocked() { return isBlocked; }

    /** @return True if favorite. */
    public boolean isFavorite() { return isFavorite; }
}
