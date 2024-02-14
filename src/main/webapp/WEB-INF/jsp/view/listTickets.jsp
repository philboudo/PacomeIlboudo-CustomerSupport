<!DOCTYPE html>
<html>
<head>
    <title>Customer Support</title>
</head>
<body>
<a href="<c:url value='/logout'/>">Logout</a>
<h2>Tickets</h2>
<a href="<c:url value='/ticket/create'/>">Create Ticket</a><br><br>
<c:choose>
    <c:when test="${ticketDatabase.size() == 0}">
        <p>There are no tickets yet...</p>
    </c:when>
    <c:otherwise>
        <c:forEach var="ticket" items="${ticketDatabase}">
            Ticket#:&nbsp;<c:out value="${ticket.id}"/>
            <a href="<c:url value='/ticket/view/${ticket.id}'/>">
                <c:out value="${ticket.subject}"/></a><br>
        </c:forEach>
    </c:otherwise>
</c:choose>
</body>
</html>
