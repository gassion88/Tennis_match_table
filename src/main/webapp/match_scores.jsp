<%@ page import="com.gassion.tennis_match_table.entities.DTO.TwoPlayersMatchDTO" %>
<%
    TwoPlayersMatchDTO matchDTO = (TwoPlayersMatchDTO) request.getAttribute("matchJSON");
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Match score</title>
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
                <th><%=matchDTO.PlayerOneName%></th>
                <th><%=matchDTO.PlayerTwoName%></th>
            </tr>
            <tr>
                <td>Score</td>
                <td><%=matchDTO.PlayerOneScores%></td>
                <td><%=matchDTO.PlayerTwoScores%></td>
            </tr>
            <tr>
                <td>Game</td>
                <td><%=matchDTO.PlayerOneGames%></td>
                <td><%=matchDTO.PlayerTwoGames%></td>
            </tr>
            <tr>
                <td>Set</td>
                <td><%=matchDTO.PlayerOneSets%></td>
                <td><%=matchDTO.PlayerTwoSets%></td>
            </tr>
        </table>

        <div>
            <form action="match-score?uuid=${uuid}" method="post" name="scoredform">
                <button name = "goal" value="<%=matchDTO.PlayerOneName%>"><%=matchDTO.PlayerOneName%> scored!</button>
                <button name="goal" value="<%=matchDTO.PlayerOneName%>"><%=matchDTO.PlayerOneName%> scored!</button>
            </form>
        </div>

    </div>
</body>
</html>
