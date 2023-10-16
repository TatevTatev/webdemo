package com.epam.webdemoapp.servlets;

import com.epam.webdemoapp.manager.UserManager;
import com.epam.webdemoapp.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditUserServlet extends HttpServlet{

       UserManager userManager = new UserManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("id");
        int id=Integer.parseInt(userId);
        User user = userManager.getById(id);
        if (user!=null){
            req.setAttribute("userToEdit",user);
            req.getRequestDispatcher("/edituser.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String userId = req.getParameter("userId");

        User user = userManager.getById(Integer.valueOf(userId));
        if (user != null) {
            user.setName(name);
            user.setLastName(lastName);
            user.setEmail(email);
            userManager.update(user);
        }

        resp.sendRedirect("/webdemo_war/users");
    }

}
