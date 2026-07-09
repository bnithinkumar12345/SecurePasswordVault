package com.nithin.service;

import com.nithin.dao.LoginHistoryDAO;
import com.nithin.dao.UserDAO;
import com.nithin.model.User;

public class AuthService {

    private UserDAO userDAO = new UserDAO();
    private LoginHistoryDAO loginHistoryDAO = new LoginHistoryDAO();

    // Register User
    public boolean register(String username, String email, String masterPassword) {

        User user = new User(username, email, masterPassword);

        return userDAO.registerUser(user);
    }

    // Login User
    public User login(String username, String masterPassword) {

        User user = userDAO.loginUser(username, masterPassword);

        if (user != null) {

            loginHistoryDAO.saveLoginHistory(user.getId(), "SUCCESS");
        }

        return user;
    }

    // Forgot Password
    public boolean forgotPassword(String username,
                                  String email,
                                  String newPassword) {

        return userDAO.resetPassword(username, email, newPassword);
    }

    // Change Master Password
    public boolean changeMasterPassword(int userId,
                                        String oldPassword,
                                        String newPassword) {

        return userDAO.changeMasterPassword(
                userId,
                oldPassword,
                newPassword
        );
    }
}