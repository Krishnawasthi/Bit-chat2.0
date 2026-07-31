/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity representing an entry in the mesh routing table.
 */
@Entity(tableName = "routing_table")
public class RoutingTableEntity {

    /** The destination node identifier. */
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "destination_id")
    private final String destinationId;

    /** The next hop node identifier to reach the destination. */
    @NonNull
    @ColumnInfo(name = "next_hop_id")
    private final String nextHopId;

    /** The cost or distance to the destination. */
    @ColumnInfo(name = "metric")
    private final int metric;

    /** The timestamp when this route was last updated. */
    @ColumnInfo(name = "updated_at")
    private final long updatedAt;

    /**
     * Constructs a new RoutingTableEntity.
     *
     * @param destinationId The destination node ID.
     * @param nextHopId     The next hop node ID.
     * @param metric        The routing metric.
     * @param updatedAt     The last updated timestamp.
     */
    public RoutingTableEntity(@NonNull String destinationId, @NonNull String nextHopId,
                              int metric, long updatedAt) {
        this.destinationId = destinationId;
        this.nextHopId = nextHopId;
        this.metric = metric;
        this.updatedAt = updatedAt;
    }

    /** @return The destination node ID. */
    @NonNull
    public String getDestinationId() { return destinationId; }

    /** @return The next hop node ID. */
    @NonNull
    public String getNextHopId() { return nextHopId; }

    /** @return The routing metric. */
    public int getMetric() { return metric; }

    /** @return The last updated timestamp. */
    public long getUpdatedAt() { return updatedAt; }
}
