/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.core.clock;

/**
 * Interface representing a system clock.
 * Useful for testability by replacing real system time with a fake clock.
 */
public interface Clock {
    
    /**
     * Returns the current time in milliseconds.
     * @return Current time in epoch milliseconds.
     */
    long currentTimeMillis();

    /**
     * Returns the current monotonic time in nanoseconds.
     * @return Current monotonic time in nanoseconds.
     */
    long nanoTime();
}
