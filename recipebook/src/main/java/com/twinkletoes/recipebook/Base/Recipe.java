package com.twinkletoes.recipebook.Base;

import java.util.List;

public class Recipe {
    private int id;
    private String name;
    private String description;
    private UserAccess recipeAccessLevel;
    private List<String> ingredients;
    private List<String> steps;

    public Recipe(int id, String name, String description, UserAccess accessLevel, List<String> ingredients, List<String> steps) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.steps = steps;
        this.recipeAccessLevel = accessLevel;
    }

    public int getId() { return this.id; }
    public String getName() { return this.name; }
    public String getDescription() { return this.description; }
    public UserAccess getRecipeAccessLevel() { return this.recipeAccessLevel; }
    public List<String> getIngredients() { return this.ingredients; }
    public List<String> getSteps() { return this.steps; }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(name + "\n");
        output.append(description + "\n");
        output.append("Accessible to: " + UserAccess.toString(recipeAccessLevel) + "\n");
        output.append("Ingredients: \n");

        for (String ingredient : ingredients) {
            output.append(" - " + ingredient + "\n");
        }
        output.append("\n");

        output.append("Steps:\n");

        int count = 1;
        for (String step : steps) {
            output.append(" " + count + ". " + step + "\n");
            count++;
        }

        return output.toString();
    }
}
