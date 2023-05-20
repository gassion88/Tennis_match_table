package com.gassion.tennis_match_table.service;

import com.gassion.tennis_match_table.Util.ValidateUtil;
import com.gassion.tennis_match_table.entities.LocalEntities.MatchDTO;
import com.gassion.tennis_match_table.entities.LocalEntities.MatchState;
import com.gassion.tennis_match_table.entities.Match;
import com.gassion.tennis_match_table.entities.Player;
import com.gassion.tennis_match_table.repository.MatchDAO;
import com.gassion.tennis_match_table.repository.PlayerDAO;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {
    public static Map<UUID, MatchDTO> ongoingMatches = new ConcurrentHashMap<>();
    private static final MatchDAO MATCH_DAO = new MatchDAO();
    private static final PlayerDAO PLAYER_DAO = new PlayerDAO();

    private static final ValidateUtil VALIDATE_UTIL = new ValidateUtil();

    public static UUID createMatch(List<Player> players) throws Exception{
        VALIDATE_UTIL.ongoingMatchExistValidation(players);
        Player playerOne = players.get(0);
        Player playerTwo = players.get(1);

        MatchDTO newMatchDTO = new MatchDTO();
        UUID newMatchKey = UUID.randomUUID();
        newMatchDTO.setPlayerOne(playerOne);
        newMatchDTO.setPlayerTwo(playerTwo);
        newMatchDTO.setMatchUUID(newMatchKey);
        newMatchDTO.setState(MatchState.ONGOING);

        ongoingMatches.put(newMatchKey, newMatchDTO);
        return newMatchKey;
    }

    public static MatchDTO getMatchDTO(UUID matchKey) {
        return ongoingMatches.get(matchKey);
    }

    public static void deleteMatch(Long matchID) {

    }

    public static void saveMatch(Match match) {

    }
}
