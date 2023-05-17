<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<div class="nav">
    <a href="match_scores.jsp">Tennis scoreboard</a>
    <a href="${pageContext.request.contextPath}/new-match">New match</a>
    <a href="matches.jsp">Recent matches</a>
</div>

</body>
</html>