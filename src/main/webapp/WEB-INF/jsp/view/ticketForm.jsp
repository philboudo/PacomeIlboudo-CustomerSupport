<!DOCTYPE html>
<html>
<head>
    <title>Create a New Ticket</title>
</head>
<body>
<a href="<c:url value='/logout'/>">Logout</a>
<h2>Create a New Ticket</h2>

<form:form method="POST" action="create" modelAttribute = "ticket" enctype="multipart/form-data">
    <form:label path="customerName">Customer Name:</form:label><br>
    <form:input path="customerName" required="true" /><br><br>
    <form:label path="subject">Subject:</form:label><br>
    <form:input path="subject" required="true" /><br><br>
    <form:label path="body">Body:</form:label><br>
    <form:textarea path="body" rows="5" cols="40" required="true" /><br><br>
    <b>Attachment</b><br>
    <form:input path="attachment" type="file"/><br><br>
    <input type="submit" value="Submit">
</form:form>

<a href="<c:url value='/ticket/list' />">Return to Ticket List</a>
</body>
</html>
