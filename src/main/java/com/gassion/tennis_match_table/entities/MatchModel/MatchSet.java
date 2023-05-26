package com.gassion.tennis_match_table.entities.MatchModel;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class MatchSet {
    private List<MatchGame> games = new ArrayList<>();
    private MatchState state;
    private boolean nowTaiBreak;

    public MatchSet() {
        this.state = MatchState.ONGOING;
        this.games.add(new MatchGame());
    }

    public void addGameToSet() {
        MatchGame newGame = new MatchGame();
        newGame.setState(MatchState.ONGOING);

        games.add(newGame);
    }

    public boolean isOngoing() {
        return state == MatchState.ONGOING;
    }
}