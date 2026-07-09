package com.nithin.model;

import java.sql.Timestamp;

public class LoginHistory {

    private int id;
    private int userId;
    private Timestamp loginTime;
    private String status;

    public LoginHistory() {
    }

    public LoginHistory(int userId, Timestamp loginTime, String status) {
        this.userId = userId;
        this.loginTime = loginTime;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Timestamp loginTime) {
        this.loginTime = loginTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}