package com.nithin.service;

import com.nithin.dao.UserDAO;
import com.nithin.model.User;

public class AuthService {

    private UserDAO userDAO = new UserDAO();

    // Register User
    public boolean register(String username, String email, String masterPassword) {

        User user = new User(username, email, masterPassword);

        return userDAO.registerUser(user);
    }

    // Login User
    public User login(String username, String masterPassword) {

        return userDAO.loginUser(username, masterPassword);
    }
}