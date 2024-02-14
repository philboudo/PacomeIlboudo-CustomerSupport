package com.example.pacomeilboudocustomersupport.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.security.Timestamp;

@Entity
@Table(name="tickets")
public class TicketEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L; // unique id for serializable version
    private long id; // primary key
    private String subject;
    private String customerName;
    private Timestamp dateCreated;
    private String body;
    private int numberOfAttachments;

    @ManyToOne
    @Column(name = "attachment")
    private AttachmentEntity attachment;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Basic
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Basic
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Basic
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Basic
    public int getNumberOfAttachments() {
        return numberOfAttachments;
    }

    public void setNumberOfAttachments(int numberOfAttachments) {
        this.numberOfAttachments = numberOfAttachments;
    }

    public AttachmentEntity getAttachment() {
        return attachment;
    }

    public void setAttachment(AttachmentEntity attachment) {
        this.attachment = attachment;
    }
}



