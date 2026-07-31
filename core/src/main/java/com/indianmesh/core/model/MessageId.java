/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.core.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.UUID;
import com.indianmesh.core.util.Preconditions;

/**
 * UUID-based message identifier. Immutable and thread-safe.
 */
public final class MessageId implements Comparable<MessageId> {

    private final byte[] bytes;

    /**
     * Private constructor. Use factory methods.
     * @param bytes A 16-byte array representing the message ID.
     */
    private MessageId(@NonNull byte[] bytes) {
        Preconditions.checkArgument(bytes.length == 16, "MessageId must be exactly 16 bytes");
        this.bytes = Arrays.copyOf(bytes, bytes.length);
    }

    /**
     * Generates a new random MessageId.
     * @return A newly generated MessageId.
     */
    @NonNull
    public static MessageId generate() {
        UUID uuid = UUID.randomUUID();
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return new MessageId(bb.array());
    }

    /**
     * Creates a MessageId from a standard UUID string format.
     * @param uuidString The UUID string.
     * @return A MessageId instance.
     */
    @NonNull
    public static MessageId fromString(@NonNull String uuidString) {
        Preconditions.checkNotNull(uuidString, "uuidString cannot be null");
        UUID uuid = UUID.fromString(uuidString);
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return new MessageId(bb.array());
    }

    /**
     * Creates a MessageId from a byte array.
     * @param bytes A 16-byte array.
     * @return A MessageId instance.
     */
    @NonNull
    public static MessageId fromBytes(@NonNull byte[] bytes) {
        Preconditions.checkNotNull(bytes, "bytes cannot be null");
        return new MessageId(bytes);
    }

    /**
     * Returns the raw byte array of this MessageId.
     * @return A 16-byte array.
     */
    @NonNull
    public byte[] toBytes() {
        return Arrays.copyOf(bytes, bytes.length);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MessageId other = (MessageId) obj;
        return Arrays.equals(this.bytes, other.bytes);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(bytes);
    }

    @Override
    @NonNull
    public String toString() {
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        long mostSigBits = bb.getLong();
        long leastSigBits = bb.getLong();
        return new UUID(mostSigBits, leastSigBits).toString();
    }

    @Override
    public int compareTo(@NonNull MessageId o) {
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
