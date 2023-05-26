<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New match</title>
</head>
<body>
    <div>
        <div class="nav">
            <a href="../match_scores.jsp">Tennis scoreboard</a>
            <a class="current" href="${pageContext.request.contextPath}/new-match">New match</a>
            <a href="../matches.jsp">Recent matches</a>
        </div>

        <div class="content">
            <h1>New match</h1>

            <form action="${pageContext.request.contextPath}/new-match" method="post">
                <div>
                    <div>
                        <label for="player1"><strong>Player 1 name</strong></label>
                        <input type="text" name="player1" id="player1" placeholder="P1 name" required >
                        <label for="player2"><strong>Player 2 name</strong></label>
                        <input type="text" name="player2" id="player2" placeholder="P2 name" required >

                        <label ><strong>Sets count</strong></label>
                        <input checked type="radio" id="sets" name="setsCount" value="3">
                        <label for="sets">3</label>
                        <input type="radio" id="sets2" name="setsCount" value="5">
                        <label for="sets2">5</label>

                        <input type="submit" value="Start match!">
                    </div>
                </div>
            </form>

        </div>

        <div class="footer">
            <p>footer</p>
        </div>

    </div>
</body>
</html>
