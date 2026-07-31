/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.core.concurrency;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.indianmesh.core.util.Preconditions;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A bounded priority queue for scheduling tasks.
 * Thread-safe using ReentrantLock and Conditions.
 */
public final class TaskQueue {

    private final PriorityQueue<PrioritizedTask> queue;
    private final int capacity;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

    /**
     * Constructs a TaskQueue with the given capacity.
     * @param capacity The maximum number of tasks.
     */
    public TaskQueue(int capacity) {
        Preconditions.checkPositive(capacity, "capacity must be positive");
        this.capacity = capacity;
        this.queue = new PriorityQueue<>();
    }

    /**
     * Enqueues a task with the given priority.
     * Higher numerical values indicate higher priority.
     * Blocks if the queue is full.
     * @param task The task.
     * @param priority The priority.
     * @throws InterruptedException if interrupted while waiting.
     */
    public void enqueue(@NonNull Runnable task, int priority) throws InterruptedException {
        Preconditions.checkNotNull(task, "task cannot be null");
        lock.lockInterruptibly();
        try {
            while (queue.size() >= capacity) {
                notFull.await();
            }
            queue.offer(new PrioritizedTask(task, priority));
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * Dequeues the highest priority task, blocking if the queue is empty.
     * @return The runnable task.
     * @throws InterruptedException if interrupted while waiting.
     */
    @NonNull
    public Runnable dequeue() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (queue.isEmpty()) {
                notEmpty.await();
            }
            Runnable task = queue.poll().task;
            notFull.signal();
            return task;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Attempts to dequeue a task, waiting up to the specified timeout.
     * @param timeout The timeout duration.
     * @param unit The time unit.
     * @return The task, or null if the timeout elapsed.
     * @throws InterruptedException if interrupted while waiting.
     */
    @Nullable
    public Runnable tryDequeue(long timeout, @NonNull TimeUnit unit) throws InterruptedException {
        Preconditions.checkNotNull(unit, "unit cannot be null");
        long nanos = unit.toNanos(timeout);
        lock.lockInterruptibly();
        try {
            while (queue.isEmpty()) {
                if (nanos <= 0) {
                    return null;
                }
                nanos = notEmpty.awaitNanos(nanos);
            }
            Runnable task = queue.poll().task;
            notFull.signal();
            return task;
        } finally {
            lock.unlock();
        }
    }

    /**
     * Returns the current number of tasks in the queue.
     * @return The size.
     */
    public int size() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }

    /**
     * Clears all tasks from the queue.
     */
    public void clear() {
        lock.lock();
        try {
            queue.clear();
            notFull.signalAll();
        } finally {
            lock.unlock();
        }
    }

    /**
     * Checks if the queue is empty.
     * @return True if empty, false otherwise.
     */
    public boolean isEmpty() {
        lock.lock();
        try {
            return queue.isEmpty();
        } finally {
            lock.unlock();
        }
    }

    /**
     * Wrapper for a prioritized task.
     */
    private static class PrioritizedTask implements Comparable<PrioritizedTask> {
        final Runnable task;
        final int priority;
        final long sequenceNumber;
        
        static final java.util.concurrent.atomic.AtomicLong seq = new java.util.concurrent.atomic.AtomicLong(0);

        PrioritizedTask(@NonNull Runnable task, int priority) {
            this.task = task;
            this.priority = priority;
            this.sequenceNumber = seq.getAndIncrement();
        }

        @Override
        public int compareTo(PrioritizedTask o) {
            int res = Integer.compare(o.priority, this.priority);
            if (res == 0) {
                res = Long.compare(this.sequenceNumber, o.sequenceNumber);
            }
            return res;
        }
    }
}
