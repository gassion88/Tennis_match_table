package com.gassion.tennis_match_table.entities.MatchModel;

import com.gassion.tennis_match_table.entities.DTO.NewMatchConfigurationDTO;
import com.gassion.tennis_match_table.entities.Player;

import java.util.UUID;

public class MatchModelFactory {
    public static MatchModel fromDTO (NewMatchConfigurationDTO newMatchConfigurationDTO) {
        UUID newMatchUUID = UUID.randomUUID();
        Player playerOne = newMatchConfigurationDTO.getPlayers().get(0);
        Player playerTwo = newMatchConfigurationDTO.getPlayers().get(1);
        int setsCount = newMatchConfigurationDTO.getSetCount();

        return new MatchModel(newMatchUUID, playerOne, playerTwo, setsCount, true);
    }
}
