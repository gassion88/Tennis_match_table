package com.gassion.tennis_match_table.service;

import com.gassion.tennis_match_table.entities.Match;
import com.gassion.tennis_match_table.entities.Player;
import com.gassion.tennis_match_table.repository.MatchDAO;
import com.gassion.tennis_match_table.repository.PlayerDAO;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {
    public static ConcurrentHashMap<Long, Math> ongoingMatches = new ConcurrentHashMap<>();
    public static MatchDAO matchDAO = new MatchDAO();
    public static PlayerDAO playerDAO = new PlayerDAO();

    public static void createMatch(List<Player> players) {
        Player player1 = playerDAO.getByName(players.get(0).getName());
        Player player2 = playerDAO.getByName(players.get(1).getName());

        Match match = new Match();
        match.setPlayer1(player1.getId());
        match.setPlayer2(player2.getId());

        matchDAO.add(match);
    }

    public static void deleteMatch(Long matchID) {

    }

    public static void setMatch(Match match) {

    }

    public static void saveMatch(Match match) {

    }


}
