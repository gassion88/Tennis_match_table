package com.gassion.tennis_match_table.Util;

import com.gassion.tennis_match_table.Util.exceptions.RequestParamException;
import com.gassion.tennis_match_table.entities.MatchModel.MatchState;
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

        if (params.size() != 3) {
            throw new RequestParamException("Wrong number of arguments");
        }
    }

    public static void matchEndValidate(MatchState state) throws Exception {
        if (state != MatchState.ONGOING) {
            throw new Exception("Match end");
        }
    }

    public static void matchRequestValidate(HttpServletRequest request) throws RequestParamException {
        long matchID = getMatchIDInRequest(request);

        if (matchID == 0) {
            throw new RequestParamException("Invalid ID value");
        }
    }

    public static long getMatchIDInRequest(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");

        return Long.parseLong(pathParts[1]);
    }

//    public void ongoingMatchExistValidation(List<Player> players) throws Exception {
//        String newMatchPlayerOneName = players.get(0).getName();
//        String newMatchPlayerTwoName = players.get(1).getName();
//
//        if (OngoingMatchesService.ongoingMatches.containsKey(newMatchPlayerOneName + newMatchPlayerTwoName) ||
//                OngoingMatchesService.ongoingMatches.containsKey(newMatchPlayerTwoName + newMatchPlayerOneName)) {
//            throw new Exception("Exist Players");
//        }
//    }
}
