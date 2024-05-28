import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
// ainda precisa implementar a GUI
public class main {
    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
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
                        break;

                    case 2:
                        System.out.println("Enter the number of users to be inserted: ");
                        int numUsers = scanner.nextInt();
                        scanner.nextLine();  // Consume the newline
                        for (int i = 0; i < numUsers; i++) {
                            User newUser = User.insertNewUser(scanner);
                            userList.add(newUser);
                            newUser.printDetails();
                        }
                        break;

                    case 3:
                        System.out.println("Enter the user ID: ");
                        int userId = scanner.nextInt();
                        scanner.nextLine();  // Consume the newline
                        User foundUser = User.searchUserByID(userList, userId);
                        if (foundUser != null) {
                            foundUser.printDetails();
                        } else {
                            System.out.println("User not found.");
                        }
                        break;

                    case 4:
                        System.out.println("Enter the source user ID:");
                        int sourceId = scanner.nextInt();
                        System.out.println("Enter the destination user ID:");
                        int destinationId = scanner.nextInt();
                        System.out.println("Enter the amount to be transferred:");
                        float transferAmount = scanner.nextFloat();
                        scanner.nextLine();  // Consume the newline
                        User.performTransfer(userList, sourceId, destinationId, transferAmount, scanner);
                        break;

                    case 5:
                        System.out.println("Enter the user ID to be removed:");
                        int removalId = scanner.nextInt();
                        scanner.nextLine();  // Consume the newline
                        User userToRemove = User.searchUserByID(userList, removalId);
                        if (userToRemove != null) {
                            userList.remove(userToRemove);
                            System.out.println("User successfully removed.");
                        } else {
                            System.out.println("User not found.");
                        }
                        break;

                    case 6:
                        System.out.println("Enter the user ID:");
                        userId = scanner.nextInt();
                        scanner.nextLine();  // Consume the newline
                        User userForStock = User.searchUserByID(userList, userId);
                        if (userForStock != null) {
                            Stock stock = User.insertNewStock(scanner);
                            userForStock.addStock(stock);
                        } else {
                            System.out.println("User not found.");
                        }
                        break;

                    case 7:
                        System.out.println("Enter the user ID:");
                        userId = scanner.nextInt();
                        scanner.nextLine();  // Consume the newline
                        userForStock = User.searchUserByID(userList, userId);
                        if (userForStock != null) {
                            System.out.println("Enter the stock symbol to be removed:");
                            String stockSymbol = scanner.nextLine();
                            userForStock.removeStock(stockSymbol);
                        } else {
                            System.out.println("User not found.");
                        }
                        break;

                    case 8:
                        System.out.println("Enter the user ID:");
                        userId = scanner.nextInt();
                        scanner.nextLine();  // Consume the newline
                        User userForCalculation = User.searchUserByID(userList, userId);
                        if (userForCalculation != null) {
                            userForCalculation.calculateGainLoss();
                        } else {
                            System.out.println("User not found.");
                        }
                        break;

                    case 9:
                        System.out.println("Enter the user ID:");
                        userId = scanner.nextInt();
                        scanner.nextLine();  // Consume the newline
                        User userForBuyStock = User.searchUserByID(userList, userId);
                        if (userForBuyStock != null) {
                            userForBuyStock.buyStock(scanner);
                        } else {
                            System.out.println("User not found.");
                        }
                        break;

                    case 10:
                        System.out.println("Exiting the program...");
                        return;

                    default:
                        System.out.println("Invalid option. Please select a valid option.");
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
