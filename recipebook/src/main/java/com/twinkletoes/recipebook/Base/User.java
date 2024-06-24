package com.twinkletoes.recipebook.Base;

public class User {
    private String username;
    private String password;
    private String userFullName;
    private UserAccess accessLevel;

    public User(String username, String passwordHash, String userFullName, int userAccessLevel) {
        this.username = username;
        this.password = passwordHash;
        this.userFullName = userFullName;
        this.accessLevel = UserAccess.fromInt(userAccessLevel);
    }

    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }
    public String getUserFullName() { return this.userFullName; }
    public UserAccess getUserAccessLevel() { return this.accessLevel; }
}
