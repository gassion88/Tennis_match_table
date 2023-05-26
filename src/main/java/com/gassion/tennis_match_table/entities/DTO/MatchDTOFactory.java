package com.gassion.tennis_match_table.entities.DTO;

import com.gassion.tennis_match_table.entities.Match;
import com.gassion.tennis_match_table.entities.MatchModel.MatchModel;
import com.gassion.tennis_match_table.entities.MatchModel.MatchState;

import java.util.UUID;

public class MatchDTOFactory {
    public static OngoingMatchDTO fromMatchModel(MatchModel matchModel) {
        UUID matchUUID = matchModel.getMatchUUID();
        String playerOneName = matchModel.getPlayerOne().getName();
        String playerTwoName = matchModel.getPlayerTwo().getName();
        int playerOneSets = matchModel.getTeamWinsSetsCountToMatch(MatchState.PLAYER_ONE_WIN);
        int playerTwoSets = matchModel.getTeamWinsSetsCountToMatch(MatchState.PLAYER_TWO_WIN);
        int playerOneGames = matchModel.getPlayerWinGamesCountToSet(MatchState.PLAYER_ONE_WIN, matchModel.getCurrentSet());
        int playerTwoGames = matchModel.getPlayerWinGamesCountToSet(MatchState.PLAYER_TWO_WIN, matchModel.getCurrentSet());
        int playerOneScores = matchModel.getPlayerWinScoresToGame(matchModel.getPlayerOne().getName(), matchModel.getCurrentGame());
        int playerTwoScores = matchModel.getPlayerWinScoresToGame(matchModel.getPlayerTwo().getName(), matchModel.getCurrentGame());

        return new OngoingMatchDTO(matchUUID, playerOneName, playerTwoName, playerOneSets, playerTwoSets, playerOneGames, playerTwoGames, playerOneScores, playerTwoScores);
    }

    public static MatchDTO fromMatch(Match match) {
        long matchID = match.getId();
        long playerOneID = match.getPlayer1();
        long playerTwoID = match.getPlayer2();
        long winnerID = match.getWinner();

        return new MatchDTO(matchID, playerOneID, playerTwoID, winnerID);
    }
}
