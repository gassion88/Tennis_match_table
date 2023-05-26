package com.gassion.tennis_match_table.service;

import com.gassion.tennis_match_table.Util.exceptions.MatchNotFoundException;
import com.gassion.tennis_match_table.entities.Match;
import com.gassion.tennis_match_table.entities.MatchModel.MatchModel;
import com.gassion.tennis_match_table.entities.Player;
import com.gassion.tennis_match_table.entities.factories.MatchFactory;
import com.gassion.tennis_match_table.repository.MatchDAO;
import com.gassion.tennis_match_table.repository.PlayerDAO;

public class FinishedMatchesPersistenceService {
    public static long saveMatch(MatchModel matchModel) throws MatchNotFoundException {
//        Player playerOne = matchModel.getPlayerOne();
//        Player playerTwo = matchModel.getPlayerTwo();
//        new PlayerDAO().add(playerOne);
//        new PlayerDAO().add(playerTwo);
//        matchModel.getPlayerOne().setId(playerOne.getId());
//        matchModel.getPlayerTwo().setId(playerTwo.getId());
//
//        Match match = MatchFactory.fromMatchModel(matchModel);
//        new MatchDAO().add(match);
//
//        return match.getId();
        return 12l;
    }

    private static void saveMatchPlayers() {

    }
}
