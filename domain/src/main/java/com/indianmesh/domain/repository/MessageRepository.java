/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.repository;

import com.indianmesh.domain.model.DeliveryStatus;
import com.indianmesh.domain.model.Message;
import com.indianmesh.core.model.MessageId;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Repository interface for managing messages.
 */
public interface MessageRepository {

    /**
     * Saves a single message.
     *
     * @param message the message to save
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> saveMessage(Message message);

    /**
     * Saves multiple messages.
     *
     * @param messages the list of messages to save
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> saveMessages(List<Message> messages);

    /**
     * Retrieves a message by its ID.
     *
     * @param id the ID of the message
     * @return a CompletableFuture containing the message
     */
    CompletableFuture<Message> getMessageById(MessageId id);

    /**
     * Retrieves messages for a specific conversation with pagination.
     *
     * @param conversationId the ID of the conversation
     * @param limit the maximum number of messages to retrieve
     * @param offset the offset from which to start retrieving
     * @return a CompletableFuture containing the list of messages
     */
    CompletableFuture<List<Message>> getMessagesForConversation(String conversationId, int limit, int offset);

    /**
     * Searches for messages matching a query string.
     *
     * @param query the search query
     * @return a CompletableFuture containing the matching messages
     */
    CompletableFuture<List<Message>> searchMessages(String query);

    /**
     * Updates the delivery status of a message.
     *
     * @param id the ID of the message
     * @param status the new delivery status
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> updateDeliveryStatus(MessageId id, DeliveryStatus status);

    /**
     * Deletes a message.
     *
     * @param id the ID of the message
     * @param forEveryone true if the message should be deleted for everyone, false otherwise
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> deleteMessage(MessageId id, boolean forEveryone);

    /**
     * Stars or unstars a message.
     *
     * @param id the ID of the message
     * @param starred true to star the message, false to unstar
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> starMessage(MessageId id, boolean starred);

    /**
     * Retrieves all starred messages.
     *
     * @return a CompletableFuture containing the list of starred messages
     */
    CompletableFuture<List<Message>> getStarredMessages();

    /**
     * Retrieves all pending messages.
     *
     * @return a CompletableFuture containing the list of pending messages
     */
    CompletableFuture<List<Message>> getPendingMessages();

    /**
     * Retrieves all failed messages.
     *
     * @return a CompletableFuture containing the list of failed messages
     */
    CompletableFuture<List<Message>> getFailedMessages();

    /**
     * Gets the unread message count for a conversation.
     *
     * @param conversationId the ID of the conversation
     * @return a CompletableFuture containing the unread count
     */
    CompletableFuture<Integer> getUnreadCount(String conversationId);

    /**
     * Marks all messages in a conversation as read.
     *
     * @param conversationId the ID of the conversation
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> markAsRead(String conversationId);
}
