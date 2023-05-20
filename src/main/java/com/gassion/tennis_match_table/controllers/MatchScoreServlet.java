package com.gassion.tennis_match_table.controllers;

import com.gassion.tennis_match_table.entities.LocalEntities.MatchModel;
import com.gassion.tennis_match_table.service.OngoingMatchesService;
import com.gassion.tennis_match_table.view.MatchScoreView;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "MatchScoreServlet", value = "/match-score")
public class MatchScoreServlet extends HttpServlet {
    private final static MatchScoreView MATCH_SCORE_VIEW = new MatchScoreView();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UUID matchUUID = UUID.fromString(request.getParameter("uuid"));
        MatchModel match = OngoingMatchesService.getMatchDTO(matchUUID);

        MATCH_SCORE_VIEW.display(request, response, match);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(123);
    }
}
