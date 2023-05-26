package com.gassion.tennis_match_table.entities.MatchModel;

import com.gassion.tennis_match_table.entities.DTO.NewMatchConfigurationDTO;
import com.gassion.tennis_match_table.entities.team.Team;

import java.util.UUID;

public class MatchModelFactory {
    public static MatchModel fromDTO (NewMatchConfigurationDTO newMatchConfigurationDTO) {
        UUID newMatchUUID = UUID.randomUUID();
        Team teamOne = newMatchConfigurationDTO.getTeams().get(0);
        Team teamTwo = newMatchConfigurationDTO.getTeams().get(1);
        int setsCount = newMatchConfigurationDTO.getSetCount();

        return new MatchModel(newMatchUUID, teamOne, teamTwo, setsCount, true);
    }
}
