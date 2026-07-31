/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.model;

import java.util.Objects;

/**
 * Represents an option within a poll.
 */
public final class PollOption {
    private final String optionId;
    private final String pollId;
    private final String text;
    private final int voteCount;

    /**
     * Constructs a PollOption.
     *
     * @param optionId  The unique identifier for the option.
     * @param pollId    The ID of the poll this option belongs to.
     * @param text      The text of the option.
     * @param voteCount The number of votes this option has received.
     */
    public PollOption(String optionId, String pollId, String text, int voteCount) {
        this.optionId = optionId;
        this.pollId = pollId;
        this.text = text;
        this.voteCount = voteCount;
    }

    /**
     * Gets the option ID.
     *
     * @return The option ID.
     */
    public String getOptionId() {
        return optionId;
    }

    /**
     * Gets the poll ID.
     *
     * @return The poll ID.
     */
    public String getPollId() {
        return pollId;
    }

    /**
     * Gets the option text.
     *
     * @return The option text.
     */
    public String getText() {
        return text;
    }

    /**
     * Gets the vote count.
     *
     * @return The vote count.
     */
    public int getVoteCount() {
        return voteCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PollOption that = (PollOption) o;
        return voteCount == that.voteCount &&
                Objects.equals(optionId, that.optionId) &&
                Objects.equals(pollId, that.pollId) &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(optionId, pollId, text, voteCount);
    }

    @Override
    public String toString() {
        return "PollOption{" +
                "optionId='" + optionId + '\'' +
                ", pollId='" + pollId + '\'' +
                ", text='" + text + '\'' +
                ", voteCount=" + voteCount +
                '}';
    }
}
