package com.example.pacomeilboudocustomersupport;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet(name = "ticket", value = "/ticket")
@MultipartConfig(fileSizeThreshold = 5_242_880, maxFileSize = 20_971_520L, maxRequestSize = 41_943_040L)
public class TicketServlet extends HttpServlet {
    private volatile int TICKET_ID = 1;
    private final Map<Integer, Ticket> ticketDB = new LinkedHashMap<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getSession().getAttribute("username") == null) {
            response.sendRedirect("login");
            return;
        }

        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "createTicket" -> showTicketForm(response);
            case "view" -> {
                try {
                    viewTicket(request, response);
                } catch (ServletException e) {
                    throw new RuntimeException(e);
                }
            }
            case "download" -> downloadAttachment(request, response);
            default -> listTickets(response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        if (action.equals("create")) {
            createTicket(request, response);
        } else {
            response.sendRedirect("ticket");
        }
    }

    private void listTickets(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        // Heading and link to create a ticket
        out.println("<html><body><h2>Customer Support Tickets</h2>");
        out.println("<a href=\"ticket?action=createTicket\">Create Ticket</a><br><br>");

        // List out the tickets
        if (ticketDB.isEmpty()) {
            out.println("There are no customer support tickets yet...");
        } else {
            for (int id : ticketDB.keySet()) {
                Ticket ticket = ticketDB.get(id);
                out.println("Ticket #" + id);
                out.println(": <a href=\"ticket?action=view&ticketId=" + id + "\">");
                out.println(ticket.getSubject() + "</a><br>");
            }
        }
        out.println("</body></html>");
    }

    private void createTicket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Create the ticket and set all values
        String customerName = request.getParameter("customerName");
        String subject = request.getParameter("subject");
        String body = request.getParameter("body");

        Ticket ticket = new Ticket(customerName, subject, body);

        Part attachment = request.getPart("attachment");
        if (attachment != null) {
            Attachment attachmentObj = processAttachment(attachment);
            if (attachmentObj != null) {
                ticket.addAttachment(attachmentObj);
            }
        }

        // Add and synchronize
        int id;
        synchronized (this) {
            id = this.TICKET_ID++;
            ticketDB.put(id, ticket);
        }

        response.sendRedirect("ticket?action=view&ticketId=" + id);
    }

    private Attachment processAttachment(Part attachment) throws IOException {
        if (attachment == null) {
            return null; // No attachment provided
        }

        InputStream in = attachment.getInputStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // Processing the binary data to bytes
        int read;
        final byte[] bytes = new byte[1024];
        while ((read = in.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }

        Attachment attachmentObj = new Attachment();
        attachmentObj.setName(attachment.getSubmittedFileName());
        attachmentObj.setContents(out.toByteArray());

        return attachmentObj;
    }


    private void downloadAttachment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idString = request.getParameter("ticketId");
        String attachmentName = request.getParameter("attachmentName");

        Ticket ticket = getTicket(idString, response);

        if (ticket != null) {
            Attachment attachment = ticket.getAttachmentByName(attachmentName);

            if (attachment != null) {
                response.setHeader("Content-Disposition", "attachment; filename=" + attachment.getName());
                response.setContentType("application/octet-stream");

                ServletOutputStream out = response.getOutputStream();
                out.write(attachment.getContents());
            }
        }
    }

    private void viewTicket(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idString = request.getParameter("ticketId");

        Ticket ticket = getTicket(idString, response);

        if (ticket != null) {
            // Set attributes for the JSP
            request.setAttribute("ticketId", idString);
            request.setAttribute("ticket", ticket);

            // Forward the request to the viewTicket.jsp
            request.getRequestDispatcher("/WEB-INF/jsp/view/viewTicket.jsp").forward(request, response);
        } else {
            // Handle the case where the ticket is not found
            response.sendRedirect("ticket"); // Redirect to ticket list or error page
        }
    }


    private void showTicketForm(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        out.println("<html><body><h2>Create a Customer Support Ticket</h2>");
        out.println("<form method=\"POST\" action=\"ticket\" enctype=\"multipart/form-data\">");
        out.println("<input type=\"hidden\" name=\"action\" value=\"create\">");
        out.println("Customer Name:<br>");
        out.println("<input type=\"text\" name=\"customerName\"><br><br>");
        out.println("Subject:<br>");
        out.println("<input type=\"text\" name=\"subject\"><br><br>");
        out.println("Body:<br>");
        out.println("<textarea name=\"body\" rows=\"5\" cols=\"40\"></textarea><br><br>");
        out.println("Attachment:<br>");
        out.println("<input type=\"file\" name=\"attachment\"><br><br>");
        out.println("<input type=\"submit\" value=\"Submit Ticket\">");
        out.println("</form></body></html>");
    }

    private Ticket getTicket(String idString, HttpServletResponse response) throws IOException {
        if (idString == null || idString.length() == 0) {
            response.sendRedirect("ticket");
            return null;
        }

        try {
            int id = Integer.parseInt(idString);
            Ticket ticket = ticketDB.get(id);
            if (ticket == null) {
                response.sendRedirect("ticket");
                return null;
            }
            return ticket;
        } catch (NumberFormatException e) {
            response.sendRedirect("ticket");
            return null;
        }
    }
}
