<%--
  Created by IntelliJ IDEA.
  User: maleg
  Date: 1/17/2024
  Time: 10:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ticket List</title>
</head>
<body>
<h1>Ticket List</h1>
<table>
    <tr>
        <th>Customer Name</th>
        <th>Subject</th>
        <th>View</th>
    </tr>
    <%--@elvariable id="tickets" type="java.util.List"--%>
    <c:forEach items="${tickets}" var="ticket">
        <tr>
            <td>${ticket.value.customerName}</td>
            <td>${ticket.value.subject}</td>
            <td><a href="ticket?id=${ticket.key}">View</a></td>
        </tr>
    </c:forEach>
</table>
<a href="ticketForm">Create Ticket</a>
</body>
</html>

