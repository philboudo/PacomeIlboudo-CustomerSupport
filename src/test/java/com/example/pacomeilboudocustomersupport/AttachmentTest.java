package com.example.pacomeilboudocustomersupport;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AttachmentTest {

    @Test
    void testAttachmentCreation() {
        // Attachment object
        Attachment attachment = new Attachment();

        assertNotNull(attachment);
    }

    @Test
    void testAttachmentName() {
        // Attachment object
        Attachment attachment = new Attachment();

        // Set the name
        attachment.setName("Attachment 1");

        // Verify the name
        assertEquals("Attachment 1", attachment.getName());
    }

    @Test
    void testAttachmentContents() {
        // Create an Attachment object
        Attachment attachment = new Attachment();

        // Set the contents
        byte[] contents = { 1, 2, 3 };
        attachment.setContents(contents);

        // Verify the contents
        assertArrayEquals(contents, attachment.getContents());
    }
}
