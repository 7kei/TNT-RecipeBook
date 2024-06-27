package com.twinkletoes.recipebook.UI.Console;

import java.util.*;
import com.twinkletoes.recipebook.Base.*;

public class ConsoleApp {
    private Session currentSession;

    private void beverageMenu(Scanner scanner) {
        System.out.println("All available beverage recipes: ");
        List<Recipe> beverages = currentSession.getUserBeverages();

        int count = 0;
        for (Recipe beverageRecipe : beverages) {
            System.out.println("[" + count + "] : " + beverageRecipe.getName());
            System.out.println("    "+beverageRecipe.getDescription());
            count++;
        }

        System.out.println("\nWhat do you want to do?");
        System.out.println("[1] Display full recipe for a beverage.");
        System.out.println("[0] Back to recipe menu.");

        char choice = scanner.nextLine().strip().toCharArray()[0];

        switch (choice) {
            case '1':
                System.out.print("Type in the number of the recipe: ");
                int chosenRecipeDisplay = Integer.parseInt(scanner.nextLine());

                while (chosenRecipeDisplay >= beverages.size() || chosenRecipeDisplay < 0) {
                    System.out.println("Invalid index. Type new index.");
                    System.out.print("Type in the number of the recipe: ");
                    chosenRecipeDisplay = Integer.parseInt(scanner.nextLine());
                }

                System.out.println(beverages.get(chosenRecipeDisplay).toString());
                mainMenu(scanner);
                break;
            case '0':
            default:
                mainMenu(scanner);
                break;
        }
    }

    private void pastryMenu(Scanner scanner) {
        System.out.println("All available pastry recipes: ");
        List<Recipe> pastries = currentSession.getUserPastries();

        int count = 0;
        for (Recipe pastryRecipe : pastries) {
            System.out.println("[" + count + "] : " + pastryRecipe.getName());
            System.out.println("    "+pastryRecipe.getDescription());
            count++;
        }

        System.out.println("\nWhat do you want to do?");
        System.out.println("[1] Display full recipe for a beverage.");
        System.out.println("[0] Back to recipe menu.");

        char choice = scanner.nextLine().strip().toCharArray()[0];

        switch (choice) {
            case '1':
                System.out.print("Type in the number of the recipe: ");
                int chosenRecipeDisplay = Integer.parseInt(scanner.nextLine());

                while (chosenRecipeDisplay >= pastries.size() || chosenRecipeDisplay < 0) {
                    System.out.println("Invalid index. Type new index.");
                    System.out.print("Type in the number of the recipe: ");
                    chosenRecipeDisplay = Integer.parseInt(scanner.nextLine());
                }

                System.out.println(pastries.get(chosenRecipeDisplay).toString());
                mainMenu(scanner);
                break;
            case '0':
            default:
                mainMenu(scanner);
                break;
        }
    }

    private void changePassword(Scanner scanner) {
        System.out.println("Enter new password (, or spaces not allowed)");
        String newPassword = scanner.nextLine();
        User userBackup = new User(currentSession.getCurrentUser());
        int result = currentSession.changeUserPassword(newPassword);
        if (result == 1) {
            System.out.println("Password has invalid characters. Try again.");
            changePassword(scanner);
        } else if (result != 0) {
            System.out.println("An error occured. Reverting changes and returning to main menu.");
            currentSession.setCurrentUser(userBackup);
            mainMenu(scanner);
        }
        currentSession.saveDatabases();
        System.out.println("Password changed. Returning to main menu.");
        mainMenu(scanner);
    }

    private void removeUser(Scanner scanner) {
        System.out.println("Current users in database: ");
        for (int i = 0; i < currentSession.getAllUsers().size(); i++) {
            System.out.println("["+i+"]: "+currentSession.getAllUsers().get(i).getUsername());
        }

        System.out.print("Which user index do u want to remove?: ");
        int choice = Integer.parseInt(scanner.nextLine());

        while (choice >= currentSession.getAllUsers().size() || choice < 0) {
            System.out.println("Invalid index entered. Please enter a new index.");
            System.out.print(": ");
            choice = Integer.parseInt(scanner.nextLine());
        }

        int result = currentSession.removeUser(choice);

        if (result == 1) {
            System.out.println("Removed user cannot be the same as logged in user. Try again.");
            removeUser(scanner);
        } else if (result != 0) {
            System.out.println("An error occured. Returning to main menu.");
            mainMenu(scanner);
        }

        System.out.println("Successfully removed user. Returning to main menu.");
        mainMenu(scanner);
    }

    private void mainMenu(Scanner scanner) {
        boolean isAdmin = currentSession.getCurrentUser().getUserAccessLevel() == UserAccess.ADMIN;
        System.out.println("Welcome to the main menu, " + currentSession.getCurrentUser().getUserFullName() + "!");
        System.out.println("What do you want to do?");
        System.out.println("[1] Display beverage recipes available to the user.");
        System.out.println("[2] Display pastry recipes available to the user.");
        System.out.println("[3] Change password.");
        if (isAdmin) System.out.println("[4] ADMINS ONLY: Remove/manage users");

        System.out.println("[0] Exit the program.");

        char choice = scanner.nextLine().strip().toCharArray()[0];

        switch (choice) {
            case '1':
                beverageMenu(scanner);
                break;
            case '2':
                pastryMenu(scanner);
                break;
            case '3':
                changePassword(scanner);
                break;
            case '4':
                if (isAdmin) { removeUser(scanner); }
                else mainMenu(scanner);
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

    private void signUp(Scanner scanner) {
        Session session = new Session();
        boolean isSignedUp = false;
        while (!isSignedUp) {
            System.out.print("User name: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            System.out.print("What is the user's full name: ");
            String userFullName = scanner.nextLine();
            System.out.println("What is the user's access level?");
            System.out.print("[0] Employee, [1] Manager, [2] Admin : ");
            int accessLevel = Integer.parseInt(scanner.nextLine());

            int result = session.signUp(username, password, userFullName, accessLevel);
            if (result == 0) {
                System.out.println("Signed up!");
                currentSession = session;
                isSignedUp = true;
            } else if (result == 1) {
                System.out.println("User with same username already exists. Please try again.");
            } else {
                System.out.println("An error occured. Please try again.");
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
                signUp(scanner);
                break;
            default:
                System.out.println("Unimplemented");
            case '0':
                exitProgram(scanner);
        }
    }

    private void exitProgram(Scanner scanner) {
        if (currentSession != null) {
            System.out.println("Saving databases.");
            currentSession.saveDatabases();
        }
        scanner.close();
        System.out.println("Exiting program.");
        System.exit(0);
    }

    public void main() {
        System.out.println("Welcome to TNT Recipe Book!");
        Scanner scanner = new Scanner(System.in);
        loginMenu(scanner);
        mainMenu(scanner);
    }
}
