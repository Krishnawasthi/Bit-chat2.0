/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.model;

import com.indianmesh.core.model.NodeId;
import java.util.Objects;

/**
 * Represents a peer in the mesh network.
 */
public final class Peer {
    private final NodeId nodeId;
    private final String displayName;
    private final String publicKeyHex;
    private final TransportType lastTransport;
    private final String bleAddress;
    private final String wifiAddress;
    private final int signalStrength;
    private final long lastSeenAt;
    private final long firstSeenAt;
    private final boolean isConnected;
    private final boolean isTrusted;
    private final boolean isBlocked;
    private final int hopsAway;
    private final String fingerprint;

    private Peer(Builder builder) {
        this.nodeId = builder.nodeId;
        this.displayName = builder.displayName;
        this.publicKeyHex = builder.publicKeyHex;
        this.lastTransport = builder.lastTransport;
        this.bleAddress = builder.bleAddress;
        this.wifiAddress = builder.wifiAddress;
        this.signalStrength = builder.signalStrength;
        this.lastSeenAt = builder.lastSeenAt;
        this.firstSeenAt = builder.firstSeenAt;
        this.isConnected = builder.isConnected;
        this.isTrusted = builder.isTrusted;
        this.isBlocked = builder.isBlocked;
        this.hopsAway = builder.hopsAway;
        this.fingerprint = builder.fingerprint;
    }

    public NodeId getNodeId() { return nodeId; }
    public String getDisplayName() { return displayName; }
    public String getPublicKeyHex() { return publicKeyHex; }
    public TransportType getLastTransport() { return lastTransport; }
    public String getBleAddress() { return bleAddress; }
    public String getWifiAddress() { return wifiAddress; }
    public int getSignalStrength() { return signalStrength; }
    public long getLastSeenAt() { return lastSeenAt; }
    public long getFirstSeenAt() { return firstSeenAt; }
    public boolean isConnected() { return isConnected; }
    public boolean isTrusted() { return isTrusted; }
    public boolean isBlocked() { return isBlocked; }
    public int getHopsAway() { return hopsAway; }
    public String getFingerprint() { return fingerprint; }

    /**
     * Checks if the peer is nearby (last seen within 30 seconds).
     *
     * @return True if nearby.
     */
    public boolean isNearby() {
        return (System.currentTimeMillis() - lastSeenAt) <= 30000;
    }

    /**
     * Checks if the peer is directly reachable.
     *
     * @return True if directly reachable (hops == 1).
     */
    public boolean isDirectlyReachable() {
        return hopsAway == 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Peer peer = (Peer) o;
        return Objects.equals(nodeId, peer.nodeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeId);
    }

    @Override
    public String toString() {
        return "Peer{" +
                "nodeId=" + nodeId +
                ", displayName='" + displayName + '\'' +
                ", hopsAway=" + hopsAway +
                '}';
    }

    public static class Builder {
        private NodeId nodeId;
        private String displayName;
        private String publicKeyHex;
        private TransportType lastTransport;
        private String bleAddress;
        private String wifiAddress;
        private int signalStrength;
        private long lastSeenAt;
        private long firstSeenAt;
        private boolean isConnected;
        private boolean isTrusted;
        private boolean isBlocked;
        private int hopsAway;
        private String fingerprint;

        public Builder nodeId(NodeId nodeId) { this.nodeId = nodeId; return this; }
        public Builder displayName(String displayName) { this.displayName = displayName; return this; }
        public Builder publicKeyHex(String publicKeyHex) { this.publicKeyHex = publicKeyHex; return this; }
        public Builder lastTransport(TransportType lastTransport) { this.lastTransport = lastTransport; return this; }
        public Builder bleAddress(String bleAddress) { this.bleAddress = bleAddress; return this; }
        public Builder wifiAddress(String wifiAddress) { this.wifiAddress = wifiAddress; return this; }
        public Builder signalStrength(int signalStrength) { this.signalStrength = signalStrength; return this; }
        public Builder lastSeenAt(long lastSeenAt) { this.lastSeenAt = lastSeenAt; return this; }
        public Builder firstSeenAt(long firstSeenAt) { this.firstSeenAt = firstSeenAt; return this; }
        public Builder isConnected(boolean isConnected) { this.isConnected = isConnected; return this; }
        public Builder isTrusted(boolean isTrusted) { this.isTrusted = isTrusted; return this; }
        public Builder isBlocked(boolean isBlocked) { this.isBlocked = isBlocked; return this; }
        public Builder hopsAway(int hopsAway) { this.hopsAway = hopsAway; return this; }
        public Builder fingerprint(String fingerprint) { this.fingerprint = fingerprint; return this; }

        public Peer build() {
            return new Peer(this);
        }
    }
}
