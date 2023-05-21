package com.gassion.tennis_match_table.entities.MatchModel;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MatchSet {
    private List<MatchGame> games = new ArrayList<>();
    private MatchState setState;
    private boolean nowTaiBreak;

    public void addGameToSet() {
        MatchGame newGame = new MatchGame();
        newGame.setGameState(MatchState.ONGOING);

        games.add(newGame);
    }
}