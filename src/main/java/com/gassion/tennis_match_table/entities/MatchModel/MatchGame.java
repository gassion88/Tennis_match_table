package com.gassion.tennis_match_table.entities.MatchModel;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MatchGame {
    private List<MatchScore> scores = new ArrayList<>();
    private MatchState state;

    public MatchGame() {
        this.state = MatchState.ONGOING;
    }

    public void addScoreToGame(String ScoredPlayerName) {
        MatchScore newScore = new MatchScore(ScoredPlayerName);
        scores.add(newScore);
    }

    public boolean isOngoing() {
        return state == MatchState.ONGOING;
    }
}