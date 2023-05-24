<%@ page import="com.gassion.tennis_match_table.entities.DTO.MatchDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MatchDTO matchDTO = (MatchDTO) request.getAttribute("matchDTO");
%>
<html>
<head>
    <title>Match</title>
</head>
<body>
    <div>
        <a href="${pageContext.request.contextPath}/new-match">New match</a>
        <a href="matches.jsp">Recent matches</a>
    </div>

    <div>
        <table>
            <tr>
                <td></td>
                <th><%=matchDTO.playerOneID%></th>
                <th><%=matchDTO.playerTwoID%></th>
                <th><%=matchDTO.winnerID%></th>
            </tr>
        </table>
    </div>

</body>
</html>
