package com.gassion.tennis_match_table.Util;

import com.gassion.tennis_match_table.Util.exceptions.RequestParamException;
import com.gassion.tennis_match_table.entities.LocalEntities.MatchDTO;
import com.gassion.tennis_match_table.entities.Player;
import com.gassion.tennis_match_table.service.OngoingMatchesService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ValidateUtil {

    public static void newMatchRequestValidate(HttpServletRequest request) throws RequestParamException {
        Map<String, String[]> params = request.getParameterMap();
        String player1 = request.getParameter("player1");
        String player2 = request.getParameter("player2");

        if(Objects.equals(player1.trim(), "") || Objects.equals(player2.trim(), "")) {
            throw new RequestParamException("Required fields are not filled");
        }

        if (params.size() != 2) {
            throw new RequestParamException("Wrong number of arguments");
        }
    }

    public void ongoingMatchExistValidation(List<Player> players) throws Exception {
        String newMatchPlayerOneName = players.get(0).getName();
        String newMatchPlayerTwoName = players.get(1).getName();
        List<MatchDTO> ongoingMatches = OngoingMatchesService.ongoingMatches;

        for (MatchDTO match : ongoingMatches) {
            String ongoingMatchPlayersNames = match.getPlayerOne().getName() + match.getPlayerTwo().getName();

            if (ongoingMatchPlayersNames.equals(newMatchPlayerOneName + newMatchPlayerTwoName) ||
                    ongoingMatchPlayersNames.equals(newMatchPlayerTwoName + newMatchPlayerOneName)) {
                throw new Exception("Exist Players");
            }
        }



    }
}
