package com.example.pacomeilboudocustomersupport.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "attachments")
public class AttachmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private TicketEntity ticket;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "contents")
    private byte[] contents;

    // Constructors, Getters, and Setters

    public AttachmentEntity() {
    }

    public AttachmentEntity(TicketEntity ticket, String name, byte[] contents) {
        this.ticket = ticket;
        this.name = name;
        this.contents = contents;
    }

    public static AttachmentEntity getAttachment() {
        return null;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TicketEntity getTicket() {
        return ticket;
    }

    public void setTicket(TicketEntity ticket) {
        this.ticket = ticket;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getContents() {
        return contents;
    }

    public void setContents(byte[] contents) {
        this.contents = contents;
    }
}
