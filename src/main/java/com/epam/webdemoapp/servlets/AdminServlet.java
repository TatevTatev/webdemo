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


public class AdminServlet extends HttpServlet {
    BookManager bookManager = new BookManager();
    UserManager userManager = new UserManager();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookManager.getAllUnassignedBooks();
        List<User> users = userManager.getAll();
        req.setAttribute("books",books);
        req.setAttribute("users",users);
        req.getRequestDispatcher("/adminwelcome.jsp").forward(req,resp);

    }
}
