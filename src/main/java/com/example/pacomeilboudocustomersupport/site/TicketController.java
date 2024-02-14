package com.example.pacomeilboudocustomersupport.site;

import com.example.pacomeilboudocustomersupport.entities.Attachment;
import jakarta.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Controller
@RequestMapping("ticket")
public class TicketController {
    //private volatile int TICKET_ID = 1;
    //private Map<Integer, Ticket> ticketDB = new LinkedHashMap<>();

    @Inject TicketService ticketService;

    @GetMapping("/form")
    public String showTicketForm(Model model) {
        model.addAttribute("ticketForm", new TicketForm());
        return "ticketForm";
    }

    @RequestMapping(value = {"list", ""})
    public String listTickets(Model model) {
        model.addAttribute("ticketDatabase", ticketService.getAllTickets());
        return "listTickets";
    }

    @GetMapping("create")
    public ModelAndView createTicket() {
        return new ModelAndView("ticketForm", "ticket", new TicketForm());
    }

    @PostMapping("create")
    public View createPost(@ModelAttribute("ticket") TicketForm form) throws IOException {
        Ticket ticket = new Ticket();
        ticket.setSubject(form.getSubject());
        ticket.setCustomerName(form.getCustomerName());
        ticket.setBody(form.getBody());

        MultipartFile file = form.getAttachment();
        if (file != null && !file.isEmpty()) {
            Attachment attachment = new Attachment();
            attachment.setName(file.getOriginalFilename());
            attachment.setContents(file.getBytes());
            //ticket.addAttachment(attachment);
            ticket.setAttachment(attachment);
        }

        // add and synchronize
//        int id;
//        synchronized (this) {
//            id = this.TICKET_ID++;
//            ticketDB.put(id, ticket);
//        }

        ticketService.save(ticket);

        // show the view with the ticket id
        return new RedirectView("view/" + ticket.getId(), true, false);
    }

    @GetMapping("view/{ticketId}")
    public ModelAndView viewPost(Model model, @PathVariable("ticketId") int ticketId) {
        Ticket ticket = ticketService.getTicket(ticketId); // get the ticket
        // if ticket doesn't exist?
        if (ticket == null) {
            return new ModelAndView(new RedirectView("list", true, false));
        }

        // found the ticket, so send it to the view
        model.addAttribute("ticketId", ticketId);
        model.addAttribute("ticket", ticket);

        return new ModelAndView("viewTicket");
    }

    @GetMapping("/{ticketId}/attachment/{attachment:.+}")
    public View downloadAttachment(@PathVariable("ticketId") int ticketId, @PathVariable("attachment") String name) {
        Ticket ticket = ticketService.getTicket(ticketId);
        // no ticket or attachment
        if (ticket == null || ticket.getAttachment() == null) {
            return new RedirectView("list", true, false);
        }

        Attachment attachment = ticket.getAttachment();

        // otherwise we have an attachment, let's download
        return new DownloadView(attachment.getName(), attachment.getContents());
    }

    public static class TicketForm {
        private String subject;
        private String body;
        private MultipartFile attachment;
        private String customerName;

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

        public MultipartFile getAttachment() {
            return attachment;
        }

        public void setAttachment(MultipartFile attachment) {
            this.attachment = attachment;
        }

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }
    }
}
