package com.example.pacomeilboudocustomersupport;
import java.util.HashMap;
import java.util.Map;

public class Ticket {
    private String customerName;
    private String subject;
    private String body;
    private Map<Integer, Attachment> attachments;

    public Ticket() {
        this.attachments = new HashMap<>();
    }

    public Ticket (String customerName, String subject, String body) {
        this.customerName = customerName;
        this.subject = subject;
        this.body = body;
        this.attachments = new HashMap<>();
    }

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

    public Map<Integer, Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Map<Integer, Attachment> attachments) {
        this.attachments = attachments;
    }

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

}
