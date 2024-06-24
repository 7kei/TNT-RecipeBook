package com.twinkletoes.recipebook.Base;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Database {

    public static List<Recipe> loadRecipeList(String fileName) {
        List<Recipe> recipeList = new ArrayList<Recipe>();

        File recipeFile = new File(fileName);
        
        try (Scanner scanner = new Scanner(recipeFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] split = line.split(",");
                
                String recipeName = split[0];
                String recipeDescription = split[1];
                UserAccess recipeAccessLevel = UserAccess.fromInt(Integer.parseInt(split[2]));
                List<String> ingredients = new ArrayList<String>();
                List<String> steps = new ArrayList<String>();

                boolean inIngredientList = false;
                boolean inStepsList = false;
                for ( String str : Arrays.asList(split).subList(2, split.length) )
                {
                    if (str.equals("INGREDIENTS_START")) inIngredientList = true;
                    if (inIngredientList) ingredients.add(str);
                    if (str.equals("INGREDIENTS_END")) inIngredientList = false;

                    if (str.equals("STEPS_START")) inStepsList = true;
                    if (inStepsList) steps.add(str);
                    if (str.equals("STEPS_END")) inStepsList = false;
                }

                Recipe recipe = new Recipe(recipeName, recipeDescription, recipeAccessLevel, ingredients, steps);
                recipeList.add(recipe);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return recipeList;
    }

    public static List<User> loadUserDatabase() {
        File userDatabaseDirectory = new File(System.getProperty("user.dir") + "\\Databases\\users.txt");
        List<User> userList = new ArrayList<User>();

        try (Scanner scanner = new Scanner(userDatabaseDirectory)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] split = line.split(",");
                
                User u = new User(split[0], split[1], split[2], Integer.parseInt(split[3]));
                userList.add(u);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return userList;
    }
}
