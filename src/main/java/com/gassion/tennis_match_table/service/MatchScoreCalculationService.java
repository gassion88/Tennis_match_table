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
        int playerOneSetsWinToMatch = match.getPlayerWinsSetsCountToMatch(MatchState.PLAYER_ONE_WIN);
        int playerTwoSetsWinToMatch = match.getPlayerWinsSetsCountToMatch(MatchState.PLAYER_ONE_WIN);

        if (match.getSets().size() <= setsCountInGame) {
            if (playerOneSetsWinToMatch == getNumberSetsToWin()) {
                match.setState(MatchState.PLAYER_ONE_WIN);
            }

            if (playerTwoSetsWinToMatch == getNumberSetsToWin()) {
                match.setState(MatchState.PLAYER_TWO_WIN);
            }
        }

        if (playerOneSetsWinToMatch >= setsCountInGame && playerOneSetsWinToMatch - playerTwoSetsWinToMatch >= LEAD_ON_POINTS_TO_WIN) {
            match.setState(MatchState.PLAYER_ONE_WIN);
        } else if (playerTwoSetsWinToMatch >= setsCountInGame && playerTwoSetsWinToMatch - playerOneSetsWinToMatch >= LEAD_ON_POINTS_TO_WIN) {
            match.setState(MatchState.PLAYER_TWO_WIN);
        }
    }

    private void updateCurrentSet() {
        MatchSet currentSet = match.getCurrentSet();
        int playerOneGamesWinToSet = match.getPlayerWinGamesCountToSet(MatchState.PLAYER_ONE_WIN, currentSet);
        int playerTwoGamesWinToSet = match.getPlayerWinGamesCountToSet(MatchState.PLAYER_TWO_WIN, currentSet);

        if (playerOneGamesWinToSet >= SET_WIN_GAMES_COUNT && playerOneGamesWinToSet - playerTwoGamesWinToSet >= LEAD_ON_POINTS_TO_WIN) {
            currentSet.setState(MatchState.PLAYER_ONE_WIN);
        } else if (playerTwoGamesWinToSet >= SET_WIN_GAMES_COUNT && playerTwoGamesWinToSet - playerOneGamesWinToSet >= LEAD_ON_POINTS_TO_WIN) {
            currentSet.setState(MatchState.PLAYER_TWO_WIN);
        }

        if (taiBreak) {
            if (playerOneGamesWinToSet == SET_WIN_GAMES_COUNT && playerTwoGamesWinToSet == SET_WIN_GAMES_COUNT) {
                currentSet.setNowTaiBreak(true);
            }
        }
    }

    private void updateCurrentGame() {
        MatchGame currentGame = match.getCurrentGame();
        int playerOneScoresWinToGame = match.getPlayerWonScoresToGame(match.getPlayerOne().getName(), currentGame);
        int playerTwoScoresWinToGame = match.getPlayerWonScoresToGame(match.getPlayerTwo().getName(), currentGame);

        if (match.getCurrentSet().isNowTaiBreak()) {
            taiBreakGameCalculation(currentGame, playerOneScoresWinToGame, playerTwoScoresWinToGame);
        } else {
            gameCalculation(currentGame, playerOneScoresWinToGame, playerTwoScoresWinToGame);
        }
    }

    private void taiBreakGameCalculation(MatchGame currentGame, int playerOneScoresWinToGame, int playerTwoScoresWinToGame) {
        MatchSet currentSet = match.getCurrentSet();

        if (playerOneScoresWinToGame >= TAI_BREAK_WIN_SCORES_COUNT && playerOneScoresWinToGame - playerTwoScoresWinToGame >= LEAD_ON_POINTS_TO_WIN) {
            currentGame.setState(MatchState.PLAYER_ONE_WIN);
            currentSet.setState(MatchState.PLAYER_ONE_WIN);
        } else if (playerTwoScoresWinToGame >= TAI_BREAK_WIN_SCORES_COUNT && playerTwoScoresWinToGame - playerOneScoresWinToGame >= LEAD_ON_POINTS_TO_WIN) {
            currentGame.setState(MatchState.PLAYER_TWO_WIN);
            currentSet.setState(MatchState.PLAYER_TWO_WIN);
        }
    }

    private void gameCalculation(MatchGame currentGame, int playerOneScoresWinToGame, int playerTwoScoresWinToGame) {
        if (playerOneScoresWinToGame >= GAME_WIN_SCORES_COUNT && playerOneScoresWinToGame - playerTwoScoresWinToGame >= LEAD_ON_POINTS_TO_WIN) {
            currentGame.setState(MatchState.PLAYER_ONE_WIN);
        } else if (playerTwoScoresWinToGame >= GAME_WIN_SCORES_COUNT && playerTwoScoresWinToGame - playerOneScoresWinToGame >= LEAD_ON_POINTS_TO_WIN) {
            currentGame.setState(MatchState.PLAYER_TWO_WIN);
        }
    }

    private int getNumberSetsToWin() {
        return (match.getSetsCountInGame() / 2) + 1;
    }
}
