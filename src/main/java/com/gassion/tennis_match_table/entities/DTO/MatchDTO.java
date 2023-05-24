package com.gassion.tennis_match_table.entities.DTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MatchDTO extends DTO{
    public long matchID;
    public long playerOneID;
    public long playerTwoID;
    public long winnerID;
}
