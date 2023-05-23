package com.gassion.tennis_match_table.entities.DTO;

import com.gassion.tennis_match_table.entities.MatchModel.MatchModel;
import com.gassion.tennis_match_table.entities.MatchModel.MatchState;

import java.util.UUID;

public class MatchDTOFactory {
    public static TwoPlayersMatchDTO fromMatchModel(MatchModel matchModel) {
        UUID matchUUID = matchModel.getMatchUUID();
        String playerOneName = matchModel.getPlayerOne().getName();
        String playerTwoName = matchModel.getPlayerTwo().getName();
        int playerOneSets = matchModel.getPlayerWinsSetsCountToMatch(MatchState.PLAYER_ONE_WIN);
        int playerTwoSets = matchModel.getPlayerWinsSetsCountToMatch(MatchState.PLAYER_TWO_WIN);
        int playerOneGames = matchModel.getPlayerWinGamesCountToSet(MatchState.PLAYER_ONE_WIN, matchModel.getCurrentSet());
        int playerTwoGames = matchModel.getPlayerWinGamesCountToSet(MatchState.PLAYER_TWO_WIN, matchModel.getCurrentSet());
        int playerOneScores = matchModel.getPlayerWonScoresToGame(matchModel.getPlayerOne().getName(), matchModel.getCurrentGame());
        int playerTwoScores = matchModel.getPlayerWonScoresToGame(matchModel.getPlayerTwo().getName(), matchModel.getCurrentGame());

        return new TwoPlayersMatchDTO(matchUUID, playerOneName, playerTwoName, playerOneSets, playerTwoSets, playerOneGames, playerTwoGames, playerOneScores, playerTwoScores);
    }
}
