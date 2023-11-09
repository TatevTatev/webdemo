package com.epam.webdemoapp.servlets;

import com.epam.webdemoapp.manager.BookManager;
import com.epam.webdemoapp.manager.UserManager;
import com.epam.webdemoapp.models.Book;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class UnassignServlet extends HttpServlet {
    BookManager bookManager;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        bookManager = context.getBean("bookManager", BookManager.class);

        String strId = req.getParameter("id");
        int id = Integer.parseInt(strId);
        Book book = bookManager.getById(id);
        if (book != null) {
            bookManager.unassignBook(book);
            req.getRequestDispatcher("/users").forward(req,resp);
        }
    }
}
