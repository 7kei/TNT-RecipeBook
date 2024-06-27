package com.twinkletoes.recipebook.Base;

import java.io.*;
import java.util.*;

public class Database {

    public static List<Recipe> loadRecipeList(File file) {
        List<Recipe> recipeList = new ArrayList<Recipe>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.startsWith("MODEL: "))
                    continue;

                String[] split = line.split(",");
                
                int id = Integer.parseInt(split[0]);
                String recipeName = split[1];
                String recipeDescription = split[2];
                UserAccess recipeAccessLevel = UserAccess.fromInt(Integer.parseInt(split[3]));
                List<String> ingredients = new ArrayList<String>();
                List<String> steps = new ArrayList<String>();

                boolean inIngredientList = false;
                boolean inStepsList = false;
                for (String str : Arrays.asList(split).subList(2, split.length)) {
                    if (str.equals("INGREDIENTS_START")) { inIngredientList = true; continue; }
                    if (str.equals("INGREDIENTS_END")) { inIngredientList = false; continue; }
                    if (str.equals("STEPS_START")) { inStepsList = true; continue; }
                    if (str.equals("STEPS_END")) { inStepsList = false; continue;}

                    if (inStepsList) steps.add(str);
                    if (inIngredientList) ingredients.add(str);
                }

                Recipe recipe = new Recipe(id, recipeName, recipeDescription, recipeAccessLevel, ingredients, steps);
                recipeList.add(recipe);
            }
            scanner.close();
        } catch (NumberFormatException | FileNotFoundException e) {
            e.printStackTrace();
        }

        return recipeList;
    }

    public static List<Recipe> loadRecipeList(String fileName) {
        File file = new File(fileName);
        return loadRecipeList(file);
    }

    public static int saveRecipeList(List<Recipe> recipeList, File file) {
        try {
            FileWriter out = new FileWriter(file);

            for (Recipe recipe : recipeList) {
                out.write(recipe.getId()+","
                         +recipe.getName()+","
                         +recipe.getDescription()+","
                         +UserAccess.toInt(recipe.getRecipeAccessLevel())
                         +",");
                
                out.write("INGREDIENTS_START,");
                for (String ingredient : recipe.getIngredients()) {
                    out.write(ingredient + ",");
                }
                out.write("INGREDIENTS_END,");
                out.write("STEPS_START,");
                for (String step : recipe.getSteps()) {
                    out.write(step + ",");
                }
                out.write("STEPS_END\n");
            }

            out.flush();
            out.close();

            return 0;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return 1;
    }

    public static int saveRecipeList(List<Recipe> recipeList, String fileName) {
        try {
            File file = new File(fileName);
            file.createNewFile();
            return saveRecipeList(recipeList, file);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        return -1;
    }

    public static List<User> loadUserDatabase() {
        File userDatabaseDirectory = new File(System.getProperty("user.dir") + "\\Databases\\users.txt");
        List<User> userList = new ArrayList<User>();

        try (Scanner scanner = new Scanner(userDatabaseDirectory)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.strip().startsWith("MODEL:")) continue;

                String[] split = line.split(",");

                if (split.length != 5) {
                    System.out.println("Malformed entry. Skipping");
                    continue;
                }

                User u = new User(Integer.parseInt(split[0]), split[1], split[2], split[3], Integer.parseInt(split[4]));
                userList.add(u);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public static int saveUserDatabase(List<User> userList) {
        try {
            File userDatabaseDirectory = new File(System.getProperty("user.dir") + "\\Databases\\users.txt");
            userDatabaseDirectory.createNewFile();
            FileWriter out = new FileWriter(userDatabaseDirectory);
            for (User user : userList) {
                out.write(user.getId()+","
                         +user.getUsername()+","
                         +user.getPassword()+","
                         +user.getUserFullName()+","
                         +UserAccess.toInt(user.getUserAccessLevel())+"\n");
            }
            out.flush();
            out.close();

            return 0;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return 1;
    }
}
