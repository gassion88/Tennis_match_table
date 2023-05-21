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
    private int setsCountInGame;
    private boolean taiBreak;
    private MatchState state;

    public void addSetToMatch() {
        MatchSet newSet = new MatchSet();
        newSet.setSetState(MatchState.ONGOING);

        sets.add(newSet);
    }

    public MatchGame getCurrentGame() {
        MatchSet currentSet = getCurrentSet();

        for (MatchGame matchGame : currentSet.getGames()) {
            MatchState gameState = matchGame.getGameState();

            if (gameState == MatchState.ONGOING) {
                return matchGame;
            }
        }

        return currentSet.getGames().get(currentSet.getGames().size()-1);
    }

    public MatchSet getCurrentSet() {
        for (MatchSet set : sets) {
            MatchState matchSetState = set.getSetState();

            if (matchSetState == MatchState.ONGOING) {
                return set;
            }
        }

        return sets.get(sets.size()-1);
    }

    public int getPlayerWinsSetsCountToMatch(MatchState state) {
        int playerWonSets = 0;

        for (MatchSet set : sets) {
            MatchState setState = set.getSetState();

            if (setState == state) {
                playerWonSets++;
            }
        }

        return playerWonSets;
    }

    public int getPlayerWinGamesCountToSet(MatchState state, MatchSet set) {
        if (set.getGames().size() == 0) {
            return 0;
        }

        int playerWonGames = 0;

            for (MatchGame game :  set.getGames()){
                MatchState gameState = game.getGameState();

                if (gameState == state) {
                    playerWonGames++;
                }
            }

        return playerWonGames;
    }

    public int getPlayerWonScoresToGame(String playerName, MatchGame game) {
        if (game.getScores().size() == 0) {
            return 0;
        }

        int playerWonScores = 0;

        for (MatchScore score :  game.getScores()){
            String scorePlayerName = score.getPlayerName();

            if (Objects.equals(scorePlayerName, playerName)) {
                playerWonScores++;
            }
        }

        return playerWonScores;
    }
}
