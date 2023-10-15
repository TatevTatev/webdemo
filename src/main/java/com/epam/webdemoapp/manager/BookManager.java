package com.epam.webdemoapp.manager;

import com.epam.webdemoapp.db.DBConnectionProvider;
import com.epam.webdemoapp.models.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();






    public void addBook(Book book){

    try {
        PreparedStatement preparedStatement = connection.prepareStatement
                ("INSERT INTO book(book_name, author) VALUES(?,?);");
        preparedStatement.setString(1, book.getBookName());
        preparedStatement.setString(2, book.getAuthor());
        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }

}
    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT * FROM book");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setBookName(resultSet.getString("book_name"));
                book.setAuthor(resultSet.getString("author"));
                book.setUserId(resultSet.getInt("user_id"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public List<Book> getAllUnassignedBooks() {
        List<Book> books = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT * FROM book where user_id is null");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setBookName(resultSet.getString("book_name"));
                book.setAuthor(resultSet.getString("author"));
                book.setUserId(resultSet.getInt("user_id"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public Book getById(Integer id) {
        connection = DBConnectionProvider.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT * FROM book where id=?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setBookName(resultSet.getString("book_name"));
                book.setAuthor(resultSet.getString("author"));
                book.setUserId(resultSet.getInt("user_id"));
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void update(Book book) {
        if (book != null) {
            connection = DBConnectionProvider.getInstance().getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE book SET book_name=?, author=?, user_id=? WHERE id=?"
                );
                preparedStatement.setString(1, book.getBookName());
                preparedStatement.setString(2, book.getAuthor());
                preparedStatement.setInt(3, book.getUserId());
                preparedStatement.setInt(4, book.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
