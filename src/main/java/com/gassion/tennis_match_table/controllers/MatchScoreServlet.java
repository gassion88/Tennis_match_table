package com.gassion.tennis_match_table.controllers;

import com.gassion.tennis_match_table.entities.LocalEntities.MatchDTO;
import com.gassion.tennis_match_table.service.OngoingMatchesService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "MatchScoreServlet", value = "/match-score")
public class MatchScoreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UUID matchUUID = UUID.fromString(request.getParameter("uuid"));
        MatchDTO match = OngoingMatchesService.getMatchDTO(matchUUID);


        System.out.println(111);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(123);
    }
}
