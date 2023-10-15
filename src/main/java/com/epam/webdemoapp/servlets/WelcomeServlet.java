package com.epam.webdemoapp.servlets;

import com.epam.webdemoapp.manager.UserManager;
import com.epam.webdemoapp.models.User;
import com.epam.webdemoapp.models.UserRole;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WelcomeServlet extends HttpServlet {
   UserManager userManager=new UserManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = userManager.getUserByEmailAndPassword(email, password);

        if (user==null){
            req.setAttribute("emailAndPasswordCheck", "Your email or password is wrong");
           req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }else {
            req.getSession().setAttribute("user",user);
            if (user.getUserRole()== UserRole.USER){
            resp.sendRedirect("/webdemo_war/welcome.jsp");
            }
            if (user.getUserRole()== UserRole.ADMIN){
                resp.sendRedirect("/webdemo_war/admin");
            }

        }



    }
}
