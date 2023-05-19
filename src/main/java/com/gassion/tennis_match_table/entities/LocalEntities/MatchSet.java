package com.gassion.tennis_match_table.entities.LocalEntities;

import lombok.Data;

import java.util.List;

@Data
public class MatchSet {
    private long id;
    private long matchID;
    private List<MatchGame> games;
    private MatchState gameState;
    private int counter;
}