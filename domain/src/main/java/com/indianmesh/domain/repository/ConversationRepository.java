/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.repository;

import com.indianmesh.domain.model.Conversation;
import com.indianmesh.core.model.NodeId;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Repository interface for managing conversations.
 */
public interface ConversationRepository {

    /**
     * Retrieves all conversations.
     *
     * @return a CompletableFuture containing the list of all conversations
     */
    CompletableFuture<List<Conversation>> getAllConversations();

    /**
     * Retrieves a conversation by its ID.
     *
     * @param id the ID of the conversation
     * @return a CompletableFuture containing the conversation
     */
    CompletableFuture<Conversation> getConversationById(String id);

    /**
     * Retrieves an existing direct conversation or creates a new one.
     *
     * @param peerId the NodeId of the peer
     * @return a CompletableFuture containing the conversation
     */
    CompletableFuture<Conversation> getOrCreateDirectConversation(NodeId peerId);

    /**
     * Updates a conversation.
     *
     * @param conversation the conversation to update
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> updateConversation(Conversation conversation);

    /**
     * Deletes a conversation by its ID.
     *
     * @param id the ID of the conversation
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> deleteConversation(String id);

    /**
     * Pins or unpins a conversation.
     *
     * @param id the ID of the conversation
     * @param pinned true to pin, false to unpin
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> pinConversation(String id, boolean pinned);

    /**
     * Mutes or unmutes a conversation.
     *
     * @param id the ID of the conversation
     * @param muted true to mute, false to unmute
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> muteConversation(String id, boolean muted);

    /**
     * Archives or unarchives a conversation.
     *
     * @param id the ID of the conversation
     * @param archived true to archive, false to unarchive
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> archiveConversation(String id, boolean archived);

    /**
     * Saves a draft message for a conversation.
     *
     * @param conversationId the ID of the conversation
     * @param text the draft text
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> saveDraft(String conversationId, String text);
}
