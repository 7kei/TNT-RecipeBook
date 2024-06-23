package com.twinkletoes.recipebook.Base;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Database {
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
        } catch (NumberFormatException | FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return userList;
    }
}
