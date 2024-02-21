package com.example.pacomeilboudocustomersupport.site;

import com.example.pacomeilboudocustomersupport.entities.TicketEntity;
import jakarta.inject.Inject;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultTicketService implements TicketService {

    @Inject TicketRepository ticketRepository;
    @Inject AttachmentRepository attachmentRepository;

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

    private Ticket convert(TicketEntity entity) {
        Ticket ticket = new Ticket();
        ticket.setId(entity.getId());
        ticket.setSubject(entity.getSubject());
        ticket.setCustomerName(entity.getCustomerName());
        ticket.setBody(entity.getBody());
        ticket.setAttachment(attachmentRepository.getByTicket_id(entity.getId()));

        //ticket.setNumberOfAttachments(entity.getNumberOfAttachments());

        return ticket;
    }

    @Override
    @Transactional
    public void save(Ticket ticket) {
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.setId(ticket.getId());
        ticketEntity.setSubject(ticket.getSubject());
        ticketEntity.setCustomerName(ticket.getCustomerName());
        ticketEntity.setBody(ticket.getBody());
        ticketEntity.setNumberOfAttachments(ticket.getNumberOfAttachments());
        if (ticket.getId() < 1) {
            ticketRepository.add(ticketEntity);
            ticket.setId(ticketEntity.getId());

            if (ticket.hasAttachments()) {
                ticket.getAttachment().setTicket_id(ticketEntity.getId());
                attachmentRepository.add(ticket.getAttachment());
            }
        } else {
            ticketRepository.update(ticketEntity);
        }
    }

    @Override
    @Transactional
    public void deleteTicket(long id) {
        ticketRepository.deleteById(id);
    }
}