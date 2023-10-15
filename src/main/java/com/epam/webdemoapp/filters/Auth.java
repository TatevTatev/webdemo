package com.epam.webdemoapp.filters;

import com.epam.webdemoapp.models.User;
import com.epam.webdemoapp.models.UserRole;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Auth extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null && user.getUserRole() == UserRole.ADMIN) {
            chain.doFilter(request, response);
        }else{
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
