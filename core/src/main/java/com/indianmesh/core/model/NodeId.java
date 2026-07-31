/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.core.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.UUID;
import com.indianmesh.core.util.HexUtils;
import com.indianmesh.core.util.Preconditions;

/**
 * Represents a unique 128-bit node identifier (device identity).
 * Backed by a 16-byte array internally. Immutable and thread-safe.
 */
public final class NodeId implements Comparable<NodeId> {

    private final byte[] bytes;

    /**
     * Private constructor. Use factory methods instead.
     * @param bytes A 16-byte array representing the node ID.
     */
    private NodeId(@NonNull byte[] bytes) {
        Preconditions.checkArgument(bytes.length == 16, "NodeId must be exactly 16 bytes");
        this.bytes = Arrays.copyOf(bytes, bytes.length);
    }

    /**
     * Generates a new random UUID-based NodeId.
     * @return A newly generated NodeId.
     */
    @NonNull
    public static NodeId generate() {
        UUID uuid = UUID.randomUUID();
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return new NodeId(bb.array());
    }

    /**
     * Parses a NodeId from a hex string.
     * @param hex The 32-character hex string.
     * @return A NodeId instance.
     * @throws IllegalArgumentException if the hex string is invalid or not 32 characters.
     */
    @NonNull
    public static NodeId fromHex(@NonNull String hex) {
        Preconditions.checkNotNull(hex, "hex string cannot be null");
        byte[] parsedBytes = HexUtils.fromHex(hex);
        return new NodeId(parsedBytes);
    }

    /**
     * Creates a NodeId from a byte array.
     * @param bytes A 16-byte array.
     * @return A NodeId instance.
     */
    @NonNull
    public static NodeId fromBytes(@NonNull byte[] bytes) {
        Preconditions.checkNotNull(bytes, "bytes cannot be null");
        return new NodeId(bytes);
    }

    /**
     * Returns the hex string representation of this NodeId.
     * @return A 32-character lower-case hex string.
     */
    @NonNull
    public String toHex() {
        return HexUtils.toHex(bytes);
    }

    /**
     * Returns the raw byte array of this NodeId.
     * @return A 16-byte array.
     */
    @NonNull
    public byte[] toBytes() {
        return Arrays.copyOf(bytes, bytes.length);
    }

    /**
     * Returns the first 8 bytes of the NodeId as a long.
     * Useful for packet headers where space is limited.
     * @return The first 8 bytes as a long.
     */
    public long toTruncatedLong() {
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        return bb.getLong();
    }

    /**
     * Generates a human-readable 4-word fingerprint for this NodeId.
     * Currently returns a placeholder derived from hex, to be replaced with a word list.
     * @return A 4-word fingerprint string.
     */
    @NonNull
    public String getFingerprint() {
        String hex = toHex();
        return hex.substring(0, 4) + "-" + hex.substring(4, 8) + "-" + 
               hex.substring(8, 12) + "-" + hex.substring(12, 16);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        NodeId other = (NodeId) obj;
        return Arrays.equals(this.bytes, other.bytes);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(bytes);
    }

    @Override
    @NonNull
    public String toString() {
        return "NodeId{" + toHex() + "}";
    }

    @Override
    public int compareTo(@NonNull NodeId o) {
        Preconditions.checkNotNull(o, "Cannot compare to null");
        for (int i = 0; i < 16; i++) {
            int thisByte = this.bytes[i] & 0xFF;
            int otherByte = o.bytes[i] & 0xFF;
            if (thisByte != otherByte) {
                return Integer.compare(thisByte, otherByte);
            }
        }
        return 0;
    }
}
