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

            String hashedPassword = BCryptUtil.hashPassword(user.getMasterPassword());

            ps.setString(3, hashedPassword);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Login User
    public User loginUser(String username, String password) {

        String sql = "SELECT * FROM users WHERE username=?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String hashedPassword = rs.getString("master_password");

                if (BCryptUtil.checkPassword(password, hashedPassword)) {

                    User user = new User();

                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setEmail(rs.getString("email"));

                    return user;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Forgot Password
    public boolean resetPassword(String username,
                                 String email,
                                 String newPassword) {

        String checkUser =
                "SELECT * FROM users WHERE username=? AND email=?";

        String updatePassword =
                "UPDATE users SET master_password=? WHERE username=?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement check = connection.prepareStatement(checkUser);

            check.setString(1, username);
            check.setString(2, email);

            ResultSet rs = check.executeQuery();

            if (rs.next()) {

                PreparedStatement update =
                        connection.prepareStatement(updatePassword);

                String hashedPassword =
                        BCryptUtil.hashPassword(newPassword);

                update.setString(1, hashedPassword);
                update.setString(2, username);

                return update.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Change Master Password
    public boolean changeMasterPassword(int userId,
                                        String oldPassword,
                                        String newPassword) {

        String selectSql =
                "SELECT master_password FROM users WHERE id=?";

        String updateSql =
                "UPDATE users SET master_password=? WHERE id=?";

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement select =
                    connection.prepareStatement(selectSql);

            select.setInt(1, userId);

            ResultSet rs = select.executeQuery();

            if (rs.next()) {

                String hashedPassword =
                        rs.getString("master_password");

                if (BCryptUtil.checkPassword(oldPassword, hashedPassword)) {

                    PreparedStatement update =
                            connection.prepareStatement(updateSql);

                    String newHashed =
                            BCryptUtil.hashPassword(newPassword);

                    update.setString(1, newHashed);
                    update.setInt(2, userId);

                    return update.executeUpdate() > 0;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}