package com.gassion.tennis_match_table.entities.MatchModel;

import com.gassion.tennis_match_table.entities.Player;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
public class MatchModel {
    private UUID matchUUID;
    private Player PlayerOne;
    private Player PlayerTwo;
    private List<MatchSet> sets = new ArrayList<>();
    private int setsCount;
    private MatchState state;

//    public void addScore(String scoredPlayerName) {
//       MatchGame currentGame = getOrCreateCurrentGame();
//       currentGame.addScoreToGame(scoredPlayerName);
//
//       //updateCurrentGameState();
//    }

    private MatchGame getCurrentGame() {
        MatchSet currentSet = getCurrentSet();

        for (MatchGame matchGame : currentSet.getGames()) {
            MatchState gameState = matchGame.getGameState();

            if (gameState == MatchState.ONGOING) {
                return matchGame;
            }
        }

        return currentSet.getGames().get(currentSet.getGames().size()-1);
    }

    private MatchSet getCurrentSet() {
        for (MatchSet set : sets) {
            MatchState matchSetState = set.getGameState();

            if (matchSetState == MatchState.ONGOING) {
                return set;
            }
        }

        return sets.get(sets.size()-1);
    }

    public int getPlayerScoredSets(MatchState state) {
        int playerWonSets = 0;

        for (MatchSet set : sets) {
            MatchState setState = set.getGameState();

            if (setState == state) {
                playerWonSets++;
            }
        }

        return playerWonSets;
    }

    public int getPlayerWonGames(MatchState state) {
        if (sets.size() == 0) {
            return 0;
        }

        int playerWonGames = 0;

        MatchSet currentSet = getCurrentSet();

            for (MatchGame game :  currentSet.getGames()){
                MatchState gameState = game.getGameState();

                if (gameState == state) {
                    playerWonGames++;
                }
            }

        return playerWonGames;
    }

    public int getPlayerWonScores(String playerName) {
        if (sets.size() == 0) {
            return 0;
        }

        int playerWonScores = 0;

        MatchGame currentGame = getCurrentGame();

        for (MatchScore score :  currentGame.getScores()){
            String scorePlayerName = score.getPlayerName();

            if (Objects.equals(scorePlayerName, playerName)) {
                playerWonScores++;
            }
        }

        return playerWonScores;
    }
}
