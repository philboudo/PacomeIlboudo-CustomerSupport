
<html>
<head>
    <title>Customer Support Ticket #<c:out value="${ticketId}"/></title>
</head>
<body>
<a href="<c:url value='/logout' />">Logout</a>
<h2>Customer Support Ticket</h2>
<h3>Ticket #<c:out value="${ticketId}"/>: <c:out value="${ticket.subject}"/></h3>
<i>Customer Name: <c:out value="${ticket.customerName}"/></i><br>
<c:out value="${ticket.body}"/><br>
Attachments:
<c:if test="${ticket.hasAttachments()}">
        <a href="<c:url value='/ticket/${ticketId}/attachment/${ticket.attachment.name} '/>">
            <c:out value="${ticket.attachment.name}"/></a>
</c:if>
<br><a href="<c:url value='/ticket/list' />">Return to ticket list</a>
</body>
</html>
