/*
 * Copyright (c) 2026 Indian Mesh. All rights reserved.
 */
package com.indianmesh.network.transfer;

import androidx.annotation.NonNull;

import java.nio.ByteBuffer;
import java.util.Objects;

/**
 * Represents the header of a payload chunk, containing sequence and metadata.
 * Total size is 16 bytes: 8 bytes for message ID, 4 for total chunks, 4 for sequence number.
 */
public final class PacketHeader {

    /**
     * The constant size of this header when serialized, in bytes.
     */
    public static final int HEADER_SIZE = 16;

    private final long messageId;
    private final int totalChunks;
    private final int sequenceNumber;

    /**
     * Constructs a new {@link PacketHeader}.
     *
     * @param messageId      the unique identifier for the entire message payload
     * @param totalChunks    the total number of chunks this message is divided into
     * @param sequenceNumber the sequence index of this specific chunk (0-indexed)
     */
    public PacketHeader(long messageId, int totalChunks, int sequenceNumber) {
        if (totalChunks <= 0) {
            throw new IllegalArgumentException("totalChunks must be greater than zero");
        }
        if (sequenceNumber < 0 || sequenceNumber >= totalChunks) {
            throw new IllegalArgumentException("sequenceNumber must be between 0 and totalChunks - 1");
        }
        this.messageId = messageId;
        this.totalChunks = totalChunks;
        this.sequenceNumber = sequenceNumber;
    }

    /**
     * Gets the unique identifier for the message.
     *
     * @return the message ID
     */
    public long getMessageId() {
        return messageId;
    }

    /**
     * Gets the total number of chunks for the message.
     *
     * @return the total chunk count
     */
    public int getTotalChunks() {
        return totalChunks;
    }

    /**
     * Gets the sequence number (0-indexed) of this chunk.
     *
     * @return the sequence number
     */
    public int getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Serializes this header into a byte array.
     *
     * @return a byte array of size {@link #HEADER_SIZE}
     */
    @NonNull
    public byte[] toBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(HEADER_SIZE);
        buffer.putLong(messageId);
        buffer.putInt(totalChunks);
        buffer.putInt(sequenceNumber);
        return buffer.array();
    }

    /**
     * Deserializes a header from a given byte array.
     *
     * @param data the byte array containing the header data at the start
     * @return the reconstructed {@link PacketHeader}
     * @throws IllegalArgumentException if the array is too short
     */
    @NonNull
    public static PacketHeader fromBytes(@NonNull byte[] data) {
        if (data.length < HEADER_SIZE) {
            throw new IllegalArgumentException("Data is too short to contain a valid PacketHeader");
        }
        ByteBuffer buffer = ByteBuffer.wrap(data);
        long messageId = buffer.getLong();
        int totalChunks = buffer.getInt();
        int sequenceNumber = buffer.getInt();
        return new PacketHeader(messageId, totalChunks, sequenceNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PacketHeader that = (PacketHeader) o;
        return messageId == that.messageId &&
                totalChunks == that.totalChunks &&
                sequenceNumber == that.sequenceNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, totalChunks, sequenceNumber);
    }
}
