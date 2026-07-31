/*
 * Copyright (c) 2026 Indian Mesh. All rights reserved.
 */
package com.indianmesh.network.transfer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility for reassembling sequential chunks back into their original complete payload.
 */
public final class PayloadAssembler {

    private final long expectedMessageId;
    private final int expectedTotalChunks;
    private final Map<Integer, byte[]> receivedChunks;
    private int totalReceivedBytes;

    /**
     * Constructs a new {@link PayloadAssembler} for a specific message.
     *
     * @param messageId   the unique identifier for the expected message
     * @param totalChunks the total number of chunks expected for this message
     * @throws IllegalArgumentException if totalChunks is zero or negative
     */
    public PayloadAssembler(long messageId, int totalChunks) {
        if (totalChunks <= 0) {
            throw new IllegalArgumentException("totalChunks must be greater than zero");
        }
        this.expectedMessageId = messageId;
        this.expectedTotalChunks = totalChunks;
        this.receivedChunks = new HashMap<>(totalChunks);
        this.totalReceivedBytes = 0;
    }

    /**
     * Adds a newly received chunk to this assembler.
     *
     * @param chunkData the complete chunk (header + payload)
     * @return {@code true} if all chunks have been received and assembly is complete; {@code false} otherwise
     * @throws IllegalArgumentException if the chunk data is invalid or doesn't belong to this message
     */
    public boolean addChunk(@NonNull byte[] chunkData) {
        PacketHeader header = PacketHeader.fromBytes(chunkData);

        if (header.getMessageId() != expectedMessageId) {
            throw new IllegalArgumentException("Chunk message ID does not match expected message ID");
        }
        if (header.getTotalChunks() != expectedTotalChunks) {
            throw new IllegalArgumentException("Chunk total chunks does not match expected total chunks");
        }

        int sequenceNumber = header.getSequenceNumber();
        if (receivedChunks.containsKey(sequenceNumber)) {
            return isComplete();
        }

        int payloadLength = chunkData.length - PacketHeader.HEADER_SIZE;
        byte[] payloadChunk = new byte[payloadLength];
        System.arraycopy(chunkData, PacketHeader.HEADER_SIZE, payloadChunk, 0, payloadLength);

        receivedChunks.put(sequenceNumber, payloadChunk);
        totalReceivedBytes += payloadLength;

        return isComplete();
    }

    /**
     * Checks if all expected chunks have been successfully received.
     *
     * @return {@code true} if complete, {@code false} otherwise
     */
    public boolean isComplete() {
        return receivedChunks.size() == expectedTotalChunks;
    }

    /**
     * Assembles and returns the complete, contiguous payload.
     *
     * @return the full payload, or {@code null} if not all chunks have been received yet
     * @throws IllegalStateException if the assembler is marked complete but chunks are missing internally
     */
    @Nullable
    public byte[] getAssembledPayload() {
        if (!isComplete()) {
            return null;
        }

        ByteBuffer buffer = ByteBuffer.allocate(totalReceivedBytes);
        for (int i = 0; i < expectedTotalChunks; i++) {
            byte[] chunk = receivedChunks.get(i);
            if (chunk == null) {
                throw new IllegalStateException("Missing chunk " + i + " despite being marked as complete");
            }
            buffer.put(chunk);
        }

        return buffer.array();
    }

    /**
     * Gets the expected message ID for this assembler.
     *
     * @return the message ID
     */
    public long getMessageId() {
        return expectedMessageId;
    }
}
