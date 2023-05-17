package com.gassion.tennis_match_table.Util;

import com.gassion.tennis_match_table.Util.exceptions.RequestParamException;
import jakarta.servlet.http.HttpServletRequest;

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
}
