package com.twinkletoes.recipebook.Base;

public class User {
    private int id;
    private String username;
    private String password;
    private String userFullName;
    private UserAccess accessLevel;

    public User(int id, String username, String passwordHash, String userFullName, int userAccessLevel) {
        this.id = id;
        this.username = username;
        this.password = passwordHash;
        this.userFullName = userFullName;
        this.accessLevel = UserAccess.fromInt(userAccessLevel);
    }
    
    // Copy Constructor
    public User(User inUser) {
        this(inUser.getId(),
             inUser.getUsername(),
             inUser.getPassword(),
             inUser.getUserFullName(),
             UserAccess.toInt(inUser.getUserAccessLevel()));
    }

    public int getId() { return this.id; }
    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }
    public String getUserFullName() { return this.userFullName; }
    public UserAccess getUserAccessLevel() { return this.accessLevel; }

    public void setPassword(String password) { this.password = password; }
}
