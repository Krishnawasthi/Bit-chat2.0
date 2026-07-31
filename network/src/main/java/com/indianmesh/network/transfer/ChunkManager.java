/*
 * Copyright (c) 2026 Indian Mesh. All rights reserved.
 */
package com.indianmesh.network.transfer;

import androidx.annotation.NonNull;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Utility for splitting large payloads into smaller chunks suitable for transmission.
 * Each chunk is prepended with a {@link PacketHeader}.
 */
public final class ChunkManager {

    /**
     * Default chunk size representing the maximum payload per chunk (excluding header).
     */
    public static final int DEFAULT_CHUNK_PAYLOAD_SIZE = 512;

    private ChunkManager() {
        // Prevent instantiation
    }

    /**
     * Splits a given payload into a list of sequenced chunks.
     *
     * @param messageId       the unique identifier for the message
     * @param payload         the full byte array payload to split
     * @param chunkPayloadSize the maximum size of payload data per chunk
     * @return an unmodifiable list of byte arrays, each starting with a header
     * @throws IllegalArgumentException if chunk payload size is invalid or payload is empty
     */
    @NonNull
    public static List<byte[]> splitPayload(long messageId, @NonNull byte[] payload, int chunkPayloadSize) {
        if (chunkPayloadSize <= 0) {
            throw new IllegalArgumentException("chunkPayloadSize must be greater than zero");
        }
        if (payload.length == 0) {
            throw new IllegalArgumentException("payload cannot be empty");
        }

        int totalChunks = (int) Math.ceil((double) payload.length / chunkPayloadSize);
        List<byte[]> chunks = new ArrayList<>(totalChunks);

        for (int i = 0; i < totalChunks; i++) {
            int offset = i * chunkPayloadSize;
            int length = Math.min(chunkPayloadSize, payload.length - offset);

            PacketHeader header = new PacketHeader(messageId, totalChunks, i);
            byte[] headerBytes = header.toBytes();

            ByteBuffer buffer = ByteBuffer.allocate(headerBytes.length + length);
            buffer.put(headerBytes);
            buffer.put(payload, offset, length);

            chunks.add(buffer.array());
        }

        return Collections.unmodifiableList(chunks);
    }

    /**
     * Splits a given payload into chunks using the default payload size.
     *
     * @param messageId the unique identifier for the message
     * @param payload   the full byte array payload to split
     * @return an unmodifiable list of byte arrays
     * @see #splitPayload(long, byte[], int)
     */
    @NonNull
    public static List<byte[]> splitPayload(long messageId, @NonNull byte[] payload) {
        return splitPayload(messageId, payload, DEFAULT_CHUNK_PAYLOAD_SIZE);
    }
}
