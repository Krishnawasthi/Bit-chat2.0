/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.model;

import com.indianmesh.core.model.MessageId;
import java.util.Objects;

/**
 * Represents an attachment associated with a message.
 */
public final class Attachment {
    private final String attachmentId;
    private final MessageId messageId;
    private final String fileName;
    private final String mimeType;
    private final long fileSize;
    private final String localPath;
    private final String checksum;
    private final String thumbnailPath;
    private final int width;
    private final int height;
    private final long durationMs;
    private final TransferStatus transferStatus;
    private final float transferProgress;

    private Attachment(Builder builder) {
        this.attachmentId = builder.attachmentId;
        this.messageId = builder.messageId;
        this.fileName = builder.fileName;
        this.mimeType = builder.mimeType;
        this.fileSize = builder.fileSize;
        this.localPath = builder.localPath;
        this.checksum = builder.checksum;
        this.thumbnailPath = builder.thumbnailPath;
        this.width = builder.width;
        this.height = builder.height;
        this.durationMs = builder.durationMs;
        this.transferStatus = builder.transferStatus;
        this.transferProgress = builder.transferProgress;
    }

    public String getAttachmentId() { return attachmentId; }
    public MessageId getMessageId() { return messageId; }
    public String getFileName() { return fileName; }
    public String getMimeType() { return mimeType; }
    public long getFileSize() { return fileSize; }
    public String getLocalPath() { return localPath; }
    public String getChecksum() { return checksum; }
    public String getThumbnailPath() { return thumbnailPath; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public long getDurationMs() { return durationMs; }
    public TransferStatus getTransferStatus() { return transferStatus; }
    public float getTransferProgress() { return transferProgress; }

    /**
     * Checks if the attachment is an image.
     *
     * @return True if image.
     */
    public boolean isImage() {
        return mimeType != null && mimeType.startsWith("image/");
    }

    /**
     * Checks if the attachment is a video.
     *
     * @return True if video.
     */
    public boolean isVideo() {
        return mimeType != null && mimeType.startsWith("video/");
    }

    /**
     * Checks if the attachment is an audio file.
     *
     * @return True if audio.
     */
    public boolean isAudio() {
        return mimeType != null && mimeType.startsWith("audio/");
    }

    /**
     * Checks if the attachment is a document.
     *
     * @return True if document.
     */
    public boolean isDocument() {
        return mimeType != null && (mimeType.startsWith("application/") || mimeType.startsWith("text/"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attachment that = (Attachment) o;
        return Objects.equals(attachmentId, that.attachmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attachmentId);
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "attachmentId='" + attachmentId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", mimeType='" + mimeType + '\'' +
                '}';
    }

    public static class Builder {
        private String attachmentId;
        private MessageId messageId;
        private String fileName;
        private String mimeType;
        private long fileSize;
        private String localPath;
        private String checksum;
        private String thumbnailPath;
        private int width;
        private int height;
        private long durationMs;
        private TransferStatus transferStatus;
        private float transferProgress;

        public Builder attachmentId(String attachmentId) { this.attachmentId = attachmentId; return this; }
        public Builder messageId(MessageId messageId) { this.messageId = messageId; return this; }
        public Builder fileName(String fileName) { this.fileName = fileName; return this; }
        public Builder mimeType(String mimeType) { this.mimeType = mimeType; return this; }
        public Builder fileSize(long fileSize) { this.fileSize = fileSize; return this; }
        public Builder localPath(String localPath) { this.localPath = localPath; return this; }
        public Builder checksum(String checksum) { this.checksum = checksum; return this; }
        public Builder thumbnailPath(String thumbnailPath) { this.thumbnailPath = thumbnailPath; return this; }
        public Builder width(int width) { this.width = width; return this; }
        public Builder height(int height) { this.height = height; return this; }
        public Builder durationMs(long durationMs) { this.durationMs = durationMs; return this; }
        public Builder transferStatus(TransferStatus transferStatus) { this.transferStatus = transferStatus; return this; }
        public Builder transferProgress(float transferProgress) { this.transferProgress = transferProgress; return this; }

        public Attachment build() {
            return new Attachment(this);
        }
    }
}
