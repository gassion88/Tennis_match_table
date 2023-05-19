package com.gassion.tennis_match_table.entities.factories;

import com.gassion.tennis_match_table.entities.Player;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

public class PlayerFactory {
    public static List<Player> getPlayersFromRequest(HttpServletRequest request) {
        List<Player> players = new ArrayList<>();
        String player1Name = request.getParameter("player1");
        String player2Name = request.getParameter("player2");

        Player player1 = new Player();
        Player player2 = new Player();
        player1.setName(player1Name);
        player2.setName(player2Name);

        players.add(player1);
        players.add(player2);

        return players;
    }
}
