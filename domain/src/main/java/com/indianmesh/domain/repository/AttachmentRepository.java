/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.repository;

import com.indianmesh.domain.model.Attachment;
import com.indianmesh.core.model.MessageId;
import com.indianmesh.domain.model.TransferStatus;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Repository interface for managing attachments.
 */
public interface AttachmentRepository {

    /**
     * Saves an attachment.
     *
     * @param attachment the attachment to save
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> saveAttachment(Attachment attachment);

    /**
     * Retrieves an attachment by its ID.
     *
     * @param attachmentId the ID of the attachment
     * @return a CompletableFuture containing the attachment
     */
    CompletableFuture<Attachment> getAttachmentById(String attachmentId);

    /**
     * Retrieves all attachments for a specific message.
     *
     * @param messageId the ID of the message
     * @return a CompletableFuture containing the list of attachments
     */
    CompletableFuture<List<Attachment>> getAttachmentsForMessage(MessageId messageId);

    /**
     * Updates the transfer status and progress of an attachment.
     *
     * @param attachmentId the ID of the attachment
     * @param status the new transfer status
     * @param progress the new transfer progress
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> updateTransferStatus(String attachmentId, TransferStatus status, float progress);

    /**
     * Deletes an attachment.
     *
     * @param attachmentId the ID of the attachment
     * @return a CompletableFuture representing the completion of the operation
     */
    CompletableFuture<Void> deleteAttachment(String attachmentId);
}
