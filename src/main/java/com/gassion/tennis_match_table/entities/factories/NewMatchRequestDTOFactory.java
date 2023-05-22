package com.gassion.tennis_match_table.entities.factories;

import com.gassion.tennis_match_table.entities.DTO.NewMatchRequestDTO;
import com.gassion.tennis_match_table.entities.Player;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class NewMatchRequestDTOFactory {
    public static NewMatchRequestDTO fromRequest(HttpServletRequest request) {
        List<Player> players = PlayerFactory.getPlayersFromRequest(request);
        int setsCount = Integer.parseInt(request.getParameter("setsCount"));

        return new NewMatchRequestDTO(players, setsCount);
    }
}
