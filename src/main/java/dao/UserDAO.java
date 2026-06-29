package com.nithin.dao;

import com.nithin.config.DBConnection;
import com.nithin.model.User;
import com.nithin.util.BCryptUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    // Register User
    public boolean registerUser(User user) {

        String sql = "INSERT INTO users(username, email, master_password) VALUES (?, ?, ?)";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());

            // Hash password before storing
            String hashedPassword = BCryptUtil.hashPassword(user.getMasterPassword());
            ps.setString(3, hashedPassword);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Login User
    public boolean loginUser(String username, String password) {

        String sql = "SELECT master_password FROM users WHERE username = ?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String hashedPassword = rs.getString("master_password");

                return BCryptUtil.checkPassword(password, hashedPassword);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}