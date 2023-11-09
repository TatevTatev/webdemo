package com.epam.webdemoapp.servlets;

import com.epam.webdemoapp.manager.UserManager;
import com.epam.webdemoapp.models.User;
import com.epam.webdemoapp.models.UserRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class WelcomeServlet extends HttpServlet {

    UserManager userManager;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userManager=new UserManager();
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        userManager = context.getBean("userManager", UserManager.class);


        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = userManager.getUserByEmailAndPassword(email, password);

        if (user == null) {
            req.setAttribute("emailAndPasswordCheck", "Your email or password is wrong");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("user", user);
            if (user.getUserRole() == UserRole.USER) {
                resp.sendRedirect("/webdemo_war/welcome.jsp");
            }
            if (user.getUserRole() == UserRole.ADMIN) {
                resp.sendRedirect("/webdemo_war/admin");
            }

        }


    }
}
