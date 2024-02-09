package com.example.pacomeilboudocustomersupport.site;


public class Ticket {
    private String customerName;
    private String subject;
    private String body;
    private Attachment attachment;

    public Ticket(String customerName, String subject, String body) {
        this.customerName = customerName;
        this.subject = subject;
        this.body = body;
    }

    public Ticket() {
        this("", "", "");
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


    public boolean hasAttachments() {

        return attachment != null && attachment.getName().length() >0 && attachment.getContents().length >0;
    }




    @Override
    public String toString() {
        return "Ticket{" +
                "customerName='" + customerName + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", attachments=" + attachment +
                '}';
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public Attachment getAttachment() {
        return this.attachment;
    }

}