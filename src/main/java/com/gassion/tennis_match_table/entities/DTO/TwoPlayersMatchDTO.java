package com.gassion.tennis_match_table.entities.DTO;

import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class TwoPlayersMatchDTO extends DTO{
    public UUID matchUUID;
    public String PlayerOneName;
    public String PlayerTwoName;
    public int PlayerOneSets;
    public int PlayerTwoSets;
    public int PlayerOneGames;
    public int PlayerTwoGames;
    public int PlayerOneScores;
    public int PlayerTwoScores;
}
