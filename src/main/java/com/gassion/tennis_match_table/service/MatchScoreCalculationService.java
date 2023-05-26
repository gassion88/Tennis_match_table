package com.gassion.tennis_match_table.service;

import com.gassion.tennis_match_table.entities.MatchModel.MatchGame;
import com.gassion.tennis_match_table.entities.MatchModel.MatchModel;
import com.gassion.tennis_match_table.entities.MatchModel.MatchSet;
import com.gassion.tennis_match_table.entities.MatchModel.MatchState;

public class MatchScoreCalculationService {
    private final MatchModel match;
    private final int setsCountInGame;
    private final boolean taiBreak;
    private final static int LEAD_ON_POINTS_TO_WIN = 2;
    private final static int GAME_WIN_SCORES_COUNT = 4;
    private final static int SET_WIN_GAMES_COUNT = 6;
    private final static int TAI_BREAK_WIN_SCORES_COUNT = 7;

    public MatchScoreCalculationService(MatchModel match) {
        this.setsCountInGame = match.getSetsCountInGame();
        this.taiBreak = match.isTaiBreak();
        this.match = match;
    }

    public void goal(String scoredPlayerName) {
        addNewScoreToPlayer(scoredPlayerName);

        checkOrUpdateMatchState(match);
    }

    private void checkOrUpdateMatchState(MatchModel match) {
        updateCurrentGame();
        updateCurrentSet();
        updateMatchState();

        if (!match.getCurrentGame().isOngoing() && match.getCurrentSet().isOngoing()) {
            addNewGameToSet();
        } else if (!match.getCurrentSet().isOngoing() && match.isOngoing()) {
            addNewSetToMatch();
        }
    }

    private void addNewSetToMatch() {
        match.addSetToMatch();
    }

    private void addNewGameToSet() {
        match.getCurrentSet().addGameToSet();
    }

    private void addNewScoreToPlayer(String scoredPlayerName) {
        match.getCurrentGame().addScoreToGame(scoredPlayerName);
    }

    private void updateMatchState() {
        int teamOneSetsWinToMatch = match.getTeamWinsSetsCountToMatch(MatchState.TEAM_ONE_WIN);
        int teamTwoSetsWinToMatch = match.getTeamWinsSetsCountToMatch(MatchState.TEAM_TWO_WIN);

        if (match.getSets().size() <= setsCountInGame) {
            if (teamOneSetsWinToMatch == getNumberSetsToWin()) {
                match.setState(MatchState.TEAM_ONE_WIN);
            }

            if (teamTwoSetsWinToMatch == getNumberSetsToWin()) {
                match.setState(MatchState.TEAM_TWO_WIN);
            }
        }

        if (teamOneSetsWinToMatch >= setsCountInGame && teamOneSetsWinToMatch - teamTwoSetsWinToMatch >= LEAD_ON_POINTS_TO_WIN) {
            match.setState(MatchState.TEAM_TWO_WIN);
        } else if (teamTwoSetsWinToMatch >= setsCountInGame && teamTwoSetsWinToMatch - teamOneSetsWinToMatch >= LEAD_ON_POINTS_TO_WIN) {
            match.setState(MatchState.TEAM_TWO_WIN);
        }
    }

    private void updateCurrentSet() {
        MatchSet currentSet = match.getCurrentSet();
        int teamOneGamesWinToSet = match.getTeamWinGamesCountToSet(MatchState.TEAM_ONE_WIN, currentSet);
        int teamTwoGamesWinToSet = match.getTeamWinGamesCountToSet(MatchState.TEAM_TWO_WIN, currentSet);

        if (teamOneGamesWinToSet >= SET_WIN_GAMES_COUNT && teamOneGamesWinToSet - teamTwoGamesWinToSet >= LEAD_ON_POINTS_TO_WIN) {
            currentSet.setState(MatchState.TEAM_ONE_WIN);
        } else if (teamTwoGamesWinToSet >= SET_WIN_GAMES_COUNT && teamTwoGamesWinToSet - teamOneGamesWinToSet >= LEAD_ON_POINTS_TO_WIN) {
            currentSet.setState(MatchState.TEAM_TWO_WIN);
        }

        if (taiBreak) {
            if (teamOneGamesWinToSet == SET_WIN_GAMES_COUNT && teamTwoGamesWinToSet == SET_WIN_GAMES_COUNT) {
                currentSet.setNowTaiBreak(true);
            }
        }
    }

    private void updateCurrentGame() {
        MatchGame currentGame = match.getCurrentGame();
        int teamOneScoresWinToGame = match.getTeamWinScoresToGame(match.getTeamOne(), currentGame);
        int teamTwoScoresWinToGame = match.getTeamWinScoresToGame(match.getTeamTwo(), currentGame);

        if (match.getCurrentSet().isNowTaiBreak()) {
            taiBreakGameCalculation(currentGame, teamOneScoresWinToGame, teamTwoScoresWinToGame);
        } else {
            gameCalculation(currentGame, teamOneScoresWinToGame, teamTwoScoresWinToGame);
        }
    }

    private void taiBreakGameCalculation(MatchGame currentGame, int teamOneScoresWinToGame, int teamTwoScoresWinToGame) {
        MatchSet currentSet = match.getCurrentSet();

        if (teamOneScoresWinToGame >= TAI_BREAK_WIN_SCORES_COUNT && teamOneScoresWinToGame - teamTwoScoresWinToGame >= LEAD_ON_POINTS_TO_WIN) {
            currentGame.setState(MatchState.TEAM_ONE_WIN);
            currentSet.setState(MatchState.TEAM_ONE_WIN);
        } else if (teamTwoScoresWinToGame >= TAI_BREAK_WIN_SCORES_COUNT && teamTwoScoresWinToGame - teamOneScoresWinToGame >= LEAD_ON_POINTS_TO_WIN) {
            currentGame.setState(MatchState.TEAM_TWO_WIN);
            currentSet.setState(MatchState.TEAM_TWO_WIN);
        }
    }

    private void gameCalculation(MatchGame currentGame, int playerOneScoresWinToGame, int playerTwoScoresWinToGame) {
        if (playerOneScoresWinToGame >= GAME_WIN_SCORES_COUNT && playerOneScoresWinToGame - playerTwoScoresWinToGame >= LEAD_ON_POINTS_TO_WIN) {
            currentGame.setState(MatchState.TEAM_ONE_WIN);
        } else if (playerTwoScoresWinToGame >= GAME_WIN_SCORES_COUNT && playerTwoScoresWinToGame - playerOneScoresWinToGame >= LEAD_ON_POINTS_TO_WIN) {
            currentGame.setState(MatchState.TEAM_TWO_WIN);
        }
    }

    private int getNumberSetsToWin() {
        return (match.getSetsCountInGame() / 2) + 1;
    }
}
