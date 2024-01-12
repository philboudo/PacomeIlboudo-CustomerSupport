package com.example.pacomeilboudocustomersupport;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {

    @Test
    void testTicketCreation() {
        // Create a Ticket object
        Ticket ticket = new Ticket();

        // Verify that the ticket is not null
        assertNotNull(ticket);
    }

    @Test
    void testTicketAttachmentsSize() {
        // Create a Ticket object
        Ticket ticket = new Ticket();

        // Add an attachment
        Attachment attachment = new Attachment();
        ticket.addAttachment(attachment);

        // Verify the number of attachments
        assertEquals(1, ticket.getNumberOfAttachments());
    }

    @Test
    void testTicketAttachmentsNotNull() {
        // Create a Ticket object
        Ticket ticket = new Ticket();

        // Add an attachment
        Attachment attachment = new Attachment();
        ticket.addAttachment(attachment);

        // Verify that the attachment is not null
        assertNotNull(ticket.getAllAttachments().values().iterator().next());
    }
}