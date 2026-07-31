/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.model;

import com.indianmesh.core.model.NodeId;
import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a user's own profile settings.
 */
public final class UserProfile {
    private final NodeId nodeId;
    private final String displayName;
    private final byte[] avatar;
    private final String statusText;
    private final String about;
    private final String languages;
    private final String theme;
    private final boolean isHidden;
    private final boolean isPrivate;

    private UserProfile(Builder builder) {
        this.nodeId = builder.nodeId;
        this.displayName = builder.displayName;
        this.avatar = builder.avatar;
        this.statusText = builder.statusText;
        this.about = builder.about;
        this.languages = builder.languages;
        this.theme = builder.theme;
        this.isHidden = builder.isHidden;
        this.isPrivate = builder.isPrivate;
    }

    public NodeId getNodeId() { return nodeId; }
    public String getDisplayName() { return displayName; }
    public byte[] getAvatar() { return avatar; }
    public String getStatusText() { return statusText; }
    public String getAbout() { return about; }
    public String getLanguages() { return languages; }
    public String getTheme() { return theme; }
    public boolean isHidden() { return isHidden; }
    public boolean isPrivate() { return isPrivate; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return isHidden == that.isHidden &&
                isPrivate == that.isPrivate &&
                Objects.equals(nodeId, that.nodeId) &&
                Objects.equals(displayName, that.displayName) &&
                Arrays.equals(avatar, that.avatar) &&
                Objects.equals(statusText, that.statusText) &&
                Objects.equals(about, that.about) &&
                Objects.equals(languages, that.languages) &&
                Objects.equals(theme, that.theme);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(nodeId, displayName, statusText, about, languages, theme, isHidden, isPrivate);
        result = 31 * result + Arrays.hashCode(avatar);
        return result;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "nodeId=" + nodeId +
                ", displayName='" + displayName + '\'' +
                ", theme='" + theme + '\'' +
                '}';
    }

    public static class Builder {
        private NodeId nodeId;
        private String displayName;
        private byte[] avatar;
        private String statusText;
        private String about;
        private String languages;
        private String theme;
        private boolean isHidden;
        private boolean isPrivate;

        public Builder nodeId(NodeId nodeId) { this.nodeId = nodeId; return this; }
        public Builder displayName(String displayName) { this.displayName = displayName; return this; }
        public Builder avatar(byte[] avatar) { this.avatar = avatar; return this; }
        public Builder statusText(String statusText) { this.statusText = statusText; return this; }
        public Builder about(String about) { this.about = about; return this; }
        public Builder languages(String languages) { this.languages = languages; return this; }
        public Builder theme(String theme) { this.theme = theme; return this; }
        public Builder isHidden(boolean isHidden) { this.isHidden = isHidden; return this; }
        public Builder isPrivate(boolean isPrivate) { this.isPrivate = isPrivate; return this; }

        public UserProfile build() {
            return new UserProfile(this);
        }
    }
}
