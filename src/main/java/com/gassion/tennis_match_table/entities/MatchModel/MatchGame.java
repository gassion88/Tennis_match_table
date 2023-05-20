package com.gassion.tennis_match_table.entities.MatchModel;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MatchGame {
    private List<MatchScore> scores = new ArrayList<>();
    private MatchState gameState;

    public void addScoreToGame(String ScoredPlayerName) {
        MatchScore newScore = new MatchScore(ScoredPlayerName);
        scores.add(newScore);
    }
}