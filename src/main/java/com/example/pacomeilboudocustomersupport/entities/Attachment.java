package com.example.pacomeilboudocustomersupport.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;

@Entity
@Table(name="attachment")
public class Attachment implements Serializable
{

    @Serial
    private static  final long serialVersionUID = 1L;

    private long id;

    public void setTicket_id(long ticket_id) {
        this.ticket_id = ticket_id;
    }

    private long ticket_id;
    private String name;

    private byte[] contents;

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
    public long getTicket_id() {
        return ticket_id;
    }
    @Basic
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    @Lob
    public byte[] getContents()
    {
        return contents;
    }

    public void setContents(byte[] contents)
    {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "name='" + name + '\'' +
                ", contents=" + Arrays.toString(contents) +
                '}';
    }
}