package com.gassion.tennis_match_table.view;

import com.gassion.tennis_match_table.entities.MatchModel.MatchModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class NewMatchView extends View{
    String page = "/new_match.jsp";

    @Override
    public void display(HttpServletRequest request, HttpServletResponse response, MatchModel matchDTO) throws IOException, ServletException {
        request.getServletContext().getRequestDispatcher(page).forward(request, response);
    }
}
