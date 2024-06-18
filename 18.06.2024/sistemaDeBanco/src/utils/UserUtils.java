package utils;

import model.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserUtils {

	public static List<User> loadUsersFromFile(String filename) {
        List<User> userList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            User user;
            while ((user = User.loadUser(reader, userList)) != null) {
                userList.add(user);
            }
        } catch (IOException e) {
            System.out.println("Error loading users from file: " + e.getMessage());
        }
        User.updateNextId(userList);
        return userList;
    }

	public static void saveUsersToFile(List<User> userList, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (User user : userList) {
                user.saveUser(writer);
            }
        } catch (IOException e) {
            System.out.println("Error saving users to file: " + e.getMessage());
        }
    }

	public static void removeUser(int userId, List<User> userList, String filename) {
        User userToRemove = null;
        for (User user : userList) {
            if (user.getId() == userId) {
                userToRemove = user;
                break;
            }
        }
        if (userToRemove != null) {
            userList.remove(userToRemove);
            saveUsersToFile(userList, filename);
        }
    }

    public static int getNumericInput(Scanner scanner, String prompt) {
        int input;
        while (true) {
            System.out.println(prompt);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                scanner.nextLine();  // Consume the newline
                break;
            } else {
                System.out.println("Invalid input. Please enter a numeric value.");
                scanner.nextLine();  // Consume the invalid input
            }
        }
        return input;
    }
}
