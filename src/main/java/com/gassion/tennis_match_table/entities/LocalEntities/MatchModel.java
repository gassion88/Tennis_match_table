package com.gassion.tennis_match_table.entities.LocalEntities;

import com.gassion.tennis_match_table.entities.Player;
import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
public class MatchModel {
    private UUID matchUUID;
    private Player PlayerOne;
    private Player PlayerTwo;
    private List<MatchSet> sets;
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
}
