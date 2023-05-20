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
    private MatchState state;

    public void addScore(String scoredPlayerName) {
       MatchGame currentGame = getOrCreateCurrentGame();
       currentGame.addScoreToGame(scoredPlayerName);

       //updateCurrentGameState();
    }

    private MatchGame getOrCreateCurrentGame() {
        MatchSet currentSet = getOrCreateCurrentSet();
        return null;
    }

    private MatchSet getOrCreateCurrentSet() {
        for (MatchSet set : sets) {
            MatchState matchSetState = set.getGameState();

            return null;
        }
        return null;
    }

    public int getPlayerWonSets(MatchState state) {
        if (sets.size() == 0) {
            return 0;
        }

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

        MatchSet currentSet = sets.get(sets.size()-1);

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

        MatchSet currentSet = sets.get(sets.size()-1);
        MatchGame currentGame = currentSet.getGames().get(currentSet.getGames().size()-1);

        for (MatchScore score :  currentGame.getScores()){
            String scorePlayerName = score.getPlayerName();

            if (Objects.equals(scorePlayerName, playerName)) {
                playerWonScores++;
            }
        }

        return playerWonScores;
    }
}
