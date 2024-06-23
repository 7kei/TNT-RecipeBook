package com.twinkletoes.recipebook.Base;

import java.util.List;

public class Session {
    private User currentUser;

    public int login(String username, String password) {
        List<User> users = Database.loadUserDatabase();
        
        for (User u : users) {
            if (u.getUsername().equals(username)) {
                System.out.println("Found user: "+ u.getUsername());

                if (u.getPassword().equals(password)) {
                    currentUser = u;
                    System.out.println("Logged in!");
                    return 0;
                } else {
                    System.out.println("Incorrect password.");
                    return 1;
                }
            }
        }
        System.out.println("Could not find user.");
        return 2;
    }
}
