package com.gassion.tennis_match_table.view;

import com.gassion.tennis_match_table.entities.DTO.DTO;
import com.gassion.tennis_match_table.entities.MatchModel.MatchModel;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MatchScoreView extends View{
    private static final Gson GSON = new Gson();
    String page = "/match_scores.jsp";
    @Override
    public void display(HttpServletRequest request, HttpServletResponse response, DTO dto) throws IOException, ServletException {
        if (dto != null) {
            request.setAttribute("matchJSON", dto);
        }

        request.getServletContext().getRequestDispatcher(page).forward(request, response);
    }
}
