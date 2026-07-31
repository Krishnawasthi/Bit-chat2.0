/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.indianmesh.database.entity.MessageEntity;
import com.indianmesh.database.entity.ReactionEntity;

import java.util.List;

/**
 * Relation class representing a Message with all its associated Reactions.
 */
public class MessageWithReactions {

    /**
     * The message entity.
     */
    @Embedded
    public MessageEntity message;

    /**
     * The list of reactions associated with this message.
     * The relationship is based on the message_id column in both tables.
     */
    @Relation(
            parentColumn = "message_id",
            entityColumn = "message_id"
    )
    public List<ReactionEntity> reactions;
}
