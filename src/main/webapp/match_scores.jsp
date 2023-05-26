<%@ page import="com.gassion.tennis_match_table.entities.DTO.OngoingMatchDTO" %>
<%
    OngoingMatchDTO matchDTO = (OngoingMatchDTO) request.getAttribute("matchJSON");
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
                <th><%=matchDTO.teamOne%></th>
                <th><%=matchDTO.teamTwo%></th>
            </tr>
            <tr>
                <td>Score</td>
                <td><%=matchDTO.TeamOneScoresWin%></td>
                <td><%=matchDTO.PlayerTwoScores%></td>
            </tr>
            <tr>
                <td>Game</td>
                <td><%=matchDTO.TeamOneGamesWin%></td>
                <td><%=matchDTO.PlayerTwoGames%></td>
            </tr>
            <tr>
                <td>Set</td>
                <td><%=matchDTO.TeamOneSetsWin%></td>
                <td><%=matchDTO.TeamTwoSetWin%></td>
            </tr>
        </table>

        <div>
            <form action="match-score?uuid=<%=matchDTO.matchUUID.toString()%>" method="post" name="scoredform">
                <button name = "goal" value="<%=matchDTO.teamOne%>"><%=matchDTO.teamOne%> scored!</button>
                <button name="goal" value="<%=matchDTO.teamTwo%>"><%=matchDTO.teamTwo%> scored!</button>
            </form>
        </div>

    </div>
</body>
</html>
