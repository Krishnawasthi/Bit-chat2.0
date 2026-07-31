/* Copyright 2024 Indian Mesh. All rights reserved. */
package com.indianmesh.domain.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class AttachmentTest {

    @Test
    public void testIsImage() {
        Attachment img = new Attachment.Builder().mimeType("image/jpeg").build();
        assertTrue(img.isImage());
        assertFalse(img.isVideo());
    }

    @Test
    public void testIsVideo() {
        Attachment video = new Attachment.Builder().mimeType("video/mp4").build();
        assertTrue(video.isVideo());
        assertFalse(video.isImage());
    }

    @Test
    public void testIsAudio() {
        Attachment audio = new Attachment.Builder().mimeType("audio/ogg").build();
        assertTrue(audio.isAudio());
    }

    @Test
    public void testIsDocument() {
        Attachment doc = new Attachment.Builder().mimeType("application/pdf").build();
        assertTrue(doc.isDocument());
        
        Attachment textDoc = new Attachment.Builder().mimeType("text/plain").build();
        assertTrue(textDoc.isDocument());
    }

    @Test
    public void testBuilder() {
        Attachment attachment = new Attachment.Builder()
                .fileName("test.jpg")
                .fileSize(1024)
                .build();
                
        assertEquals("test.jpg", attachment.getFileName());
        assertEquals(1024, attachment.getFileSize());
    }
}
