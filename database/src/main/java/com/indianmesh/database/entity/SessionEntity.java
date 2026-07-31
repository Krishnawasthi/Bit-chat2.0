/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity representing an active encryption session.
 */
@Entity(tableName = "sessions")
public class SessionEntity {

    /** The unique session identifier. */
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "session_id")
    private final String sessionId;

    /** The remote node identifier for this session. */
    @NonNull
    @ColumnInfo(name = "remote_node_id")
    private final String remoteNodeId;

    /** The serialized session state bytes. */
    @NonNull
    @ColumnInfo(name = "session_state", typeAffinity = ColumnInfo.BLOB)
    private final byte[] sessionState;

    /** The timestamp when this session was created or last updated. */
    @ColumnInfo(name = "updated_at")
    private final long updatedAt;

    /**
     * Constructs a new SessionEntity.
     *
     * @param sessionId    The session ID.
     * @param remoteNodeId The remote node ID.
     * @param sessionState The serialized session state.
     * @param updatedAt    The last updated timestamp.
     */
    public SessionEntity(@NonNull String sessionId, @NonNull String remoteNodeId,
                         @NonNull byte[] sessionState, long updatedAt) {
        this.sessionId = sessionId;
        this.remoteNodeId = remoteNodeId;
        this.sessionState = sessionState;
        this.updatedAt = updatedAt;
    }

    /** @return The session ID. */
    @NonNull
    public String getSessionId() { return sessionId; }

    /** @return The remote node ID. */
    @NonNull
    public String getRemoteNodeId() { return remoteNodeId; }

    /** @return The serialized session state. */
    @NonNull
    public byte[] getSessionState() { return sessionState; }

    /** @return The last updated timestamp. */
    public long getUpdatedAt() { return updatedAt; }
}
