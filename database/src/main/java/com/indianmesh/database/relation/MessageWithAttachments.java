/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.relation;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.indianmesh.database.entity.AttachmentEntity;
import com.indianmesh.database.entity.MessageEntity;

import java.util.List;

/**
 * Relation class representing a Message with all its associated Attachments.
 */
public class MessageWithAttachments {

    /**
     * The message entity.
     */
    @Embedded
    public MessageEntity message;

    /**
     * The list of attachments associated with this message.
     * The relationship is based on the message_id column in both tables.
     */
    @Relation(
            parentColumn = "message_id",
            entityColumn = "message_id"
    )
    public List<AttachmentEntity> attachments;
}
