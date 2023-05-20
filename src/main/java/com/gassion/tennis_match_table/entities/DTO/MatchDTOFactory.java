package com.gassion.tennis_match_table.entities.DTO;

import com.gassion.tennis_match_table.entities.MatchModel.MatchModel;
import com.gassion.tennis_match_table.entities.MatchModel.MatchState;

public class MatchDTOFactory {
    public static TwoPlayersMatchDTO fromMatchModel(MatchModel matchModel) {
        TwoPlayersMatchDTO matchDTO = new TwoPlayersMatchDTO();
        matchDTO.matchUUID = matchModel.getMatchUUID();
        matchDTO.PlayerOneName = matchModel.getPlayerOne().getName();
        matchDTO.PlayerTwoName = matchModel.getPlayerTwo().getName();
        matchDTO.PlayerOneSets = matchModel.getPlayerWonSets(MatchState.PLAYER_ONE_WIN);
        matchDTO.PlayerTwoSets = matchModel.getPlayerWonSets(MatchState.PLAYER_TWO_WIN);
        matchDTO.PlayerOneGames = matchModel.getPlayerWonGames(MatchState.PLAYER_ONE_WIN);
        matchDTO.PlayerTwoGames = matchModel.getPlayerWonGames(MatchState.PLAYER_TWO_WIN);
        matchDTO.PlayerOneScores = matchModel.getPlayerWonScores(matchModel.getPlayerOne().getName());
        matchDTO.PlayerTwoScores = matchModel.getPlayerWonScores(matchModel.getPlayerTwo().getName());

        return matchDTO;
    }
}
