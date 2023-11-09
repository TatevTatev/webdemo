package com.epam.webdemoapp.servlets;

import com.epam.webdemoapp.manager.BookManager;
import com.epam.webdemoapp.manager.UserManager;
import com.epam.webdemoapp.models.Book;
import com.epam.webdemoapp.models.User;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AssignServlet extends HttpServlet {
    BookManager bookManager=new BookManager();
    UserManager userManager=new UserManager();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer selectedUser = Integer.valueOf(req.getParameter("selectedUser"));
        Integer selectedBook = Integer.valueOf(req.getParameter("selectedBook"));


        User user = userManager.getById(selectedUser);
        if (user != null) {
            Book book = bookManager.getById(selectedBook);
            if (book != null && book.getUserId() == 0) {
                book.setUserId(selectedUser);
                bookManager.update(book);
                req.setAttribute("successAssign","Successfully assigned!");
                req.getRequestDispatcher("/admin").forward(req, resp);
            }
        }
    }
}
