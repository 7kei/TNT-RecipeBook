package com.twinkletoes.recipebook.UI.Console;

import java.util.*;
import com.twinkletoes.recipebook.Base.*;

public class ConsoleApp {
    private Session currentSession;

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

    private void menu(Scanner scanner) {
        System.out.println("[1]. Login\n[2].Sign Up");

        char choice = scanner.nextLine().strip().toCharArray()[0];

        switch (choice) {
            case '1':
                login(scanner);
                break;

            case '2':
            default:
                System.out.println("Unimplemented");
                break;
        }
    }

    public void main() {
        System.out.println("Welcome to TNT Recipe Book!");

        Scanner scanner = new Scanner(System.in);
        menu(scanner);


        System.out.println("Working Directory = " + System.getProperty("user.dir"));
    }
}
