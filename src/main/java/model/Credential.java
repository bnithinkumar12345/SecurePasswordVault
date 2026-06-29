package com.nithin.model;

public class Credential {

    private int id;
    private int userId;
    private String website;
    private String websiteUsername;
    private String password;
    private String notes;

    public Credential() {
    }

    public Credential(int userId, String website, String websiteUsername, String password, String notes) {
        this.userId = userId;
        this.website = website;
        this.websiteUsername = websiteUsername;
        this.password = password;
        this.notes = notes;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getWebsiteUsername() {
        return websiteUsername;
    }

    public void setWebsiteUsername(String websiteUsername) {
        this.websiteUsername = websiteUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}