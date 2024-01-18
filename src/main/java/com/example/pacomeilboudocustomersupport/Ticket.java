package com.example.pacomeilboudocustomersupport;

import java.util.HashMap;
import java.util.Map;

public class Ticket {
    private String customerName;
    private String subject;
    private String body;
    private Map<Integer, Attachment> attachments;

    // Parameterized constructor
    public Ticket(String customerName, String subject, String body) {
        this.customerName = customerName;
        this.subject = subject;
        this.body = body;
        this.attachments = new HashMap<>();
    }

    // Default constructor
    public Ticket() {
        this("", "", ""); // Call the parameterized constructor with default values
    }

    // Getter and Setter methods for customerName, subject, and body
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    // Helper methods
    public void addAttachment(int id, Attachment attachment) {
        attachments.put(id, attachment);
    }

    public int getNumberOfAttachments() {
        return attachments.size();
    }

    public Attachment getAttachmentById(int id) {
        return attachments.get(id);
    }

    public Map<Integer, Attachment> getAllAttachments() {
        return attachments;
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
