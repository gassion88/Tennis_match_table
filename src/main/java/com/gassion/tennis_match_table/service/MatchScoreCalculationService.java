package com.gassion.tennis_match_table.service;

import com.gassion.tennis_match_table.entities.MatchModel.MatchGame;
import com.gassion.tennis_match_table.entities.MatchModel.MatchModel;
import com.gassion.tennis_match_table.entities.MatchModel.MatchSet;
import com.gassion.tennis_match_table.entities.MatchModel.MatchState;

public class MatchScoreCalculationService {
    private final MatchModel match;
    private int setsCountInGame;
    private int numberOfSetsToWin;
    private boolean taiBreak;
    private final static int LEAD_ON_POINTS_TO_WIN = 2;
    private final static int GAME_WIN_SCORES_COUNT = 4;
    private final static int SET_WIN_GAMES_COUNT = 6;
    private final static int TAI_BREAK_WIN_SCORES_COUNT = 7;

    public MatchScoreCalculationService(MatchModel match) {
        this.setsCountInGame = match.getSetsCountInGame();
        this.taiBreak = match.isTaiBreak();
        this.match = match;
        this.numberOfSetsToWin = (match.getSetsCountInGame() / 2) + 1;
    }

    public void goal(String scoredPlayerName) {
        MatchGame currentGame = match.getCurrentGame();
        currentGame.addScoreToGame(scoredPlayerName);

        checkOrUpdateMatchState(match);
    }

    private void checkOrUpdateMatchState(MatchModel match) {
        MatchState currentGameState = checkCurrentGame();
        MatchState currentSetState = checkCurrentSet();
        MatchState currentMatchState = checkMatchState();

        if (currentGameState != MatchState.ONGOING && currentSetState == MatchState.ONGOING) {
            addNewGameToSet();
        } else if (currentSetState != MatchState.ONGOING && currentMatchState == MatchState.ONGOING) {
            addNewSetToMatch();
        }
    }

    private void addNewSetToMatch() {
    }

    private void addNewGameToSet() {
    }

    private MatchState checkMatchState() {
        int playerOneSetsWinToMatch = match.getPlayerWinsSetsCountToMatch(MatchState.PLAYER_ONE_WIN);
        int playerTwoSetsWinToMatch = match.getPlayerWinsSetsCountToMatch(MatchState.PLAYER_ONE_WIN);

        if (match.getSets().size() <= setsCountInGame) {
            if (playerOneSetsWinToMatch == numberOfSetsToWin) {
                return MatchState.PLAYER_ONE_WIN;
            }

            if (playerTwoSetsWinToMatch == numberOfSetsToWin) {
                return MatchState.PLAYER_TWO_WIN;
            }
        }

        if (playerOneSetsWinToMatch >= setsCountInGame && playerOneSetsWinToMatch - playerTwoSetsWinToMatch >= LEAD_ON_POINTS_TO_WIN) {
            return MatchState.PLAYER_ONE_WIN;
        } else if (playerTwoSetsWinToMatch >= setsCountInGame && playerTwoSetsWinToMatch - playerOneSetsWinToMatch >= LEAD_ON_POINTS_TO_WIN) {
            return MatchState.PLAYER_TWO_WIN
        }

        match.setState(MatchState.ONGOING);
        return MatchState.ONGOING;
    }

    private MatchState checkCurrentSet() {
        MatchState gameState = MatchState.ONGOING;
        MatchSet currentSet = match.getCurrentSet();
        int playerOneGamesWinToSet = match.getPlayerWinGamesCountToSet(MatchState.PLAYER_ONE_WIN, currentSet);
        int playerTwoGamesWinToSet = match.getPlayerWinGamesCountToSet(MatchState.PLAYER_TWO_WIN, currentSet);

        if (currentSet.isNowTaiBreak()) {
            gameState = taiBreakSetCalculation(playerOneGamesWinToSet, playerTwoGamesWinToSet);
        } else {
            gameState = setCalculation(playerOneGamesWinToSet, playerTwoGamesWinToSet);
        }


        return gameState;
    }

    private MatchState taiBreakSetCalculation(int playerOneGamesWinToSet, int playerTwoGamesWinToSet) {
        MatchState gameState = MatchState.ONGOING;

        if (playerOneGamesWinToSet >= TAI_BREAK_WIN_SCORES_COUNT && playerOneGamesWinToSet - playerTwoGamesWinToSet >= LEAD_ON_POINTS_TO_WIN) {
            gameState =  MatchState.PLAYER_ONE_WIN;
        } else if (playerTwoGamesWinToSet >= TAI_BREAK_WIN_SCORES_COUNT && playerTwoGamesWinToSet - playerOneGamesWinToSet >= LEAD_ON_POINTS_TO_WIN) {
            gameState =  MatchState.PLAYER_TWO_WIN;
        }

        match.getCurrentSet().setSetState(gameState);
        return gameState;
    }

    private MatchState setCalculation(int playerOneGamesWinToSet, int playerTwoGamesWinToSet) {
        MatchState gameState = MatchState.ONGOING;

        if (playerOneGamesWinToSet >= SET_WIN_GAMES_COUNT && playerOneGamesWinToSet - playerTwoGamesWinToSet >= LEAD_ON_POINTS_TO_WIN) {
            gameState = MatchState.PLAYER_ONE_WIN;
        } else if (playerTwoGamesWinToSet >= SET_WIN_GAMES_COUNT && playerTwoGamesWinToSet - playerOneGamesWinToSet >= LEAD_ON_POINTS_TO_WIN) {
            gameState =  MatchState.PLAYER_TWO_WIN;
        }

        if (taiBreak) {
            if (playerOneGamesWinToSet == SET_WIN_GAMES_COUNT && playerTwoGamesWinToSet == SET_WIN_GAMES_COUNT) {
                match.getCurrentSet().setNowTaiBreak(true);
            }
        }

        match.getCurrentSet().setSetState(gameState);
        return gameState;
    }

    private MatchState checkCurrentGame() {
        MatchState gameState = MatchState.ONGOING;
        MatchGame currentGame = match.getCurrentGame();
        int playerOneScores = match.getPlayerWonScoresToGame(match.getPlayerOne().getName(), currentGame);
        int playerTwoScores = match.getPlayerWonScoresToGame(match.getPlayerTwo().getName(), currentGame);

        if (playerOneScores >= GAME_WIN_SCORES_COUNT && playerOneScores - playerTwoScores >= LEAD_ON_POINTS_TO_WIN) {
            gameState = MatchState.PLAYER_ONE_WIN;
        } else if (playerTwoScores >= GAME_WIN_SCORES_COUNT && playerTwoScores - playerOneScores >= LEAD_ON_POINTS_TO_WIN) {
            gameState = MatchState.PLAYER_TWO_WIN;
        }

        currentGame.setGameState(gameState);

        return gameState;
    }


}
