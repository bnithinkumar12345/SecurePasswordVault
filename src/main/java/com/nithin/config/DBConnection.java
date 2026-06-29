package com.nithin.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/password_vault";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Nithin@123";

    public static Connection getConnection() {

        try {

            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            System.out.println("Database Connected Successfully!");

            return connection;

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }
}