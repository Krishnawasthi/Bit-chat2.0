/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity representing a file attachment in a message.
 */
@Entity(tableName = "attachments")
public class AttachmentEntity {

    /** The unique attachment identifier. */
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "attachment_id")
    private final String attachmentId;

    /** The associated message identifier. */
    @NonNull
    @ColumnInfo(name = "message_id")
    private final String messageId;

    /** The local file path of the attachment, if available. */
    @Nullable
    @ColumnInfo(name = "local_path")
    private final String localPath;

    /** The remote URI of the attachment, if available. */
    @Nullable
    @ColumnInfo(name = "remote_uri")
    private final String remoteUri;

    /** The MIME type of the file. */
    @NonNull
    @ColumnInfo(name = "mime_type")
    private final String mimeType;

    /** The size of the file in bytes. */
    @ColumnInfo(name = "size_bytes")
    private final long sizeBytes;

    /**
     * Constructs a new AttachmentEntity.
     *
     * @param attachmentId The attachment ID.
     * @param messageId    The message ID.
     * @param localPath    The local file path.
     * @param remoteUri    The remote URI.
     * @param mimeType     The MIME type.
     * @param sizeBytes    The file size.
     */
    public AttachmentEntity(@NonNull String attachmentId, @NonNull String messageId,
                            @Nullable String localPath, @Nullable String remoteUri,
                            @NonNull String mimeType, long sizeBytes) {
        this.attachmentId = attachmentId;
        this.messageId = messageId;
        this.localPath = localPath;
        this.remoteUri = remoteUri;
        this.mimeType = mimeType;
        this.sizeBytes = sizeBytes;
    }

    /** @return The attachment ID. */
    @NonNull
    public String getAttachmentId() { return attachmentId; }

    /** @return The message ID. */
    @NonNull
    public String getMessageId() { return messageId; }

    /** @return The local path. */
    @Nullable
    public String getLocalPath() { return localPath; }

    /** @return The remote URI. */
    @Nullable
    public String getRemoteUri() { return remoteUri; }

    /** @return The MIME type. */
    @NonNull
    public String getMimeType() { return mimeType; }

    /** @return The size in bytes. */
    public long getSizeBytes() { return sizeBytes; }
}
