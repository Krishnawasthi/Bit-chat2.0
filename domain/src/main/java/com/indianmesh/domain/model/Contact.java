/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.model;

import com.indianmesh.core.model.NodeId;
import java.util.Objects;

/**
 * Represents a contact in the user's address book.
 */
public final class Contact {
    private final String contactId;
    private final NodeId userId;
    private final String nickname;
    private final boolean isFavorite;
    private final long addedAt;

    /**
     * Constructs a Contact.
     *
     * @param contactId  The unique identifier for the contact.
     * @param userId     The user ID of the contact.
     * @param nickname   The nickname assigned to the contact.
     * @param isFavorite True if the contact is marked as a favorite.
     * @param addedAt    The timestamp when the contact was added.
     */
    public Contact(String contactId, NodeId userId, String nickname, boolean isFavorite, long addedAt) {
        this.contactId = contactId;
        this.userId = userId;
        this.nickname = nickname;
        this.isFavorite = isFavorite;
        this.addedAt = addedAt;
    }

    /**
     * Gets the contact ID.
     *
     * @return The contact ID.
     */
    public String getContactId() {
        return contactId;
    }

    /**
     * Gets the user ID.
     *
     * @return The user ID.
     */
    public NodeId getUserId() {
        return userId;
    }

    /**
     * Gets the nickname.
     *
     * @return The nickname.
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Checks if the contact is a favorite.
     *
     * @return True if a favorite, false otherwise.
     */
    public boolean isFavorite() {
        return isFavorite;
    }

    /**
     * Gets the timestamp when the contact was added.
     *
     * @return The added timestamp.
     */
    public long getAddedAt() {
        return addedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return isFavorite == contact.isFavorite &&
                addedAt == contact.addedAt &&
                Objects.equals(contactId, contact.contactId) &&
                Objects.equals(userId, contact.userId) &&
                Objects.equals(nickname, contact.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId, userId, nickname, isFavorite, addedAt);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactId='" + contactId + '\'' +
                ", userId=" + userId +
                ", nickname='" + nickname + '\'' +
                ", isFavorite=" + isFavorite +
                ", addedAt=" + addedAt +
                '}';
    }
}
