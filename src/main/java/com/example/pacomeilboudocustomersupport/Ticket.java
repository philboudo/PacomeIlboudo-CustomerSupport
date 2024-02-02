package com.example.pacomeilboudocustomersupport;

import java.util.HashMap;
import java.util.Map;

public class Ticket {
    private final String customerName;
    private final String subject;
    private final String body;
    private final Map<Integer, Attachment> attachments;

    public Ticket(String customerName, String subject, String body) {
        this.customerName = customerName;
        this.subject = subject;
        this.body = body;
        this.attachments = new HashMap<>();
    }

    public Ticket() {
        this("", "", "");
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public void addAttachment(Attachment attachment) {
        int id = attachments.size() + 1; // Assign a unique ID
        attachments.put(id, attachment);
    }

    public Attachment getAttachmentByName(String attachmentName) {
        for (Attachment attachment : attachments.values()) {
            if (attachment.getName().equals(attachmentName)) {
                return attachment;
            }
        }
        return null;
    }

    public boolean hasAttachments() {
        return !attachments.isEmpty();
    }

    public Attachment[] getAttachments() {
        return attachments.values().toArray(new Attachment[0]);
    }

    public int getNumberOfAttachments() {
        return attachments.size();
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "customerName='" + customerName + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", attachments=" + attachments +
                '}';
    }
}
