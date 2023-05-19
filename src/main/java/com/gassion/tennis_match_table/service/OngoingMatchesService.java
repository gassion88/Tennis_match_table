package com.gassion.tennis_match_table.service;

import com.gassion.tennis_match_table.Util.ValidateUtil;
import com.gassion.tennis_match_table.entities.LocalEntities.MatchDTO;
import com.gassion.tennis_match_table.entities.Match;
import com.gassion.tennis_match_table.entities.Player;
import com.gassion.tennis_match_table.repository.MatchDAO;
import com.gassion.tennis_match_table.repository.PlayerDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {
    public static List<MatchDTO> ongoingMatches = new ArrayList<>();
    private static MatchDAO matchDAO = new MatchDAO();
    private static PlayerDAO playerDAO = new PlayerDAO();

    private static ValidateUtil validateUtil = new ValidateUtil();

    public static void addMatch(List<Player> players) throws Exception{
        Player playerOne = playerDAO.getByName(players.get(0).getName());
        Player playerTwo = playerDAO.getByName(players.get(1).getName());

        validateUtil.ongoingMatchExistValidation(players);

        MatchDTO newMatch = new MatchDTO();
        newMatch.setPlayerOne(playerOne);
        newMatch.setPlayerTwo(playerTwo);

        ongoingMatches.add(newMatch);
    }

    public static void deleteMatch(Long matchID) {

    }

    public static void setMatch(Match match) {

    }

    public static void saveMatch(Match match) {

    }
}
