/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.model;

/**
 * Represents the type of a message.
 */
public enum MessageType {
    /** Text message. */
    TEXT("text/plain", false, false),
    /** Image message. */
    IMAGE("image/", true, true),
    /** Video message. */
    VIDEO("video/", true, true),
    /** Voice message. */
    VOICE("audio/ogg", true, true),
    /** PDF document message. */
    PDF("application/pdf", true, true),
    /** Location sharing message. */
    LOCATION("geo:", false, false),
    /** Poll message. */
    POLL("application/x-poll", false, false),
    /** System message. */
    SYSTEM("text/plain", false, false),
    /** Contact sharing message. */
    CONTACT("text/x-vcard", false, true),
    /** GIF message. */
    GIF("image/gif", true, true),
    /** Sticker message. */
    STICKER("image/webp", true, true),
    /** Announcement message. */
    ANNOUNCEMENT("text/plain", false, false),
    /** Emergency SOS message. */
    SOS("text/plain", false, false);

    private final String mimePrefix;
    private final boolean isMedia;
    private final boolean requiresAttachment;

    /**
     * Constructs a MessageType.
     *
     * @param mimePrefix         The MIME type prefix.
     * @param isMedia            Whether this type is considered media.
     * @param requiresAttachment Whether this type requires an attachment.
     */
    MessageType(String mimePrefix, boolean isMedia, boolean requiresAttachment) {
        this.mimePrefix = mimePrefix;
        this.isMedia = isMedia;
        this.requiresAttachment = requiresAttachment;
    }

    /**
     * Gets the MIME prefix for the type.
     *
     * @return The MIME prefix.
     */
    public String getMimePrefix() {
        return mimePrefix;
    }

    /**
     * Checks if this is a media message.
     *
     * @return True if media, false otherwise.
     */
    public boolean isMedia() {
        return isMedia;
    }

    /**
     * Checks if this message type requires an attachment.
     *
     * @return True if attachment is required, false otherwise.
     */
    public boolean requiresAttachment() {
        return requiresAttachment;
    }
}
