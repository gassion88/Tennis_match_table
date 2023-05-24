package com.gassion.tennis_match_table.controllers;

import com.gassion.tennis_match_table.Util.exceptions.MatchNotFoundException;
import com.gassion.tennis_match_table.entities.DTO.MatchDTOFactory;
import com.gassion.tennis_match_table.entities.DTO.OngoingMatchDTO;
import com.gassion.tennis_match_table.entities.MatchModel.MatchModel;
import com.gassion.tennis_match_table.service.MatchScoreCalculationService;
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
        try {
            UUID matchUUID = UUID.fromString(request.getParameter("uuid"));

            MatchModel match = OngoingMatchesService.getMatchModel(matchUUID);
            OngoingMatchDTO matchDTO = MatchDTOFactory.fromMatchModel(match);

            MATCH_SCORE_VIEW.display(request, response, matchDTO);
        } catch (MatchNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String playerName = request.getParameter("goal");
            UUID matchUUID = UUID.fromString(request.getParameter("uuid"));

            MatchModel matchModel = OngoingMatchesService.getMatchModel(matchUUID);

            MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService(matchModel);
            matchScoreCalculationService.goal(playerName);

            if (!matchModel.isOngoing()) {
                long savedMatchId = OngoingMatchesService.saveMatch(matchModel);
                response.sendRedirect(request.getContextPath() + "/match/" + savedMatchId);
                return;
            }

            OngoingMatchDTO matchDTO = MatchDTOFactory.fromMatchModel(matchModel);
            MATCH_SCORE_VIEW.display(request, response, matchDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
