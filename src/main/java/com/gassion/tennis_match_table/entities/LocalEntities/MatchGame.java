package com.gassion.tennis_match_table.entities.LocalEntities;

import lombok.Data;

import java.util.List;

@Data
public class MatchGame {
    private long id;
    private long matchID;
    private List<MatchScore> scores;
    private MatchState gameState;
    private int counter;
}