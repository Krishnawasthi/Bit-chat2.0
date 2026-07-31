/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.indianmesh.database.entity.MessageEntity;

import java.util.List;

/**
 * Data Access Object for the Message entity.
 * Provides methods for performing CRUD operations on the messages table.
 */
@Dao
public interface MessageDao {

    /**
     * Inserts a new message or replaces an existing one.
     *
     * @param message The message entity to insert.
     * @return The row ID of the newly inserted message.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(MessageEntity message);

    /**
     * Inserts multiple messages.
     *
     * @param messages The list of message entities to insert.
     * @return The row IDs of the newly inserted messages.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(List<MessageEntity> messages);

    /**
     * Updates an existing message.
     *
     * @param message The message entity to update.
     * @return The number of rows updated.
     */
    @Update
    int update(MessageEntity message);

    /**
     * Deletes a message.
     *
     * @param message The message entity to delete.
     * @return The number of rows deleted.
     */
    @Delete
    int delete(MessageEntity message);

    /**
     * Retrieves a message by its ID.
     *
     * @param messageId The ID of the message.
     * @return The message entity, or null if not found.
     */
    @Query("SELECT * FROM messages WHERE message_id = :messageId LIMIT 1")
    MessageEntity getMessageById(String messageId);

    /**
     * Retrieves all messages for a specific conversation.
     *
     * @param conversationId The ID of the conversation.
     * @return A list of message entities in the conversation.
     */
    @Query("SELECT * FROM messages WHERE conversation_id = :conversationId ORDER BY timestamp ASC")
    List<MessageEntity> getMessagesForConversation(String conversationId);

    /**
     * Retrieves all messages.
     *
     * @return A list of all message entities.
     */
    @Query("SELECT * FROM messages")
    List<MessageEntity> getAllMessages();
}
