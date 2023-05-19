package com.gassion.tennis_match_table.entities.LocalEntities;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MatchSet {
    private List<MatchGame> games = new ArrayList<>();
    private MatchState gameState;
}