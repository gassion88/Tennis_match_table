package com.gassion.tennis_match_table.view;

import com.gassion.tennis_match_table.entities.LocalEntities.MatchModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public abstract class View {
    public abstract void display(HttpServletRequest request, HttpServletResponse response, MatchModel matchDTO) throws IOException, ServletException;
}
