package com.twinkletoes.recipebook.UI.Console;

import java.util.*;
import com.twinkletoes.recipebook.Base.*;

public class ConsoleApp {
    private Session currentSession;

    private void beverageMenu(Scanner scanner) {
        System.out.println("All available beverage recipes: ");
        List<Recipe> beverages = currentSession.getBeverages();

        int count = 0;
        for (Recipe beverageRecipe : beverages) {
            System.out.println("[" + count + "] : " + beverageRecipe.getName());
            System.out.println("    "+beverageRecipe.getDescription());
            count++;
        }

        System.out.println("\nWhat do you want to do?");
        System.out.println("[1] Display full recipe for a beverage.");
        System.out.println("[2] Back to recipe menu.");

        char choice = scanner.nextLine().strip().toCharArray()[0];

        switch (choice) {
            case '1':
                System.out.print("Type in the number of the recipe: ");
                int chosenRecipe = Integer.parseInt(scanner.nextLine());
                System.out.println(beverages.get(chosenRecipe).toString());
                break;
            case '2':
            default:
                recipeMenu(scanner);
                break;
        }
    }

    private void pastryMenu(Scanner scanner) {
        System.out.println("All available pastry recipes: ");
        List<Recipe> pastries = currentSession.getPastries();

        int count = 0;
        for (Recipe pastryRecipe : pastries) {
            System.out.println("[" + count + "] : " + pastryRecipe.getName());
            System.out.println("    "+pastryRecipe.getDescription());
            count++;
        }

        System.out.println("\nWhat do you want to do?");
        System.out.println("[1] Display full recipe for a beverage.");
        System.out.println("[2] Back to recipe menu.");

        char choice = scanner.nextLine().strip().toCharArray()[0];

        switch (choice) {
            case '1':
                System.out.print("Type in the number of the recipe: ");
                int chosenRecipe = Integer.parseInt(scanner.nextLine());
                System.out.println(pastries.get(chosenRecipe).toString());
                break;
            case '2':
            default:
                recipeMenu(scanner);
                break;
        }
    }

    private void recipeMenu(Scanner scanner) {
        System.out.println("Welcome to the recipe menu, " + currentSession.getCurrentUser().getUserFullName() + "!");
        System.out.println("What do you want to do?");
        System.out.println("[1] Display beverage recipes available to the user.");
        System.out.println("[2] Display pastry recipes available to the user.");
        System.out.println("[0] Exit the program.");

        char choice = scanner.nextLine().strip().toCharArray()[0];

        switch (choice) {
            case '1':
                beverageMenu(scanner);
                break;
            case '2':
                pastryMenu(scanner);
                break;
            default:
                System.out.println("Unimplemented");
            case '0':
                exitProgram(scanner);
                break;
        }
    }

    private void login(Scanner scanner) {
        Session session = new Session();
        boolean isLoggedIn = false;
        while (!isLoggedIn) {
            System.out.print("User name: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            int result = session.login(username, password);
            if (result == 0) {
                System.out.println("Logged in!");
                currentSession = session;
                isLoggedIn = true;
            } else {
                System.out.println("Nonexistent or incorrect user credentials. Try again.");
            }
        }
    }

    private void loginMenu(Scanner scanner) {
        System.out.println("[1] Login\n[2] Sign Up\n[0] Exit Program");

        char choice = scanner.nextLine().strip().toCharArray()[0];

        switch (choice) {
            case '1':
                login(scanner);
                break;
            case '2':
            default:
                System.out.println("Unimplemented");
            case '0':
                exitProgram(scanner);
        }
    }

    private void exitProgram(Scanner scanner) {
        System.out.println("Exiting program.");
        System.exit(0);
    }

    public void main() {
        System.out.println("Welcome to TNT Recipe Book!");
        Scanner scanner = new Scanner(System.in);
        loginMenu(scanner);
        recipeMenu(scanner);
    }
}
