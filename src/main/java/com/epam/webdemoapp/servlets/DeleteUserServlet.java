package com.epam.webdemoapp.servlets;

import com.epam.webdemoapp.manager.UserManager;
import com.epam.webdemoapp.models.User;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Component
public class DeleteUserServlet extends HttpServlet {
    UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("id");
int id=Integer.parseInt(userId);

            userManager.delete(id);
            resp.sendRedirect("/webdemo_war/users");
        }
    }


