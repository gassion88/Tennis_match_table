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
                <th>${PlayerOneName}</th>
                <th>${PlayerTwoName}</th>
            </tr>
            <tr>
                <td>Score</td>
                <td>${p1score}</td>
                <td>${p2score}</td>
            </tr>
            <tr>
                <td>Game</td>
                <td>${p1game}</td>
                <td>${p2game}</td>
            </tr>
            <tr>
                <td>Set</td>
                <td>${p1set}</td>
                <td>${p2set}</td>
            </tr>
        </table>

        <div>
            <form action="match-score?uuid=${uuid}" method="post" name="scoredform">
                <button name = "goal" value="1">${p1name} scored!</button>
                <button name="goal" value="2">${p2name} scored!</button>
            </form>
        </div>

    </div>
</body>
</html>
