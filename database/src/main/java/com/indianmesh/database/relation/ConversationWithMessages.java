/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.indianmesh.database.entity.ConversationEntity;
import com.indianmesh.database.entity.MessageEntity;

import java.util.List;

/**
 * Relation class representing a Conversation with all its associated Messages.
 */
public class ConversationWithMessages {

    /**
     * The conversation entity.
     */
    @Embedded
    public ConversationEntity conversation;

    /**
     * The list of messages associated with this conversation.
     * The relationship is based on the conversation_id column in both tables.
     */
    @Relation(
            parentColumn = "conversation_id",
            entityColumn = "conversation_id"
    )
    public List<MessageEntity> messages;
}
