/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Entity representing a vote on a poll option.
 */
@Entity(tableName = "poll_votes",
        indices = {@Index(value = {"poll_id", "voter_id", "option_id"}, unique = true)})
public class PollVoteEntity {

    /** The unique vote identifier. */
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "vote_id")
    private final String voteId;

    /** The associated poll identifier. */
    @NonNull
    @ColumnInfo(name = "poll_id")
    private final String pollId;

    /** The chosen option identifier. */
    @NonNull
    @ColumnInfo(name = "option_id")
    private final String optionId;

    /** The node identifier of the voter. */
    @NonNull
    @ColumnInfo(name = "voter_id")
    private final String voterId;

    /**
     * Constructs a new PollVoteEntity.
     *
     * @param voteId   The vote ID.
     * @param pollId   The associated poll ID.
     * @param optionId The chosen option ID.
     * @param voterId  The voter's node ID.
     */
    public PollVoteEntity(@NonNull String voteId, @NonNull String pollId,
                          @NonNull String optionId, @NonNull String voterId) {
        this.voteId = voteId;
        this.pollId = pollId;
        this.optionId = optionId;
        this.voterId = voterId;
    }

    /** @return The vote ID. */
    @NonNull
    public String getVoteId() { return voteId; }

    /** @return The associated poll ID. */
    @NonNull
    public String getPollId() { return pollId; }

    /** @return The chosen option ID. */
    @NonNull
    public String getOptionId() { return optionId; }

    /** @return The voter's node ID. */
    @NonNull
    public String getVoterId() { return voterId; }
}
