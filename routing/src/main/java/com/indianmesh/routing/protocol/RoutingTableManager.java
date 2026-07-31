/*
 * Copyright (c) 2026 Indian Mesh. All rights reserved.
 */
package com.indianmesh.routing.protocol;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Manages the routing table for the mesh network.
 * Provides thread-safe access to a mapping of destination IDs to next-hop peer IDs.
 */
public final class RoutingTableManager {

    /**
     * Thread-safe map storing the routing entries (Destination ID -> Next Hop ID).
     */
    @NonNull
    private final ConcurrentHashMap<String, String> routingTable;

    /**
     * Constructs a new {@link RoutingTableManager} with an empty routing table.
     */
    public RoutingTableManager() {
        this.routingTable = new ConcurrentHashMap<>();
    }

    /**
     * Updates or adds a route to the routing table.
     *
     * @param destinationId The unique identifier of the target destination.
     * @param nextHopId     The unique identifier of the next hop to reach the destination.
     */
    public void updateRoute(@NonNull String destinationId, @NonNull String nextHopId) {
        routingTable.put(destinationId, nextHopId);
    }

    /**
     * Retrieves the next hop for a given destination ID.
     *
     * @param destinationId The unique identifier of the target destination.
     * @return The unique identifier of the next hop, or {@code null} if no route is found.
     */
    @Nullable
    public String getNextHop(@NonNull String destinationId) {
        return routingTable.get(destinationId);
    }

    /**
     * Removes a route from the routing table.
     *
     * @param destinationId The unique identifier of the destination to remove.
     */
    public void removeRoute(@NonNull String destinationId) {
        routingTable.remove(destinationId);
    }
    
    /**
     * Clears all routes from the routing table.
     */
    public void clearRoutes() {
        routingTable.clear();
    }
    
    /**
     * Gets a read-only view of the current routing table.
     * 
     * @return A map containing all current routing entries.
     */
    @NonNull
    public Map<String, String> getRoutes() {
        return Collections.unmodifiableMap(routingTable);
    }
}
