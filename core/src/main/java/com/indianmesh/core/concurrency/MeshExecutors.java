/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.core.concurrency;

import androidx.annotation.NonNull;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Provides named thread pools for different execution contexts within the mesh.
 */
public final class MeshExecutors {

    private static volatile ExecutorService ioExecutor;
    private static volatile ExecutorService computationExecutor;
    private static volatile ExecutorService meshExecutor;
    private static volatile ExecutorService serialExecutor;

    private MeshExecutors() {
        // Prevent instantiation
    }

    /**
     * Gets a cached thread pool optimized for IO operations.
     * @return The IO ExecutorService.
     */
    @NonNull
    public static ExecutorService io() {
        if (ioExecutor == null) {
            synchronized (MeshExecutors.class) {
                if (ioExecutor == null) {
                    ioExecutor = Executors.newCachedThreadPool(new NamedThreadFactory("MeshExecutors-io"));
                }
            }
        }
        return ioExecutor;
    }

    /**
     * Gets a fixed thread pool optimized for CPU intensive computation.
     * @return The Computation ExecutorService.
     */
    @NonNull
    public static ExecutorService computation() {
        if (computationExecutor == null) {
            synchronized (MeshExecutors.class) {
                if (computationExecutor == null) {
                    int cores = Runtime.getRuntime().availableProcessors();
                    computationExecutor = Executors.newFixedThreadPool(
                            Math.max(2, cores), new NamedThreadFactory("MeshExecutors-computation"));
                }
            }
        }
        return computationExecutor;
    }

    /**
     * Gets a single-thread executor dedicated to internal mesh state operations.
     * @return The Mesh ExecutorService.
     */
    @NonNull
    public static ExecutorService mesh() {
        if (meshExecutor == null) {
            synchronized (MeshExecutors.class) {
                if (meshExecutor == null) {
                    meshExecutor = Executors.newSingleThreadExecutor(new NamedThreadFactory("MeshExecutors-mesh"));
                }
            }
        }
        return meshExecutor;
    }

    /**
     * Gets a single-thread executor dedicated to ordered sequential operations.
     * @return The Serial ExecutorService.
     */
    @NonNull
    public static ExecutorService serial() {
        if (serialExecutor == null) {
            synchronized (MeshExecutors.class) {
                if (serialExecutor == null) {
                    serialExecutor = Executors.newSingleThreadExecutor(new NamedThreadFactory("MeshExecutors-serial"));
                }
            }
        }
        return serialExecutor;
    }

    /**
     * Shuts down all thread pools immediately.
     */
    public static synchronized void shutdown() {
        if (ioExecutor != null) {
            ioExecutor.shutdownNow();
            ioExecutor = null;
        }
        if (computationExecutor != null) {
            computationExecutor.shutdownNow();
            computationExecutor = null;
        }
        if (meshExecutor != null) {
            meshExecutor.shutdownNow();
            meshExecutor = null;
        }
        if (serialExecutor != null) {
            serialExecutor.shutdownNow();
            serialExecutor = null;
        }
    }

    /**
     * A ThreadFactory that names threads with a specific prefix.
     */
    private static class NamedThreadFactory implements ThreadFactory {
        private final String prefix;
        private final AtomicInteger counter = new AtomicInteger(1);

        NamedThreadFactory(@NonNull String prefix) {
            this.prefix = prefix;
        }

        @Override
        public Thread newThread(@NonNull Runnable r) {
            return new Thread(r, prefix + "-" + counter.getAndIncrement());
        }
    }
}
