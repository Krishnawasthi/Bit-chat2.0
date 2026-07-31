/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.core.config;

import androidx.annotation.NonNull;
import com.indianmesh.core.util.Preconditions;

/**
 * Configuration parameters for the mesh network.
 * Instances of this class are immutable. Use the Builder to construct.
 */
public final class MeshConfig {

    /** Default max time to live. */
    public final int maxTtl;
    /** Default max hop count. */
    public final int maxHopCount;
    /** Default message expiry in milliseconds. */
    public final long messageExpiryMillis;
    /** Default max retry count. */
    public final int maxRetryCount;
    /** Default initial retry delay in milliseconds. */
    public final long initialRetryDelayMillis;
    /** Default max retry delay in milliseconds. */
    public final long maxRetryDelayMillis;
    /** Default max group size. */
    public final int maxGroupSize;
    /** Default BLE scan interval in milliseconds. */
    public final int bleScanIntervalMs;
    /** Default BLE advertise interval in milliseconds. */
    public final int bleAdvertiseIntervalMs;
    /** Default heartbeat interval in milliseconds. */
    public final int heartbeatIntervalMs;
    /** Default maximum attachment size in bytes. */
    public final long maxAttachmentSizeBytes;
    /** Default deduplication cache size. */
    public final int deduplicationCacheSize;
    /** Default spray and wait copies. */
    public final int sprayAndWaitCopies;
    /** Default max stored bundles. */
    public final int maxStoredBundles;
    /** Default storage limit in bytes. */
    public final long storageLimitBytes;
    /** Default debug mode. */
    public final boolean debugMode;

    private MeshConfig(Builder builder) {
        this.maxTtl = builder.maxTtl;
        this.maxHopCount = builder.maxHopCount;
        this.messageExpiryMillis = builder.messageExpiryMillis;
        this.maxRetryCount = builder.maxRetryCount;
        this.initialRetryDelayMillis = builder.initialRetryDelayMillis;
        this.maxRetryDelayMillis = builder.maxRetryDelayMillis;
        this.maxGroupSize = builder.maxGroupSize;
        this.bleScanIntervalMs = builder.bleScanIntervalMs;
        this.bleAdvertiseIntervalMs = builder.bleAdvertiseIntervalMs;
        this.heartbeatIntervalMs = builder.heartbeatIntervalMs;
        this.maxAttachmentSizeBytes = builder.maxAttachmentSizeBytes;
        this.deduplicationCacheSize = builder.deduplicationCacheSize;
        this.sprayAndWaitCopies = builder.sprayAndWaitCopies;
        this.maxStoredBundles = builder.maxStoredBundles;
        this.storageLimitBytes = builder.storageLimitBytes;
        this.debugMode = builder.debugMode;
    }

    /**
     * Builder for MeshConfig.
     */
    public static final class Builder {
        private int maxTtl = 7;
        private int maxHopCount = 7;
        private long messageExpiryMillis = 72 * 60 * 60 * 1000L; // 72 hours
        private int maxRetryCount = 5;
        private long initialRetryDelayMillis = 500L;
        private long maxRetryDelayMillis = 300_000L;
        private int maxGroupSize = 256;
        private int bleScanIntervalMs = 5000;
        private int bleAdvertiseIntervalMs = 1000;
        private int heartbeatIntervalMs = 30_000;
        private long maxAttachmentSizeBytes = 10 * 1024 * 1024L; // 10MB
        private int deduplicationCacheSize = 10_000;
        private int sprayAndWaitCopies = 8;
        private int maxStoredBundles = 5000;
        private long storageLimitBytes = 50 * 1024 * 1024L; // 50MB
        private boolean debugMode = false;

        public Builder() {}

        @NonNull
        public Builder setMaxTtl(int maxTtl) {
            this.maxTtl = Preconditions.checkPositive(maxTtl, "maxTtl must be positive");
            return this;
        }

