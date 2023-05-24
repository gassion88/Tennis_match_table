package com.gassion.tennis_match_table.controllers;

import com.gassion.tennis_match_table.Util.ValidateUtil;
import com.gassion.tennis_match_table.Util.exceptions.MatchNotFoundException;
import com.gassion.tennis_match_table.Util.exceptions.RequestParamException;
import com.gassion.tennis_match_table.entities.DTO.MatchDTO;
import com.gassion.tennis_match_table.entities.DTO.MatchDTOFactory;
import com.gassion.tennis_match_table.entities.DTO.OngoingMatchDTO;
import com.gassion.tennis_match_table.entities.Match;
import com.gassion.tennis_match_table.repository.MatchDAO;
import com.gassion.tennis_match_table.view.MatchView;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.rmi.RemoteException;

@WebServlet(name = "MatchServlet", value = "/match/*")
public class MatchServlet extends HttpServlet {
    private static final MatchView MATCH_VIEW = new MatchView();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ValidateUtil.matchRequestValidate(request);
            long matchID = ValidateUtil.getMatchIDInRequest(request);

            Match match = new MatchDAO().getByID(matchID);

            if (match == null) {
                throw new MatchNotFoundException("Match not found");
            }

            MatchDTO matchDTO = MatchDTOFactory.fromMatch(match);

            MATCH_VIEW.display(request, response, matchDTO);
        } catch (RequestParamException | MatchNotFoundException e) {
            throw new RemoteException(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
