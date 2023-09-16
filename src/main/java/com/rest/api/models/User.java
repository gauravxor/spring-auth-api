package com.rest.api.models;

public class User {
    private final String userId;
    private final String name;
    private final String emailId;
    private final String password;

    public User(String userId, String name, String emailId, String password) {
        this.userId = userId;
        this.name = name;
        this.emailId = emailId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }
    public String getEmailId() {
        return emailId;
    }
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}