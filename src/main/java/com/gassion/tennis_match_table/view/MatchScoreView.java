package com.gassion.tennis_match_table.view;

import com.gassion.tennis_match_table.entities.LocalEntities.MatchDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MatchScoreView extends View{
    String page = "/match_scores.jsp";
    @Override
    public void display(HttpServletRequest request, HttpServletResponse response, MatchDTO matchDTO) throws IOException, ServletException {
        if (matchDTO != null) {
            request.setAttribute("match", matchDTO);
        }

        request.getServletContext().getRequestDispatcher(page).forward(request, response);
    }
}
