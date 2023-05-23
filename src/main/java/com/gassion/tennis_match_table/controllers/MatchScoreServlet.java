package com.gassion.tennis_match_table.controllers;

import com.gassion.tennis_match_table.Util.ValidateUtil;
import com.gassion.tennis_match_table.entities.DTO.MatchDTOFactory;
import com.gassion.tennis_match_table.entities.DTO.TwoPlayersMatchDTO;
import com.gassion.tennis_match_table.entities.MatchModel.MatchModel;
import com.gassion.tennis_match_table.entities.MatchModel.MatchState;
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
        UUID matchUUID = UUID.fromString(request.getParameter("uuid"));

        MatchModel match = OngoingMatchesService.getMatchModel(matchUUID);
        TwoPlayersMatchDTO matchDTO = MatchDTOFactory.fromMatchModel(match);

        MATCH_SCORE_VIEW.display(request, response, matchDTO);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String playerName = request.getParameter("goal");
            UUID matchUUID = UUID.fromString(request.getParameter("uuid"));

            MatchModel matchModel = OngoingMatchesService.getMatchModel(matchUUID);

            MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService(matchModel);
            matchScoreCalculationService.goal(playerName);

            if (matchModel.getState() != MatchState.ONGOING) {
                
            }

            TwoPlayersMatchDTO matchDTO = MatchDTOFactory.fromMatchModel(matchModel);
            MATCH_SCORE_VIEW.display(request, response, matchDTO);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
