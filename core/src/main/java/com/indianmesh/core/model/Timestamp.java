/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.core.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.indianmesh.core.clock.Clock;
import com.indianmesh.core.util.Preconditions;
import java.time.Duration;

/**
 * Wraps both wall clock time and monotonic time.
 * Immutable and thread-safe.
 */
public final class Timestamp implements Comparable<Timestamp> {

    private final long epochMillis;
    private final long monotonicNanos;

    /**
     * Private constructor.
     * @param epochMillis The wall clock time in milliseconds.
     * @param monotonicNanos The monotonic time in nanoseconds.
     */
    private Timestamp(long epochMillis, long monotonicNanos) {
        this.epochMillis = epochMillis;
        this.monotonicNanos = monotonicNanos;
    }

    /**
     * Creates a Timestamp representing the current time using the provided clock.
     * @param clock The clock to use.
     * @return A new Timestamp.
     */
    @NonNull
    public static Timestamp now(@NonNull Clock clock) {
        Preconditions.checkNotNull(clock, "Clock cannot be null");
        return new Timestamp(clock.currentTimeMillis(), clock.nanoTime());
    }

    /**
     * Creates a Timestamp from epoch milliseconds. Monotonic nanos will be set to 0.
     * Note: This is primarily for deserialization where monotonic time is lost.
     * @param epochMillis The epoch time in milliseconds.
     * @return A Timestamp instance.
     */
    @NonNull
    public static Timestamp fromEpochMillis(long epochMillis) {
        return new Timestamp(epochMillis, 0L);
    }

    /**
     * Gets the wall clock time in milliseconds.
     * @return The epoch time in milliseconds.
     */
    public long getEpochMillis() {
        return epochMillis;
    }

    /**
     * Gets the monotonic time in nanoseconds.
     * @return The monotonic time in nanoseconds.
     */
    public long getMonotonicNanos() {
        return monotonicNanos;
    }

    /**
     * Checks if this timestamp is strictly after the given timestamp.
     * @param other The other timestamp.
     * @return True if this is after the other.
     */
    public boolean isAfter(@NonNull Timestamp other) {
        Preconditions.checkNotNull(other, "other cannot be null");
        return this.epochMillis > other.epochMillis;
    }

    /**
     * Checks if this timestamp is strictly before the given timestamp.
     * @param other The other timestamp.
     * @return True if this is before the other.
     */
    public boolean isBefore(@NonNull Timestamp other) {
        Preconditions.checkNotNull(other, "other cannot be null");
        return this.epochMillis < other.epochMillis;
    }

    /**
     * Calculates the duration elapsed since the given timestamp.
     * Uses monotonic time if both are valid, otherwise falls back to epoch time.
     * @param earlier The earlier timestamp.
     * @return The duration elapsed.
     */
    @NonNull
    public Duration elapsed(@NonNull Timestamp earlier) {
        Preconditions.checkNotNull(earlier, "earlier cannot be null");
        if (this.monotonicNanos != 0 && earlier.monotonicNanos != 0) {
            return Duration.ofNanos(this.monotonicNanos - earlier.monotonicNanos);
        }
        return Duration.ofMillis(this.epochMillis - earlier.epochMillis);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Timestamp other = (Timestamp) obj;
        return this.epochMillis == other.epochMillis && this.monotonicNanos == other.monotonicNanos;
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(epochMillis);
        result = 31 * result + Long.hashCode(monotonicNanos);
        return result;
    }

    @Override
    public int compareTo(@NonNull Timestamp o) {
        Preconditions.checkNotNull(o, "Cannot compare to null");
        return Long.compare(this.epochMillis, o.epochMillis);
    }

    @Override
    @NonNull
    public String toString() {
        return "Timestamp{" +
                "epochMillis=" + epochMillis +
                ", monotonicNanos=" + monotonicNanos +
                '}';
    }
}
