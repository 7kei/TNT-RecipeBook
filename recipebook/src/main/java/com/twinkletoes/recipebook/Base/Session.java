package com.twinkletoes.recipebook.Base;

import java.util.ArrayList;
import java.util.List;

public class Session {
    private List<User> currentUsers;
    private List<Recipe> pastriesList;
    private List<Recipe> beveragesList;
    private User currentUser;

    public Session() {
        // Initialize users database
        this.currentUsers = Database.loadUserDatabase();
    }

    // Getters
    public User getCurrentUser() { return this.currentUser; }
    public List<Recipe> getBeverages() { return this.beveragesList; }
    public List<Recipe> getPastries() { return this.pastriesList; }

    public void loadDatabases() {
        String beverageDatabaseFileName = System.getProperty("user.dir") + "\\Databases\\Recipes\\beverages.txt";
        String pastriesDatabaseFileName = System.getProperty("user.dir") + "\\Databases\\Recipes\\pastries.txt";

        List<Recipe> tempBevList = Database.loadRecipeList(beverageDatabaseFileName);
        List<Recipe> tempPasList = Database.loadRecipeList(pastriesDatabaseFileName);

        this.beveragesList = new ArrayList<Recipe>();
        this.pastriesList = new ArrayList<Recipe>();

        for (Recipe bevRecipe : tempBevList) {
            if (UserAccess.isAccessible(bevRecipe.getRecipeAccessLevel(), this.currentUser.getUserAccessLevel()))
                this.beveragesList.add(bevRecipe);
        }

        for (Recipe pasRecipe : tempPasList) {
            if (UserAccess.isAccessible(pasRecipe.getRecipeAccessLevel(), this.currentUser.getUserAccessLevel()))
                this.pastriesList.add(pasRecipe);
        }

    }

    public int login(String username, String password) {
        for (User user : this.currentUsers) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    currentUser = user;
                    loadDatabases();
                    return 0; // Logged in
                } else {
                    return 1; // Incorrect password
                }
            }
        }
        return 2; // User not found
    }
}
