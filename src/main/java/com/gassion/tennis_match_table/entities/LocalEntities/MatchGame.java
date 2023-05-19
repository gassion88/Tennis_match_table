package com.gassion.tennis_match_table.entities.LocalEntities;

import com.gassion.tennis_match_table.entities.Player;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MatchGame {
    private List<MatchScore> scores = new ArrayList<>();
    private MatchState gameState;

    public void addScoreToGame(Player ScoredPlayerName) {
        MatchScore newScore = new MatchScore(ScoredPlayerName);
        scores.add(newScore);
    }
}