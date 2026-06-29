package com.nithin.model;

public class User {

    private int id;
    private String username;
    private String email;
    private String masterPassword;

    // Default Constructor
    public User() {
    }

    // Parameterized Constructor
    public User(String username, String email, String masterPassword) {
        this.username = username;
        this.email = email;
        this.masterPassword = masterPassword;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getMasterPassword() {
        return masterPassword;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMasterPassword(String masterPassword) {
        this.masterPassword = masterPassword;
    }
}