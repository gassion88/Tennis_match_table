package com.gassion.tennis_match_table.controllers;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Map;

@WebServlet(name = "NewMatchServlet", value = "/new-match")
public class NewMatchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.getWriter().println("/new-match");
        response.sendRedirect(request.getContextPath() + "/new_match.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> params = request.getParameterMap();

        response.getWriter().println("POST");
    }
}
