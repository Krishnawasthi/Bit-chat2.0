/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity representing a network peer.
 */
@Entity(tableName = "peers")
public class PeerEntity {

    /** The unique node identifier of the peer. */
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "node_id")
    private final String nodeId;

    /** The display name of the peer. */
    @Nullable
    @ColumnInfo(name = "display_name")
    private final String displayName;

    /** The state of connection with the peer (e.g., CONNECTED, DISCONNECTED). */
    @NonNull
    @ColumnInfo(name = "connection_state")
    private final String connectionState;

    /** The timestamp of the last seen time. */
    @ColumnInfo(name = "last_seen")
    private final long lastSeen;

    /**
     * Constructs a new PeerEntity.
     *
     * @param nodeId          The node ID.
     * @param displayName     The display name.
     * @param connectionState The connection state.
     * @param lastSeen        The last seen timestamp.
     */
    public PeerEntity(@NonNull String nodeId, @Nullable String displayName,
                      @NonNull String connectionState, long lastSeen) {
        this.nodeId = nodeId;
        this.displayName = displayName;
        this.connectionState = connectionState;
        this.lastSeen = lastSeen;
    }

    /** @return The node ID. */
    @NonNull
    public String getNodeId() { return nodeId; }

    /** @return The display name. */
    @Nullable
    public String getDisplayName() { return displayName; }

    /** @return The connection state string. */
    @NonNull
    public String getConnectionState() { return connectionState; }

    /** @return The last seen timestamp. */
    public long getLastSeen() { return lastSeen; }
}
