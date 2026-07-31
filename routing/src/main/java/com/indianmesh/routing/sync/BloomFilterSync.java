/*
 * Copyright (c) 2026 Indian Mesh. All rights reserved.
 */
package com.indianmesh.routing.sync;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements basic Bloom filter logic to compute and compare sets of known MessageIds.
 * Provides a mechanism to efficiently find missing messages between two peers.
 */
public class BloomFilterSync {

    private static final int FILTER_SIZE_BYTES = 1024;
    private static final int HASH_COUNT = 3;

    /**
     * Generates a Bloom filter from a list of known message IDs.
     *
     * @param knownIds The list of known message IDs.
     * @return A byte array representing the generated Bloom filter.
     */
    @NonNull
    public byte[] generateFilter(@NonNull List<String> knownIds) {
        byte[] filter = new byte[FILTER_SIZE_BYTES];
        for (String id : knownIds) {
            if (id != null) {
                int[] hashes = getHashes(id);
                for (int hash : hashes) {
                    int bitIndex = Math.abs(hash % (FILTER_SIZE_BYTES * 8));
                    int byteIndex = bitIndex / 8;
                    int bitOffset = bitIndex % 8;
                    filter[byteIndex] |= (1 << bitOffset);
                }
            }
        }
        return filter;
    }

    /**
     * Compares a remote Bloom filter with local IDs to determine which local IDs 
     * are likely missing from the remote peer.
     *
     * @param remoteFilter The Bloom filter from the remote peer.
     * @param localIds The list of local message IDs.
     * @return A list of message IDs that are missing from the remote filter.
     */
    @NonNull
    public List<String> getMissingIds(@NonNull byte[] remoteFilter, @NonNull List<String> localIds) {
        List<String> missingIds = new ArrayList<>();
        if (remoteFilter == null || remoteFilter.length == 0) {
            return new ArrayList<>(localIds);
        }

        int filterBitSize = remoteFilter.length * 8;
        for (String id : localIds) {
            if (id != null) {
                int[] hashes = getHashes(id);
                boolean probablyPresent = true;
                for (int hash : hashes) {
                    int bitIndex = Math.abs(hash % filterBitSize);
                    int byteIndex = bitIndex / 8;
                    int bitOffset = bitIndex % 8;

                    if (byteIndex < remoteFilter.length && (remoteFilter[byteIndex] & (1 << bitOffset)) == 0) {
                        probablyPresent = false;
                        break;
                    }
                }
                if (!probablyPresent) {
                    missingIds.add(id);
                }
            }
        }
        return missingIds;
    }

    @NonNull
    private int[] getHashes(@NonNull String value) {
        int[] hashes = new int[HASH_COUNT];
        int h = value.hashCode();
        hashes[0] = h;
        hashes[1] = h * 31;
        hashes[2] = h * 31 * 31;
        return hashes;
    }
}
