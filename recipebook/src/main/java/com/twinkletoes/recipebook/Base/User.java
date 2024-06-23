package com.twinkletoes.recipebook.Base;

enum UserAccessLevel {
    EMPLOYEE,
    MANAGER,
    ADMIN
}

public class User {
    private String username;
    private String password;
    private String userFullName;
    private UserAccessLevel accessLevel;

    public User(String username, String passwordHash, String userFullName, int userAccessLevel) {
        this.username = username;
        this.password = passwordHash;
        this.userFullName = userFullName;
        this.accessLevel = UserAccessLevel.values()[userAccessLevel];
    }

    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }
    public String getUserFullName() { return this.userFullName; }
    public UserAccessLevel getUserAccessLevel() { return this.accessLevel; }
}
