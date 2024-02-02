<%--
  Created by IntelliJ IDEA.
  User: maleg
  Date: 1/17/2024
  Time: 10:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Ticket</title>
</head>
<body>
<a href="<c:url value='/logout' />">Logout</a>


<h2>Create a New Ticket</h2>
<form method="POST" action="ticket" enctype="multipart/form-data">
    <input type="hidden" name="action" value="create">
    <label for="customerName">Customer Name:</label>
    <input type="text" id="customerName" name="customerName" required><br><br>
    <label for="subject">Subject:</label>
    <input type="text" id="subject" name="subject" required><br><br>
    <label for="body">Body:</label><br>
    <textarea id="body" name="body" rows="5" cols="40" required></textarea><br><br>
    <label for="attachment">Attachment:</label>
    <input type="file" id="attachment" name="attachment"><br><br>
    <input type="submit" value="Submit">
</form>
<a href="ticket">Return to Ticket List</a>
</body>
</html>
