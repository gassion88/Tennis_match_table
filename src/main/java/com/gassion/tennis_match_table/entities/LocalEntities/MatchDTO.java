package com.gassion.tennis_match_table.entities.LocalEntities;

import com.gassion.tennis_match_table.entities.Player;
import lombok.Data;
import java.util.List;

@Data
public class MatchDTO {
    private long matchID;
    private Player PlayerOne;
    private Player PlayerTwo;
    private List<MatchSet> sets;
    private MatchState state;

    public void addScore(long scoredPlayerID) {
       MatchGame currentGame = getOrCreateCurrentGame();
       currentGame.addScoreToGame(scoredPlayerID, matchID);

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
    }
}
