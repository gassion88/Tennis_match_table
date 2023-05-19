package com.gassion.tennis_match_table.entities.LocalEntities;


import java.util.List;

public class Match {
    private long matchID;
    private long PlayerOneID;
    private long PlayerTwoID;
    private List<MatchSet> sets;
    private MatchState state;
}
