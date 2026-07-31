/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Entity representing an option in a poll.
 */
@Entity(tableName = "poll_options",
        indices = {@Index("poll_id")})
public class PollOptionEntity {

    /** The unique option identifier. */
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "option_id")
    private final String optionId;

    /** The associated poll identifier. */
    @NonNull
    @ColumnInfo(name = "poll_id")
    private final String pollId;

    /** The textual option content. */
    @NonNull
    @ColumnInfo(name = "text")
    private final String text;

    /** The display order of the option. */
    @ColumnInfo(name = "order_index")
    private final int orderIndex;

    /**
     * Constructs a new PollOptionEntity.
     *
     * @param optionId   The option ID.
     * @param pollId     The associated poll ID.
     * @param text       The option text.
     * @param orderIndex The display order index.
     */
    public PollOptionEntity(@NonNull String optionId, @NonNull String pollId,
                            @NonNull String text, int orderIndex) {
        this.optionId = optionId;
        this.pollId = pollId;
        this.text = text;
        this.orderIndex = orderIndex;
    }

    /** @return The option ID. */
    @NonNull
    public String getOptionId() { return optionId; }

    /** @return The associated poll ID. */
    @NonNull
    public String getPollId() { return pollId; }

    /** @return The option text. */
    @NonNull
    public String getText() { return text; }

    /** @return The display order index. */
    public int getOrderIndex() { return orderIndex; }
}
