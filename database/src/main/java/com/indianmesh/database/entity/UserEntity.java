/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity representing a user in the mesh network.
 */
@Entity(tableName = "users")
public class UserEntity {

    /** The unique node identifier for this user. */
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "node_id")
    private final String nodeId;

    /** The display name of the user. */
    @Nullable
    @ColumnInfo(name = "display_name")
    private final String displayName;

    /** The avatar image data of the user. */
    @Nullable
    @ColumnInfo(name = "avatar", typeAffinity = ColumnInfo.BLOB)
    private final byte[] avatar;

    /** The status text of the user. */
    @Nullable
    @ColumnInfo(name = "status_text")
    private final String statusText;

    /** The about description of the user. */
    @Nullable
    @ColumnInfo(name = "about")
    private final String about;

    /** The languages spoken by the user. */
    @Nullable
    @ColumnInfo(name = "languages")
    private final String languages;

    /** The creation timestamp in milliseconds. */
    @ColumnInfo(name = "created_at")
    private final long createdAt;

    /** The last updated timestamp in milliseconds. */
    @ColumnInfo(name = "updated_at")
    private final long updatedAt;

    /** The identity public key in hex format. */
    @Nullable
    @ColumnInfo(name = "identity_public_key_hex")
    private final String identityPublicKeyHex;

    /** The signing public key in hex format. */
    @Nullable
    @ColumnInfo(name = "signing_public_key_hex")
    private final String signingPublicKeyHex;

    /** Flag indicating if this user is the current device user. */
    @ColumnInfo(name = "is_self")
    private final boolean isSelf;

    /**
     * Constructs a new UserEntity.
     *
     * @param nodeId               The unique node ID.
     * @param displayName          The display name.
     * @param avatar               The avatar bytes.
     * @param statusText           The status text.
     * @param about                The about description.
     * @param languages            The languages.
     * @param createdAt            The creation time.
     * @param updatedAt            The updated time.
     * @param identityPublicKeyHex The identity public key hex.
     * @param signingPublicKeyHex  The signing public key hex.
     * @param isSelf               True if this is the self user.
     */
    public UserEntity(@NonNull String nodeId, @Nullable String displayName, @Nullable byte[] avatar,
                      @Nullable String statusText, @Nullable String about, @Nullable String languages,
                      long createdAt, long updatedAt, @Nullable String identityPublicKeyHex,
                      @Nullable String signingPublicKeyHex, boolean isSelf) {
        this.nodeId = nodeId;
        this.displayName = displayName;
        this.avatar = avatar;
        this.statusText = statusText;
        this.about = about;
        this.languages = languages;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.identityPublicKeyHex = identityPublicKeyHex;
        this.signingPublicKeyHex = signingPublicKeyHex;
        this.isSelf = isSelf;
    }

    /** @return The node ID. */
    @NonNull
    public String getNodeId() { return nodeId; }

    /** @return The display name. */
    @Nullable
    public String getDisplayName() { return displayName; }

    /** @return The avatar bytes. */
    @Nullable
    public byte[] getAvatar() { return avatar; }

    /** @return The status text. */
    @Nullable
    public String getStatusText() { return statusText; }

    /** @return The about description. */
    @Nullable
    public String getAbout() { return about; }

    /** @return The languages. */
    @Nullable
    public String getLanguages() { return languages; }

    /** @return The creation time. */
    public long getCreatedAt() { return createdAt; }

    /** @return The updated time. */
    public long getUpdatedAt() { return updatedAt; }

    /** @return The identity public key hex. */
    @Nullable
    public String getIdentityPublicKeyHex() { return identityPublicKeyHex; }

    /** @return The signing public key hex. */
    @Nullable
    public String getSigningPublicKeyHex() { return signingPublicKeyHex; }

    /** @return True if self user. */
    public boolean isSelf() { return isSelf; }
}
