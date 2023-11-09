package com.epam.webdemoapp.manager;

import com.epam.webdemoapp.db.DBConnectionProvider;
import com.epam.webdemoapp.models.User;
import com.epam.webdemoapp.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component("userManager")
public class UserManager {

    @Autowired
    private DBConnectionProvider dbConnectionProvider;
    private Connection connection;


    public void add(User user) throws SQLException {
        connection=dbConnectionProvider.getConnection();
        String sql = "INSERT INTO user(name,last_name,email,password,user_role) VALUES (?,?,?,?,?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setString(5, user.getUserRole().name());
        preparedStatement.executeUpdate();
    }

    public User getUserByEmailAndPassword(String email, String password) {
        connection=dbConnectionProvider.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT * FROM user where email=? AND password=?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = new User();
            if (resultSet.next()) {

                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setUserRole(UserRole.valueOf(resultSet.getString("user_role")));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<User> getAll() {
        connection=dbConnectionProvider.getConnection();
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT * FROM user;");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user=new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setUserRole( UserRole.valueOf(resultSet.getString("user_role")));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    public User getById(Integer id) {
        connection=dbConnectionProvider.getConnection();
        connection = DBConnectionProvider.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT * FROM user where id=?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setUserRole(UserRole.valueOf(resultSet.getString("user_role")));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void update(User user) {
        connection=dbConnectionProvider.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("UPDATE user SET name=?, last_name=?, email=? where id=?;");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setInt(4, user.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Integer id) {
        connection=dbConnectionProvider.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("DELETE FROM user where id=?;");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
