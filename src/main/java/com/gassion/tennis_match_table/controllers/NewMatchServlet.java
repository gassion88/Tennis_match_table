package com.gassion.tennis_match_table.controllers;

import com.gassion.tennis_match_table.Util.ValidateUtil;
import com.gassion.tennis_match_table.entities.DTO.NewMatchConfigurationDTO;
import com.gassion.tennis_match_table.entities.factories.NewMatchConfigurationDTOFactory;
import com.gassion.tennis_match_table.service.OngoingMatchesService;
import com.gassion.tennis_match_table.view.NewMatchView;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "NewMatchServlet", value = "/new-match")
public class NewMatchServlet extends HttpServlet {
    private static final NewMatchView NEW_MATCH_VIEW = new NewMatchView();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        NEW_MATCH_VIEW.display(request, response, null);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            ValidateUtil.newMatchRequestValidate(request);
            NewMatchConfigurationDTO newMatchConfigurationDTO = NewMatchConfigurationDTOFactory.fromRequest(request);

            UUID newMatchKey = OngoingMatchesService.createMatch(newMatchConfigurationDTO);

            response.sendRedirect(request.getContextPath() + "/match-score?uuid=" + newMatchKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
