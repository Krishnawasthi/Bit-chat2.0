/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity representing a poll.
 */
@Entity(tableName = "polls")
public class PollEntity {

    /** The unique poll identifier. */
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "poll_id")
    private final String pollId;

    /** The associated message identifier. */
    @NonNull
    @ColumnInfo(name = "message_id")
    private final String messageId;

    /** The question of the poll. */
    @NonNull
    @ColumnInfo(name = "question")
    private final String question;

    /** Whether the poll allows multiple choices. */
    @ColumnInfo(name = "allow_multiple")
    private final boolean allowMultiple;

    /** Whether the poll is closed. */
    @ColumnInfo(name = "is_closed")
    private final boolean isClosed;

    /**
     * Constructs a new PollEntity.
     *
     * @param pollId        The poll ID.
     * @param messageId     The associated message ID.
     * @param question      The poll question.
     * @param allowMultiple Whether multiple choices are allowed.
     * @param isClosed      Whether the poll is closed.
     */
    public PollEntity(@NonNull String pollId, @NonNull String messageId, @NonNull String question,
                      boolean allowMultiple, boolean isClosed) {
        this.pollId = pollId;
        this.messageId = messageId;
        this.question = question;
        this.allowMultiple = allowMultiple;
        this.isClosed = isClosed;
    }

    /** @return The poll ID. */
    @NonNull
    public String getPollId() { return pollId; }

    /** @return The associated message ID. */
    @NonNull
    public String getMessageId() { return messageId; }

    /** @return The poll question. */
    @NonNull
    public String getQuestion() { return question; }

    /** @return True if multiple choices are allowed. */
    public boolean isAllowMultiple() { return allowMultiple; }

    /** @return True if the poll is closed. */
    public boolean isClosed() { return isClosed; }
}
