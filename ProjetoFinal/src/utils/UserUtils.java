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

/**
 * Utility class for performing operations related to users.
 */
public class UserUtils {

    /**
     * Loads users from a file into a list.
     *
     * @param filename the name of the file to load users from
     * @return a list of users loaded from the file
     */
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

    /**
     * Saves a list of users to a file.
     *
     * @param userList the list of users to save
     * @param filename the name of the file to save users to
     */
    public static void saveUsersToFile(List<User> userList, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (User user : userList) {
                user.saveUser(writer);
            }
        } catch (IOException e) {
            System.out.println("Error saving users to file: " + e.getMessage());
        }
    }

    /**
     * Removes a user from the list by user ID and saves the updated list to a file.
     *
     * @param userId   the ID of the user to remove
     * @param userList the list of users
     * @param filename the name of the file to save users to after removal
     */
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

    /**
     * Prompts the user for numeric input and validates it.
     *
     * @param scanner the Scanner object to read input from
     * @param prompt  the prompt message to display
     * @return the valid numeric input entered by the user
     */
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

    /**
     * Updates the balance of a user.
     *
     * @param user      the user whose balance to update
     * @param newBalance the new balance value to set
     */
    public static void updateUserBalance(User user, float newBalance) {
        user.setBalance(newBalance);
    }
}