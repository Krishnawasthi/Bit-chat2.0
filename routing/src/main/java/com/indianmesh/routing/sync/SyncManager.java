/*
 * Copyright (c) 2026 Indian Mesh. All rights reserved.
 */
package com.indianmesh.routing.sync;

import androidx.annotation.NonNull;

import java.util.List;

/**
 * Uses BloomFilterSync when a new peer connects to reconcile states.
 */
public class SyncManager {

    private final BloomFilterSync bloomFilterSync;

    /**
     * Constructs a new SyncManager.
     *
     * @param bloomFilterSync The Bloom filter synchronization implementation.
     */
    public SyncManager(@NonNull BloomFilterSync bloomFilterSync) {
        this.bloomFilterSync = bloomFilterSync;
    }

    /**
     * Reconciles states with a newly connected peer by computing missing IDs.
     *
     * @param remoteFilter The Bloom filter received from the new peer.
     * @param localIds The list of IDs known locally.
     * @return A list of IDs that need to be sent to the peer.
     */
    @NonNull
    public List<String> reconcileWithPeer(@NonNull byte[] remoteFilter, @NonNull List<String> localIds) {
        return bloomFilterSync.getMissingIds(remoteFilter, localIds);
    }
    
    /**
     * Generates a sync payload (Bloom filter) representing the local state.
     *
     * @param localIds The list of IDs known locally.
     * @return A byte array containing the Bloom filter.
     */
    @NonNull
    public byte[] generateSyncPayload(@NonNull List<String> localIds) {
        return bloomFilterSync.generateFilter(localIds);
    }
}
