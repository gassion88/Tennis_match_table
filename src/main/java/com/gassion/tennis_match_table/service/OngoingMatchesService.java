package com.gassion.tennis_match_table.service;

import com.gassion.tennis_match_table.Util.ValidateUtil;
import com.gassion.tennis_match_table.Util.exceptions.MatchNotFoundException;
import com.gassion.tennis_match_table.entities.DTO.NewMatchConfigurationDTO;
import com.gassion.tennis_match_table.entities.MatchModel.*;
import com.gassion.tennis_match_table.entities.Match;
import com.gassion.tennis_match_table.entities.Player;
import com.gassion.tennis_match_table.repository.MatchDAO;
import com.gassion.tennis_match_table.repository.PlayerDAO;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {
    public static Map<UUID, MatchModel> ongoingMatches = new ConcurrentHashMap<>();
    private static final MatchDAO MATCH_DAO = new MatchDAO();
    private static final PlayerDAO PLAYER_DAO = new PlayerDAO();

    private static final ValidateUtil VALIDATE_UTIL = new ValidateUtil();

    public static UUID createMatch(NewMatchConfigurationDTO newMatchConfigurationDTO) throws Exception {
        VALIDATE_UTIL.ongoingMatchExistValidation(newMatchConfigurationDTO.getPlayers());

        MatchModel newMatchModel = MatchModelFactory.fromDTO(newMatchConfigurationDTO);

        ongoingMatches.put(newMatchModel.getMatchUUID(), newMatchModel);
        return newMatchModel.getMatchUUID();
    }

    public static MatchModel getMatchModel(UUID matchKey) throws MatchNotFoundException {
        MatchModel match = ongoingMatches.get(matchKey);

        if (match == null) {
            throw new MatchNotFoundException("Match not found");
        }

        return ongoingMatches.get(matchKey);
    }

    public static void deleteMatch(Long matchID) {

    }

    public static void saveMatch(Match match) {

    }
}
