<html>
<head>
  <title>Session Tracking</title>
</head>
<body>
<a href="<c:url value='/logout'/>">Logout</a>
<h2>Session Tracking</h2>
There are a total of <c:out value="${numSessions}"/> active sessions going on.
<ul>
  <c:forEach items="${sessionList}" var="session">
    <li><c:out value="${session.id} - ${session.getAttribute('username')} - last active ${(now-session.getLastAccessedTime())/1000} seconds ago"/></li>
  </c:forEach>
</ul>
</body>
</html>
