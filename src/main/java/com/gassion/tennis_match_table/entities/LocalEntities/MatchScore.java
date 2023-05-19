package com.gassion.tennis_match_table.entities.LocalEntities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MatchScore {
    private long matchID;
    private long playerID;
}
