package com.twinkletoes.recipebook.Base;

import java.util.ArrayList;
import java.util.List;

public class Session {
    private List<User> currentUsers;
    private List<Recipe> globalPastriesList;
    private List<Recipe> globalPastryList;
    private List<Recipe> userPastriesList;
    private List<Recipe> userPastryList;
    private User currentUser;

    public Session() {
        // Initialize users database
        this.currentUsers = Database.loadUserDatabase();
        loadGlobalDatabases();
    }

    // Getters
    public User getCurrentUser() { return this.currentUser; }
    public List<Recipe> getGlobalBeverages() { return this.globalPastryList; }
    public List<Recipe> getGlobalPastries() { return this.globalPastriesList; }
    public List<Recipe> getUserBeverages() { return this.userPastryList; }
    public List<Recipe> getUserPastries() { return this.userPastriesList; }

    public void setCurrentUser(User user) { this.currentUser = user; }

    public void loadGlobalDatabases() {
        String beverageDatabaseFileName = System.getProperty("user.dir") + "\\Databases\\Recipes\\beverages.txt";
        String pastriesDatabaseFileName = System.getProperty("user.dir") + "\\Databases\\Recipes\\pastries.txt";

        this.globalPastryList = Database.loadRecipeList(beverageDatabaseFileName);
        this.globalPastriesList = Database.loadRecipeList(pastriesDatabaseFileName);
    }

    public void loadUserDatabases() {
        this.userPastryList = new ArrayList<Recipe>();
        this.userPastriesList = new ArrayList<Recipe>();

        for (Recipe bevRecipe : globalPastryList) {
            if (UserAccess.isAccessible(bevRecipe.getRecipeAccessLevel(), this.currentUser.getUserAccessLevel()))
                this.userPastryList.add(bevRecipe);
        }

        for (Recipe pasRecipe : globalPastriesList) {
            if (UserAccess.isAccessible(pasRecipe.getRecipeAccessLevel(), this.currentUser.getUserAccessLevel()))
                this.userPastriesList.add(pasRecipe);
        }

    }

    public int login(String username, String password) {
        for (User user : this.currentUsers) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    currentUser = user;
                    loadUserDatabases();
                    return 0; // Logged in
                } else {
                    return 1; // Incorrect password
                }
            }
        }
        return 2; // User not found
    }

    public int signUp(String userName, String password, String userFullName, int accessLevel) {
        // Generate id
        int id = (int)Math.random();
        boolean duplicateId = false;
        for (User user : this.currentUsers) {
            if (user.getUsername().equals(userName)) {
                return 1; // User with same username already exists.
            }
            if (user.getId() == id) { duplicateId = true; }
        }

        while (duplicateId) {
            id = (int)Math.random();
            duplicateId = false;
            for (User user : this.currentUsers) {
                if (user.getId() == id) { duplicateId = true; }
            }
        }

        User newUser = new User(id, userName, password, userFullName, accessLevel);
        this.currentUsers.add(newUser);
        this.currentUser = newUser;
        loadUserDatabases();
        return 0; 
    }

    private int saveCurrentUserInGlobalDb() {
        // Save user in global user db
        int currentUserId = this.currentUser.getId();
        int counter = 0;
        for (User user : this.currentUsers) {
            if (user.getId() == currentUserId) {
                this.currentUsers.set(counter, this.currentUser);
                break;
            }
            counter++;
        }
        return 0;
    }

    public int changeUserPassword(String newPassword) {
        char[] newPasswordCharArray = newPassword.strip().toCharArray();

        // Check for invalid characters
        for (char c : newPasswordCharArray) {
            if (c == ' ' || c == ',') { return 1; } // Password has invalid characters
        }

        currentUser.setPassword(newPassword.strip());

        // Update global user db
        return saveCurrentUserInGlobalDb();
    }

    public void saveDatabases() {
        String beverageDatabaseFileName = System.getProperty("user.dir") + "\\Databases\\Recipes\\beverages.txt";
        String pastriesDatabaseFileName = System.getProperty("user.dir") + "\\Databases\\Recipes\\pastries.txt";

        // Update user
        saveCurrentUserInGlobalDb();

        // Save user db
        Database.saveUserDatabase(currentUsers);

        // Reflect user beverages list to global
        for (Recipe userbeverage : userPastryList) {
            for (Recipe globalBeverage : globalPastryList) {
                if (userbeverage.getId() == globalBeverage.getId()) globalBeverage = userbeverage;
            }
        }
        // Save beverages
        Database.saveRecipeList(globalPastryList, beverageDatabaseFileName);

        // Reflect user pastries list to global
        for (Recipe userPastry : userPastryList) {
            for (Recipe globalPastry : globalPastryList) {
                if (userPastry.getId() == globalPastry.getId()) globalPastry = userPastry;
            }
        }
        // Save pastries
        Database.saveRecipeList(globalPastriesList, pastriesDatabaseFileName);
    }
}
