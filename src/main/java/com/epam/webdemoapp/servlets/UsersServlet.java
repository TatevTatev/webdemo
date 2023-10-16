package com.epam.webdemoapp.servlets;

import com.epam.webdemoapp.manager.BookManager;
import com.epam.webdemoapp.manager.UserManager;
import com.epam.webdemoapp.models.Book;
import com.epam.webdemoapp.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserManager userManager =new UserManager();
        BookManager bookManager=new BookManager();
        List<Book> books=bookManager.getAll();
        List<User> users = userManager.getAll();
        req.setAttribute("books",books);
        req.setAttribute("users",users);
        req.getRequestDispatcher("/users.jsp").forward(req,resp);
    }
}