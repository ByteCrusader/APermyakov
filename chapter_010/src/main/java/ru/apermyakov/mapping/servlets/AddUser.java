package ru.apermyakov.mapping.servlets;

import ru.apermyakov.mapping.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddUser extends HttpServlet{

    private final UserService service = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.service.create(req.getSession().getAttribute("userName").toString(), req.getHeader("Authorization"));
    }
}
