package com.gassion.tennis_match_table.service;

import com.gassion.tennis_match_table.Util.ValidateUtil;
import com.gassion.tennis_match_table.entities.MatchModel.MatchModel;
import com.gassion.tennis_match_table.entities.MatchModel.MatchState;
import com.gassion.tennis_match_table.entities.Match;
import com.gassion.tennis_match_table.entities.Player;
import com.gassion.tennis_match_table.repository.MatchDAO;
import com.gassion.tennis_match_table.repository.PlayerDAO;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {
    public static Map<UUID, MatchModel> ongoingMatches = new ConcurrentHashMap<>();
    private static final MatchDAO MATCH_DAO = new MatchDAO();
    private static final PlayerDAO PLAYER_DAO = new PlayerDAO();

    private static final ValidateUtil VALIDATE_UTIL = new ValidateUtil();

    public static UUID createMatch(List<Player> players, int setsCount) throws Exception{
        VALIDATE_UTIL.ongoingMatchExistValidation(players);
        Player playerOne = players.get(0);
        Player playerTwo = players.get(1);

        MatchModel newMatchModel = new MatchModel();
        UUID newMatchKey = UUID.randomUUID();
        newMatchModel.setPlayerOne(playerOne);
        newMatchModel.setPlayerTwo(playerTwo);
        newMatchModel.setMatchUUID(newMatchKey);
        newMatchModel.setSetsCount(setsCount);
        newMatchModel.setState(MatchState.ONGOING);

        ongoingMatches.put(newMatchKey, newMatchModel);
        return newMatchKey;
    }

    public static MatchModel getMatchModel(UUID matchKey) {
        return ongoingMatches.get(matchKey);
    }

    public static void deleteMatch(Long matchID) {

    }

    public static void saveMatch(Match match) {

    }
}
