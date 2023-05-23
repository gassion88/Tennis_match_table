package com.gassion.tennis_match_table.entities.factories;

import com.gassion.tennis_match_table.entities.DTO.NewMatchConfigurationDTO;
import com.gassion.tennis_match_table.entities.Player;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class NewMatchConfigurationDTOFactory {
    public static NewMatchConfigurationDTO fromRequest(HttpServletRequest request) {
        List<Player> players = PlayerFactory.getPlayersFromRequest(request);
        int setsCount = Integer.parseInt(request.getParameter("setsCount"));

        return new NewMatchConfigurationDTO(players, setsCount);
    }
}
