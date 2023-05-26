package com.gassion.tennis_match_table.entities.DTO;

import com.gassion.tennis_match_table.entities.team.Team;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class OngoingMatchDTO extends DTO{
    public UUID matchUUID;
    public Team teamOne;
    public Team teamTwo;
    public int TeamOneSetsWin;
    public int TeamTwoSetWin;
    public int TeamOneGamesWin;
    public int PlayerTwoGames;
    public int TeamOneScoresWin;
    public int PlayerTwoScores;
}