        @NonNull
        public Builder setMaxHopCount(int maxHopCount) {
            this.maxHopCount = Preconditions.checkPositive(maxHopCount, "maxHopCount must be positive");
            return this;
        }

        @NonNull
        public Builder setMessageExpiryMillis(long messageExpiryMillis) {
            Preconditions.checkArgument(messageExpiryMillis > 0, "messageExpiryMillis must be positive");
            this.messageExpiryMillis = messageExpiryMillis;
            return this;
        }

        @NonNull
        public Builder setMaxRetryCount(int maxRetryCount) {
            this.maxRetryCount = Preconditions.checkArgument(maxRetryCount >= 0, "maxRetryCount cannot be negative") ? maxRetryCount : this.maxRetryCount;
            this.maxRetryCount = maxRetryCount;
            return this;
        }

        @NonNull
        public Builder setInitialRetryDelayMillis(long initialRetryDelayMillis) {
            Preconditions.checkArgument(initialRetryDelayMillis >= 0, "initialRetryDelayMillis cannot be negative");
            this.initialRetryDelayMillis = initialRetryDelayMillis;
            return this;
        }

        @NonNull
        public Builder setMaxRetryDelayMillis(long maxRetryDelayMillis) {
            Preconditions.checkArgument(maxRetryDelayMillis >= 0, "maxRetryDelayMillis cannot be negative");
            this.maxRetryDelayMillis = maxRetryDelayMillis;
            return this;
        }

        @NonNull
        public Builder setMaxGroupSize(int maxGroupSize) {
            this.maxGroupSize = Preconditions.checkPositive(maxGroupSize, "maxGroupSize must be positive");
            return this;
        }

        @NonNull
        public Builder setBleScanIntervalMs(int bleScanIntervalMs) {
            this.bleScanIntervalMs = Preconditions.checkPositive(bleScanIntervalMs, "bleScanIntervalMs must be positive");
            return this;
        }

        @NonNull
        public Builder setBleAdvertiseIntervalMs(int bleAdvertiseIntervalMs) {
            this.bleAdvertiseIntervalMs = Preconditions.checkPositive(bleAdvertiseIntervalMs, "bleAdvertiseIntervalMs must be positive");
            return this;
        }

        @NonNull
        public Builder setHeartbeatIntervalMs(int heartbeatIntervalMs) {
            this.heartbeatIntervalMs = Preconditions.checkPositive(heartbeatIntervalMs, "heartbeatIntervalMs must be positive");
            return this;
        }

        @NonNull
        public Builder setMaxAttachmentSizeBytes(long maxAttachmentSizeBytes) {
            Preconditions.checkArgument(maxAttachmentSizeBytes > 0, "maxAttachmentSizeBytes must be positive");
            this.maxAttachmentSizeBytes = maxAttachmentSizeBytes;
            return this;
        }

        @NonNull
        public Builder setDeduplicationCacheSize(int deduplicationCacheSize) {
            this.deduplicationCacheSize = Preconditions.checkPositive(deduplicationCacheSize, "deduplicationCacheSize must be positive");
            return this;
        }

        @NonNull
        public Builder setSprayAndWaitCopies(int sprayAndWaitCopies) {
            this.sprayAndWaitCopies = Preconditions.checkPositive(sprayAndWaitCopies, "sprayAndWaitCopies must be positive");
            return this;
        }

        @NonNull
        public Builder setMaxStoredBundles(int maxStoredBundles) {
            this.maxStoredBundles = Preconditions.checkPositive(maxStoredBundles, "maxStoredBundles must be positive");
            return this;
        }

        @NonNull
        public Builder setStorageLimitBytes(long storageLimitBytes) {
            Preconditions.checkArgument(storageLimitBytes > 0, "storageLimitBytes must be positive");
            this.storageLimitBytes = storageLimitBytes;
            return this;
        }

        @NonNull
        public Builder setDebugMode(boolean debugMode) {
            this.debugMode = debugMode;
            return this;
        }

        @NonNull
        public MeshConfig build() {
            return new MeshConfig(this);
        }
    }
}
