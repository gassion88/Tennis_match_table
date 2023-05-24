package com.gassion.tennis_match_table.view;

import com.gassion.tennis_match_table.entities.DTO.DTO;
import com.gassion.tennis_match_table.entities.DTO.MatchDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MatchView extends View{
    private static final String PAGE = "/match.jsp";
    @Override
    public void display(HttpServletRequest request, HttpServletResponse response, DTO dto) throws IOException, ServletException {
        request.setAttribute("matchDTO", dto);

        request.getServletContext().getRequestDispatcher(PAGE).forward(request, response);
    }
}
