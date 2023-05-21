package com.gassion.tennis_match_table.entities.DTO;

import com.gassion.tennis_match_table.entities.MatchModel.MatchModel;
import com.gassion.tennis_match_table.entities.MatchModel.MatchState;

public class MatchDTOFactory {
    public static TwoPlayersMatchDTO fromMatchModel(MatchModel matchModel) {
        TwoPlayersMatchDTO matchDTO = new TwoPlayersMatchDTO();
        matchDTO.matchUUID = matchModel.getMatchUUID();
        matchDTO.PlayerOneName = matchModel.getPlayerOne().getName();
        matchDTO.PlayerTwoName = matchModel.getPlayerTwo().getName();
        matchDTO.PlayerOneSets = matchModel.getPlayerWinsSetsCountToMatch(MatchState.PLAYER_ONE_WIN);
        matchDTO.PlayerTwoSets = matchModel.getPlayerWinsSetsCountToMatch(MatchState.PLAYER_TWO_WIN);
        matchDTO.PlayerOneGames = matchModel.getPlayerWinGamesCountToSet(MatchState.PLAYER_ONE_WIN, matchModel.getCurrentSet());
        matchDTO.PlayerTwoGames = matchModel.getPlayerWinGamesCountToSet(MatchState.PLAYER_TWO_WIN, matchModel.getCurrentSet());
        matchDTO.PlayerOneScores = matchModel.getPlayerWonScoresToGame(matchModel.getPlayerOne().getName(), matchModel.getCurrentGame());
        matchDTO.PlayerTwoScores = matchModel.getPlayerWonScoresToGame(matchModel.getPlayerTwo().getName(), matchModel.getCurrentGame());

        return matchDTO;
    }
}
