package com.gassion.tennis_match_table.view;

import com.gassion.tennis_match_table.entities.LocalEntities.MatchModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MatchScoreView extends View{
    String page = "/match_scores.jsp";
    @Override
    public void display(HttpServletRequest request, HttpServletResponse response, MatchModel matchDTO) throws IOException, ServletException {
        if (matchDTO != null) {
            request.setAttribute("PlayerOneName", matchDTO.getPlayerOne().getName());
            request.setAttribute("PlayerTwoName", matchDTO.getPlayerTwo().getName());
        }

        request.getServletContext().getRequestDispatcher(page).forward(request, response);
    }
}
