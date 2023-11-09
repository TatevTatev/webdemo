package com.epam.webdemoapp.servlets;

import com.epam.webdemoapp.manager.BookManager;
import com.epam.webdemoapp.models.Book;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AddBookServlet extends HttpServlet {
    BookManager bookManager = new BookManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookName = req.getParameter("bookName");
        String author = req.getParameter("author");

        Book book = new Book();
        book.setBookName(bookName);
        book.setAuthor(author);

        bookManager.addBook(book);
        req.setAttribute("bookAdded","Book is added");
        req.getRequestDispatcher("/admin").forward(req,resp);
    }
}
