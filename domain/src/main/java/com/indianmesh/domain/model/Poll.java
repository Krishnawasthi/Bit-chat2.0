/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.model;

import com.indianmesh.core.model.MessageId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents a poll attached to a message.
 */
public final class Poll {
    private final String pollId;
    private final MessageId messageId;
    private final String question;
    private final List<PollOption> options;
    private final boolean isMultipleChoice;
    private final boolean isAnonymous;
    private final long closesAt;

    private Poll(Builder builder) {
        this.pollId = builder.pollId;
        this.messageId = builder.messageId;
        this.question = builder.question;
        this.options = builder.options == null ? Collections.emptyList() : Collections.unmodifiableList(new ArrayList<>(builder.options));
        this.isMultipleChoice = builder.isMultipleChoice;
        this.isAnonymous = builder.isAnonymous;
        this.closesAt = builder.closesAt;
    }

    public String getPollId() { return pollId; }
    public MessageId getMessageId() { return messageId; }
    public String getQuestion() { return question; }
    public List<PollOption> getOptions() { return options; }
    public boolean isMultipleChoice() { return isMultipleChoice; }
    public boolean isAnonymous() { return isAnonymous; }
    public long getClosesAt() { return closesAt; }

    /**
     * Checks if the poll is closed based on the current time.
     *
     * @return True if closed.
     */
    public boolean isClosed() {
        return closesAt > 0 && System.currentTimeMillis() >= closesAt;
    }

    /**
     * Calculates the total number of votes across all options.
     *
     * @return The total vote count.
     */
    public int totalVotes() {
        int total = 0;
        for (PollOption option : options) {
            total += option.getVoteCount();
        }
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Poll poll = (Poll) o;
        return Objects.equals(pollId, poll.pollId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pollId);
    }

    @Override
    public String toString() {
        return "Poll{" +
                "pollId='" + pollId + '\'' +
                ", question='" + question + '\'' +
                ", totalVotes=" + totalVotes() +
                '}';
    }

    public static class Builder {
        private String pollId;
        private MessageId messageId;
        private String question;
        private List<PollOption> options;
        private boolean isMultipleChoice;
        private boolean isAnonymous;
        private long closesAt;

        public Builder pollId(String pollId) { this.pollId = pollId; return this; }
        public Builder messageId(MessageId messageId) { this.messageId = messageId; return this; }
        public Builder question(String question) { this.question = question; return this; }
        public Builder options(List<PollOption> options) { this.options = options; return this; }
        public Builder isMultipleChoice(boolean isMultipleChoice) { this.isMultipleChoice = isMultipleChoice; return this; }
        public Builder isAnonymous(boolean isAnonymous) { this.isAnonymous = isAnonymous; return this; }
        public Builder closesAt(long closesAt) { this.closesAt = closesAt; return this; }

        public Poll build() {
            return new Poll(this);
        }
    }
}
