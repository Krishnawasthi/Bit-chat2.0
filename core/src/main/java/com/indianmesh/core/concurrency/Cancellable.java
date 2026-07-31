/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.core.concurrency;

import androidx.annotation.NonNull;
import java.util.concurrent.Future;

/**
 * Interface representing an operation that can be cancelled.
 */
public interface Cancellable {

    /**
     * Attempts to cancel execution of this task.
     */
    void cancel();

    /**
     * Returns true if this task was cancelled before it completed normally.
     * @return True if cancelled.
     */
    boolean isCancelled();

    /**
     * Creates a Cancellable from a Future.
     * @param future The Future to wrap.
     * @return A Cancellable instance.
     */
    @NonNull
    static Cancellable from(@NonNull Future<?> future) {
        if (future == null) {
            throw new NullPointerException("future cannot be null");
        }
        return new Cancellable() {
            @Override
            public void cancel() {
                future.cancel(true);
            }

            @Override
            public boolean isCancelled() {
                return future.isCancelled();
            }
        };
    }

    /**
     * Returns a Cancellable that does nothing when cancelled.
     * @return An empty Cancellable.
     */
    @NonNull
    static Cancellable empty() {
        return new Cancellable() {
            private boolean cancelled = false;
            
            @Override
            public void cancel() {
                cancelled = true;
            }

            @Override
            public boolean isCancelled() {
                return cancelled;
            }
        };
    }
}
