package com.epam.webdemoapp.filters;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PasswordValidationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String password = servletRequest.getParameter("password");
        String confirmPassword = servletRequest.getParameter("confirmPassword");
        if (password.equals(confirmPassword)&&password.equals(password)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            httpServletRequest.setAttribute("PasswordMatchError","Password did not match with confirm password");
            httpServletRequest.getRequestDispatcher("/register.jsp").forward(httpServletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
