/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.model;

import com.indianmesh.core.model.NodeId;
import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a user in the mesh network.
 */
public final class User {
    private final NodeId nodeId;
    private final String displayName;
    private final byte[] avatar;
    private final String statusText;
    private final String about;
    private final String languages;
    private final long createdAt;
    private final long updatedAt;
    private final String identityPublicKeyHex;
    private final String signingPublicKeyHex;
    private final boolean isSelf;

    private User(Builder builder) {
        this.nodeId = builder.nodeId;
        this.displayName = builder.displayName;
        this.avatar = builder.avatar;
        this.statusText = builder.statusText;
        this.about = builder.about;
        this.languages = builder.languages;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
        this.identityPublicKeyHex = builder.identityPublicKeyHex;
        this.signingPublicKeyHex = builder.signingPublicKeyHex;
        this.isSelf = builder.isSelf;
    }

    public NodeId getNodeId() { return nodeId; }
    public String getDisplayName() { return displayName; }
    public byte[] getAvatar() { return avatar; }
    public String getStatusText() { return statusText; }
    public String getAbout() { return about; }
    public String getLanguages() { return languages; }
    public long getCreatedAt() { return createdAt; }
    public long getUpdatedAt() { return updatedAt; }
    public String getIdentityPublicKeyHex() { return identityPublicKeyHex; }
    public String getSigningPublicKeyHex() { return signingPublicKeyHex; }
    public boolean isSelf() { return isSelf; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return createdAt == user.createdAt &&
                updatedAt == user.updatedAt &&
                isSelf == user.isSelf &&
                Objects.equals(nodeId, user.nodeId) &&
                Objects.equals(displayName, user.displayName) &&
                Arrays.equals(avatar, user.avatar) &&
                Objects.equals(statusText, user.statusText) &&
                Objects.equals(about, user.about) &&
                Objects.equals(languages, user.languages) &&
                Objects.equals(identityPublicKeyHex, user.identityPublicKeyHex) &&
                Objects.equals(signingPublicKeyHex, user.signingPublicKeyHex);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(nodeId, displayName, statusText, about, languages, createdAt, updatedAt, identityPublicKeyHex, signingPublicKeyHex, isSelf);
        result = 31 * result + Arrays.hashCode(avatar);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "nodeId=" + nodeId +
                ", displayName='" + displayName + '\'' +
                ", isSelf=" + isSelf +
                '}';
    }

    public static class Builder {
        private NodeId nodeId;
        private String displayName;
        private byte[] avatar;
        private String statusText;
        private String about;
        private String languages;
        private long createdAt;
        private long updatedAt;
        private String identityPublicKeyHex;
        private String signingPublicKeyHex;
        private boolean isSelf;

        public Builder nodeId(NodeId nodeId) { this.nodeId = nodeId; return this; }
        public Builder displayName(String displayName) { this.displayName = displayName; return this; }
        public Builder avatar(byte[] avatar) { this.avatar = avatar; return this; }
        public Builder statusText(String statusText) { this.statusText = statusText; return this; }
        public Builder about(String about) { this.about = about; return this; }
        public Builder languages(String languages) { this.languages = languages; return this; }
        public Builder createdAt(long createdAt) { this.createdAt = createdAt; return this; }
        public Builder updatedAt(long updatedAt) { this.updatedAt = updatedAt; return this; }
        public Builder identityPublicKeyHex(String identityPublicKeyHex) { this.identityPublicKeyHex = identityPublicKeyHex; return this; }
        public Builder signingPublicKeyHex(String signingPublicKeyHex) { this.signingPublicKeyHex = signingPublicKeyHex; return this; }
        public Builder isSelf(boolean isSelf) { this.isSelf = isSelf; return this; }

        public User build() {
            return new User(this);
        }
    }
}
