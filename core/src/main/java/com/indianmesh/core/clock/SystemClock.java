/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.core.clock;

/**
 * Implementation of Clock using the real system time.
 */
public final class SystemClock implements Clock {

    private static final SystemClock INSTANCE = new SystemClock();

    private SystemClock() {
        // Prevent instantiation
    }

    /**
     * Gets the singleton instance of SystemClock.
     * @return The SystemClock instance.
     */
    public static SystemClock getInstance() {
        return INSTANCE;
    }

    @Override
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    @Override
    public long nanoTime() {
        return System.nanoTime();
    }
}
