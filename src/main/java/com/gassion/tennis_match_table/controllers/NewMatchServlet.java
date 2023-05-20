package com.gassion.tennis_match_table.controllers;

import com.gassion.tennis_match_table.Util.ValidateUtil;
import com.gassion.tennis_match_table.entities.Player;
import com.gassion.tennis_match_table.entities.factories.PlayerFactory;
import com.gassion.tennis_match_table.service.OngoingMatchesService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "NewMatchServlet", value = "/new-match")
public class NewMatchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/new_match.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            ValidateUtil.newMatchRequestValidate(request);
            List<Player> players = PlayerFactory.getPlayersFromRequest(request);

            UUID newMatchKey = OngoingMatchesService.createMatch(players);
            //MatchDTO newMatch = OngoingMatchesService.getMatchDTO(newMatchKey);

            response.sendRedirect(request.getContextPath() + "/match-score?uuid=" + newMatchKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
