package com.nithin.model;

public class User {

    private int id;
    private String username;
    private String email;
    private String masterPassword;

    public User() {
    }

    public User(String username, String email, String masterPassword) {
        this.username = username;
        this.email = email;
        this.masterPassword = masterPassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMasterPassword() {
        return masterPassword;
    }

    public void setMasterPassword(String masterPassword) {
        this.masterPassword = masterPassword;
    }
}