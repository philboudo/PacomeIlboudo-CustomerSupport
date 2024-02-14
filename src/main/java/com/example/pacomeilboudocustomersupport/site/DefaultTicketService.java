package com.example.pacomeilboudocustomersupport.site;

import com.example.pacomeilboudocustomersupport.entities.Attachment;
import com.example.pacomeilboudocustomersupport.entities.AttachmentEntity;
import com.example.pacomeilboudocustomersupport.entities.TicketEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultTicketService implements TicketService {

    private final TicketRepository ticketRepository;

    public DefaultTicketService(TicketRepository ticketRepository, AttachmentRepository attachmentRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    @Transactional
    @NonNull
    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        ticketRepository.getAll().forEach(ticketEntity -> tickets.add(convert(ticketEntity)));
        return tickets;
    }

    @Override
    public Ticket getTicket(long id) {
        TicketEntity ticketEntity = ticketRepository.get(id);
        return ticketEntity == null ? null : convert(ticketEntity);
    }

    private Ticket convert(TicketEntity ticketEntity) {
        Ticket ticket = new Ticket();
        ticket.setCustomerName(ticketEntity.getCustomerName());
        ticket.setSubject(ticketEntity.getSubject());
        ticket.setBody(ticketEntity.getBody());
        ticket.setAttachment(convertAttachment(AttachmentEntity.getAttachment())); // Convert attachment
        return ticket;
    }

    private Attachment convertAttachment(AttachmentEntity attachmentEntity) {
        if (attachmentEntity == null) {
            return null;
        }
        Attachment attachment = new Attachment();
        attachment.setName(attachmentEntity.getName());
        attachment.setContents(attachmentEntity.getContents());
        return attachment;
    }

    @Override
    @Transactional
    public void save(Ticket ticket) {
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setCustomerName(ticket.getCustomerName());
        ticketEntity.setSubject(ticket.getSubject());
        ticketEntity.setBody(ticket.getBody());

        Attachment attachment = ticket.getAttachment();
        if (attachment != null) {
            AttachmentEntity attachmentEntity = new AttachmentEntity();
            attachmentEntity.setName(attachment.getName());
            attachmentEntity.setContents(attachment.getContents());
            ticketEntity.setAttachment(attachmentEntity);
        }

        if (ticket.getId() < 1) { // New ticket, insert
            ticketRepository.add(ticketEntity);
        } else { // Existing ticket, update
            ticketEntity.setId(ticket.getId());
            ticketRepository.update(ticketEntity);
        }
    }

    @Override
    @Transactional
    public void deleteTicket(long id) {
        ticketRepository.deleteById(id);
    }
}
