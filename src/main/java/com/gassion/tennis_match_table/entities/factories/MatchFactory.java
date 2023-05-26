package com.gassion.tennis_match_table.entities.factories;

import com.gassion.tennis_match_table.entities.Match;
import com.gassion.tennis_match_table.entities.MatchModel.MatchModel;

public class MatchFactory {
    public static Match fromMatchModel(MatchModel matchModel) {
        long playerOne = matchModel.getPlayerOne().getId();
        long playerTwo = matchModel.getPlayerTwo().getId();
        long winner = matchModel.getWinner().getId();

        Match match = new Match();
        match.setPlayer1(playerOne);
        match.setPlayer2(playerTwo);
        match.setWinner(winner);

        return match;
    }
}
