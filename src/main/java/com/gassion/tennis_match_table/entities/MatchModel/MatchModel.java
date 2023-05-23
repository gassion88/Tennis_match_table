package com.gassion.tennis_match_table.entities.MatchModel;

import com.gassion.tennis_match_table.entities.Player;
import lombok.AllArgsConstructor;
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

    public MatchModel(UUID matchUUID, Player playerOne, Player playerTwo, int setsCountInGame, boolean taiBreak) {
        this.matchUUID = matchUUID;
        PlayerOne = playerOne;
        PlayerTwo = playerTwo;
        this.setsCountInGame = setsCountInGame;
        this.taiBreak = taiBreak;
        this.state = MatchState.ONGOING;
        this.sets.add(new MatchSet());
    }

    public void addSetToMatch() {
        MatchSet newSet = new MatchSet();

        sets.add(newSet);
    }

    public MatchGame getCurrentGame() {
        MatchSet currentSet = getCurrentSet();

        for (MatchGame matchGame : currentSet.getGames()) {

            if (matchGame.isOngoing()) {
                return matchGame;
            }
        }

        return currentSet.getGames().get(currentSet.getGames().size()-1);
    }

    public MatchSet getCurrentSet() {
        for (MatchSet set : sets) {

            if (set.isOngoing()) {
                return set;
            }
        }

        return sets.get(sets.size()-1);
    }

    public int getPlayerWinsSetsCountToMatch(MatchState state) {
        int playerWonSets = 0;

        for (MatchSet set : sets) {
            MatchState setState = set.getState();

            if (setState == state) {
                playerWonSets++;
            }
        }

        return playerWonSets;
    }

    public int getPlayerWinGamesCountToSet(MatchState state, MatchSet set) {
        int playerWinGames = 0;

            for (MatchGame game :  set.getGames()){
                MatchState gameState = game.getState();

                if (gameState == state) {
                    playerWinGames++;
                }
            }

        return playerWinGames;
    }

    public int getPlayerWonScoresToGame(String playerName, MatchGame game) {
        int playerWonScores = 0;

        for (MatchScore score :  game.getScores()){
            String scorePlayerName = score.getPlayerName();

            if (Objects.equals(scorePlayerName, playerName)) {
                playerWonScores++;
            }
        }

        return playerWonScores;
    }

    public boolean isOngoing() {
        return state == MatchState.ONGOING;
    }
}
