package com.gassion.tennis_match_table.entities.LocalEntities;

import lombok.Data;

@Data
public class MatchScore {
    private long id;
    private long matchID;
    private long playerId;
    private int counter;
}
