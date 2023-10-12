package com.epam.webdemoapp.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionProvider {
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;
    private String dbDriverName;

    public static Connection connection;
    private volatile static DBConnectionProvider instance;


    private DBConnectionProvider() {
        try {
            loadProperties();
            Class.forName(dbDriverName);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    private void loadProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(
                "C:\\Users\\Lenovo\\IdeaProjects\\webdemo\\src\\main\\resources\\db-config.properties"));
        dbDriverName = properties.getProperty("db.source.driverClass");
        dbUrl = properties.getProperty("db.source.url");
        dbUsername = properties.getProperty("db.source.username");
        dbPassword = properties.getProperty("db.source.password");



    }

    public static DBConnectionProvider getInstance() {
        if (instance == null) {
            synchronized (DBConnectionProvider.class) {
                if (instance == null) {
                    instance = new DBConnectionProvider();
                }
            }
        }
        return instance;

    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
