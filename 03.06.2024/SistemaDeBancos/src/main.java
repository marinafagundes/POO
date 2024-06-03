import java.io.*;
import java.util.*;

public class main {
    public static void main(String[] args) {
        List<User> userList = UserUtils.loadUsersFromFile("banco.txt");

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\nMenu:\n");
                System.out.println("1. Insert new user.");
                System.out.println("2. Insert two or more users.");
                System.out.println("3. Search for user.");
                System.out.println("4. Transaction between users.");
                System.out.println("5. User removal by ID.");
                System.out.println("6. Add stock share to user's wallet.");
                System.out.println("7. Remove stock share from user's wallet.");
                System.out.println("8. Calculate gain/loss.");
                System.out.println("9. Buy stock for user's wallet.");
                System.out.println("10. Exit.\n");

                System.out.print("Select an option: ");
                int option = scanner.nextInt();
                scanner.nextLine();  // Consume the newline

                switch (option) {
                    case 1:
                        User user = User.insertNewUser(scanner);
                        userList.add(user);
                        user.printDetails();
                        UserUtils.saveUsersToFile(userList, "banco.txt");  // Save after inserting a user
                        break;

                    case 2:
                        int numUsers = 0;
                        while (numUsers <= 0) {
                            System.out.println("Enter the number of users to be inserted: ");
                            numUsers = scanner.nextInt();
                            scanner.nextLine();  // Consume the newline
                            if (numUsers <= 0) {
                                System.out.println("The number of users must be positive. Please enter a valid number.");
                            }
                        }
                        for (int i = 0; i < numUsers; i++) {
                            User newUser = User.insertNewUser(scanner);
                            userList.add(newUser);
                            newUser.printDetails();
                        }
                        UserUtils.saveUsersToFile(userList, "banco.txt");  // Save after inserting multiple users
                        break;

                    case 3:
                        int userId = UserUtils.getNumericInput(scanner, "Enter the user ID: ");
                        User foundUser = User.searchUserByID(userList, userId);
                        if (foundUser != null) {
                            foundUser.printDetails();
                        } else {
                            System.out.println("User not found.");
                        }
                        break;

                    case 4:
                        int sourceId = UserUtils.getNumericInput(scanner, "Enter the source user ID: ");
                        User originUser = User.searchUserByID(userList, sourceId);
                        while (originUser == null) {
                            System.out.println("Origin user not found. Please enter a valid origin user ID.");
                            sourceId = UserUtils.getNumericInput(scanner, "Enter the source user ID: ");
                            originUser = User.searchUserByID(userList, sourceId);
                        }

                        int destinationId = UserUtils.getNumericInput(scanner, "Enter the destination user ID: ");
                        User destinationUser = User.searchUserByID(userList, destinationId);
                        while (destinationUser == null) {
                            System.out.println("Destination user not found. Please enter a valid destination user ID.");
                            destinationId = UserUtils.getNumericInput(scanner, "Enter the destination user ID: ");
                            destinationUser = User.searchUserByID(userList, destinationId);
                        }

                        System.out.println("Enter the amount to be transferred:");
                        float transferAmount = scanner.nextFloat();
                        scanner.nextLine();  // Consume the newline

                        while (originUser.getBalance() < transferAmount || transferAmount <= 0) {
                            if (transferAmount <= 0) {
                                System.out.println("Invalid transfer amount. Please enter a positive amount:");
                            } else {
                                System.out.println("Insufficient balance to perform the transfer. Please enter a valid transfer amount:");
                            }
                            transferAmount = scanner.nextFloat();
                            scanner.nextLine();  // Consume the newline
                        }

                        User.performTransfer(userList, sourceId, destinationId, transferAmount, scanner);
                        UserUtils.saveUsersToFile(userList, "banco.txt");  // Save after performing a transfer
                        break;

                    case 5:
                        int removalId = UserUtils.getNumericInput(scanner, "Enter the user ID to be removed: ");
                        User userToRemove = User.searchUserByID(userList, removalId);
                        if (userToRemove != null) {
                            userList.remove(userToRemove);
                            System.out.println("User successfully removed.");
                            UserUtils.saveUsersToFile(userList, "banco.txt");  // Save after removing a user
                        } else {
                            System.out.println("User not found.");
                        }
                        break;

                    case 6:
                        userId = UserUtils.getNumericInput(scanner, "Enter the user ID: ");
                        User userForStock = User.searchUserByID(userList, userId);
                        if (userForStock != null) {
                            Stock stock = User.insertNewStock(scanner);
                            userForStock.addStock(stock);
                            UserUtils.saveUsersToFile(userList, "banco.txt");  // Save after adding a stock
                        } else {
                            System.out.println("User not found.");
                        }
                        break;

                    case 7:
                        userId = UserUtils.getNumericInput(scanner, "Enter the user ID: ");
                        userForStock = User.searchUserByID(userList, userId);
                        if (userForStock != null) {
                            System.out.println("Enter the stock symbol to be removed:");
                            String stockSymbol = scanner.nextLine();
                            boolean removed = userForStock.removeStock(stockSymbol);
                            if (removed) {
                                System.out.println("Stock successfully removed.");
                                UserUtils.saveUsersToFile(userList, "banco.txt");  // Save after removing a stock
                            } else {
                                System.out.println("Stock not found in user's portfolio.");
                            }
                        } else {
                            System.out.println("User not found.");
                        }
                        break;

                    case 8:
                        userId = UserUtils.getNumericInput(scanner, "Enter the user ID: ");
                        foundUser = User.searchUserByID(userList, userId);
                        if (foundUser != null) {
                            foundUser.calculateGainLoss();
                        } else {
                            System.out.println("User not found.");
                        }
                        break;

                    case 9:
                        userId = UserUtils.getNumericInput(scanner, "Enter the user ID: ");
                        foundUser = User.searchUserByID(userList, userId);
                        if (foundUser != null) {
                            foundUser.buyStock(scanner);
                            UserUtils.saveUsersToFile(userList, "banco.txt");  // Save after buying a stock
                        } else {
                            System.out.println("User not found.");
                        }
                        break;

                    case 10:
                        UserUtils.saveUsersToFile(userList, "banco.txt");  // Save before exiting
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid option. Please select a valid option.");
                }
            }
        }
    }
}
