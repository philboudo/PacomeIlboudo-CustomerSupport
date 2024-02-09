
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
<c:if test="${ticket.numberOfAttachments > 0}">
    Attachments:
    <c:forEach items="${ticket.attachments}" var="attachment" varStatus="status">
        <c:if test="${!status.first}">, </c:if>
        <a href="<c:url value='/ticket'>
                <c:param name='action' value='download' />
                <c:param name='ticketId' value='${ticketId}' />
                <c:param name='attachmentName' value='${attachment.name}'/>
            </c:url>"><c:out value="${attachment.name}"/></a>
    </c:forEach><br>
</c:if>
<br><a href="<c:url value='/ticket/list' />">Return to ticket list</a>
</body>
</html>
