/*
 * Copyright (c) 2026 Indian Mesh. All rights reserved.
 */
package com.indianmesh.routing.orchestrator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.PriorityQueue;

/**
 * Priority queue to buffer outgoing payloads when no peers are available.
 */
public class TransmissionQueue {

    private final PriorityQueue<QueueItem> queue;

    /**
     * Initializes the TransmissionQueue.
     */
    public TransmissionQueue() {
        this.queue = new PriorityQueue<>();
    }

    /**
     * Enqueues a payload with a given priority. 
     * Higher priority values are dequeued first.
     *
     * @param payload  The byte array of the outgoing message payload.
     * @param priority The priority of the message.
     */
    public synchronized void enqueue(@NonNull byte[] payload, int priority) {
        queue.offer(new QueueItem(payload, priority));
    }

    /**
     * Dequeues the highest priority payload.
     *
     * @return The raw payload byte array, or null if the queue is empty.
     */
    @Nullable
    public synchronized byte[] dequeue() {
        QueueItem item = queue.poll();
        return item != null ? item.getPayload() : null;
    }

    /**
     * Represents an item in the transmission queue.
     */
    private static final class QueueItem implements Comparable<QueueItem> {
        private final byte[] payload;
        private final int priority;

        /**
         * Constructs a QueueItem.
         *
         * @param payload The raw payload.
         * @param priority The priority of the payload.
         */
        public QueueItem(@NonNull byte[] payload, int priority) {
            this.payload = payload;
            this.priority = priority;
        }

        /**
         * Returns the payload.
         *
         * @return The payload.
         */
        @NonNull
        public byte[] getPayload() {
            return payload;
        }

        /**
         * Returns the priority.
         *
         * @return The priority.
         */
        public int getPriority() {
            return priority;
        }

        @Override
        public int compareTo(QueueItem other) {
            // Higher priority first
            return Integer.compare(other.priority, this.priority);
        }
    }
}
