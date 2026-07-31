/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity representing a cryptographic key pair for a user or session.
 */
@Entity(tableName = "key_pairs")
public class KeyPairEntity {

    /** The unique key identifier. */
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "key_id")
    private final String keyId;

    /** The node identifier this key belongs to. */
    @NonNull
    @ColumnInfo(name = "node_id")
    private final String nodeId;

    /** The type of the key (e.g., IDENTITY, PREKEY). */
    @NonNull
    @ColumnInfo(name = "key_type")
    private final String keyType;

    /** The public key bytes. */
    @Nullable
    @ColumnInfo(name = "public_key", typeAffinity = ColumnInfo.BLOB)
    private final byte[] publicKey;

    /** The private key bytes, usually encrypted. */
    @Nullable
    @ColumnInfo(name = "private_key", typeAffinity = ColumnInfo.BLOB)
    private final byte[] privateKey;

    /**
     * Constructs a new KeyPairEntity.
     *
     * @param keyId      The unique key ID.
     * @param nodeId     The associated node ID.
     * @param keyType    The key type.
     * @param publicKey  The public key bytes.
     * @param privateKey The private key bytes.
     */
    public KeyPairEntity(@NonNull String keyId, @NonNull String nodeId, @NonNull String keyType,
                         @Nullable byte[] publicKey, @Nullable byte[] privateKey) {
        this.keyId = keyId;
        this.nodeId = nodeId;
        this.keyType = keyType;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    /** @return The key ID. */
    @NonNull
    public String getKeyId() { return keyId; }

    /** @return The associated node ID. */
    @NonNull
    public String getNodeId() { return nodeId; }

    /** @return The key type. */
    @NonNull
    public String getKeyType() { return keyType; }

    /** @return The public key bytes. */
    @Nullable
    public byte[] getPublicKey() { return publicKey; }

    /** @return The private key bytes. */
    @Nullable
    public byte[] getPrivateKey() { return privateKey; }
}
