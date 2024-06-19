package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a User with a name, age, balance, ID, and a portfolio of stocks.
 */
public class User {
    private String name;
    private int age;
    private float balance;
    private int id;
    private List<Stock> stockPortfolio;
    private static int nextId = 1;

    /**
     * Constructs a User object with specified name, age, balance, and ID.
     *
     * @param name    the name of the user
     * @param age     the age of the user
     * @param balance the balance of the user
     * @param id      the unique ID of the user
     */
    public User(String name, int age, float balance, int id) {
        this.name = name;
        this.age = age;
        this.balance = balance;
        this.id = id;
        this.stockPortfolio = new ArrayList<>();
    }


    /**
     * Constructs a User object with specified name, age, and balance.
     * Automatically assigns the next available ID.
     *
     * @param name    the name of the user
     * @param age     the age of the user
     * @param balance the balance of the user
     */
    public User(String name, int age, float balance) {
        this(name, age, balance, nextId++);
    }

    /**
     * Retrieves the name of the user.
     *
     * @return the name of the user
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the name of the user.
     *
     * @param name the new name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the age of the user.
     *
     * @return the age of the user
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the user.
     *
     * @param age the new age of the user
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Retrieves the balance of the user.
     *
     * @return the balance of the user
     */
    public float getBalance() {
        return balance;
    }

    /**
     * Sets the balance of the user.
     *
     * @param balance the new balance of the user
     */
    public void setBalance(float balance) {
        this.balance = balance;
    }

    /**
     * Retrieves the ID of the user.
     *
     * @return the ID of the user
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the stock portfolio of the user.
     *
     * @return the list of stocks in the user's portfolio
     */
    public List<Stock> getStockPortfolio() {
        return stockPortfolio;
    }

    /**
     * Adds a stock to the user's portfolio.
     *
     * @param stock the stock to add
     */
    public void addStock(Stock stock) {
        stockPortfolio.add(stock);
    }

    /**
     * Removes a stock from the user's portfolio by symbol.
     *
     * @param stockSymbol the symbol of the stock to remove
     * @return true if the stock was removed successfully, false otherwise
     */
    public boolean removeStock(String stockSymbol) {
        return stockPortfolio.removeIf(stock -> stock.getSymbol().equals(stockSymbol));
    }

    /**
     * Retrieves the current price of a stock from an external API.
     *
     * @param symbol the symbol of the stock
     * @return the current price of the stock
     * @throws IOException   if there's an error in making the HTTP request
     */
    public float getStockPrice(String symbol) throws IOException {
        return StockPriceRetriever.getStockPrice(symbol);
    }
    
    /**
     * Displays the user's stock portfolio with details.
     */
    public void displayStockPortfolio() {
        System.out.println("Stock Portfolio for " + name + ":");
        for (Stock stock : stockPortfolio) {
            System.out.println("Symbol: " + stock.getSymbol() + ", Quantity: " + stock.getQuantity() + ", Order Price: " + stock.getOrderPrice());
        }
    }
    
    /**
     * Prints details of the user including ID, name, age, balance, and stock portfolio.
     */
    public void printDetails() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Balance: " + balance);
        displayStockPortfolio();
    }

    /**
     * Inserts a new user based on user input.
     *
     * @param scanner the Scanner object for user input
     * @return a new User object created based on user input
     */
    public static User insertNewUser(Scanner scanner) {
        String name;
        do {
            System.out.println("Enter the user's name (maximum 100 characters, no numeric characters): ");
            name = scanner.nextLine();
            if (name.isEmpty() || name.length() > 100 || name.matches(".*\\d.*")) {
                System.out.println("Error! Invalid name. The name must be between 1 and 100 characters and contain no numeric characters.");
            }
        } while (name.isEmpty() || name.length() > 100 || name.matches(".*\\d.*"));

        int age;
        do {
            System.out.println("Enter the user's age: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Error! Invalid age. Enter the age again: ");
                scanner.next();
            }
            age = scanner.nextInt();
            if (age < 0 || age > 200) {
                System.out.println("Error! Invalid age. The age must be between 0 and 200 years.");
            }
        } while (age < 0 || age > 200);
        scanner.nextLine();

        float balance;
        do {
            System.out.println("Enter the user's balance: ");
            while (!scanner.hasNextFloat()) {
                System.out.println("Error! Invalid balance. Enter the balance again: ");
                scanner.next();
            }
            balance = scanner.nextFloat();
            if (balance < 0) {
                System.out.println("Error! Invalid balance. The balance cannot be negative.");
            }
        } while (balance < 0);
        scanner.nextLine();

        return new User(name, age, balance);
    }

    /**
     * Searches for a user in a list based on ID.
     *
     * @param userList the list of users to search
     * @param id       the ID of the user to search for
     * @return the User object if found, or null if not found
     */
    public static User searchUserByID(List<User> userList, int id) {
        for (User user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
    
    /**
     * Inserts a new stock based on user input for the owner.
     *
     * @param scanner the Scanner object for user input
     * @param owner   the owner of the stock
     * @return a new Stock object created based on user input
     */
    public static Stock insertNewStock(Scanner scanner, User owner) {
        System.out.println("Enter stock symbol:");
        String symbol = scanner.nextLine();
        System.out.println("Enter quantity:");
        int quantity = scanner.nextInt();
        System.out.println("Enter order price:");
        float orderPrice = scanner.nextFloat();
        scanner.nextLine();  // Consume the newline
        return new Stock(symbol, quantity, orderPrice, owner);
    }

    /**
     * Calculates the total gain or loss for the user's stock portfolio.
     *
     * @return the total gain or loss in the portfolio
     */
    public float calculateGainLoss() {
        float totalGain = 0;
        for (Stock stock : stockPortfolio) {
            try {
                float currentPrice = getStockPrice(stock.getSymbol());
                float purchasePrice = stock.getOrderPrice();
                int quantity = stock.getQuantity();
                float gainLoss = (currentPrice - purchasePrice) * quantity;
                totalGain += gainLoss;
                System.out.println("Gain/Loss for stock " + stock.getSymbol() + ": " + gainLoss);
            } catch (IOException e) {
                System.out.println("Error getting the price for stock " + stock.getSymbol() + ": " + e.getMessage());
            }
        }
        System.out.println("Total gain/loss in portfolio: " + totalGain);
        return totalGain;
    }

    /**
     * Allows the user to buy a stock based on user input.
     *
     * @param scanner the Scanner object for user input
     * @return true if the stock was purchased successfully, false otherwise
     */
    public boolean buyStock(Scanner scanner) {
        System.out.println("Enter the stock symbol:");
        String symbol = scanner.nextLine();
        
        // Check if stock symbol is empty
        if (symbol == null || symbol.trim().isEmpty()) {
            System.out.println("Stock symbol cannot be empty.");
            return false;
        }

        System.out.println("Enter the number of shares:");
        int quantity;
        try {
            quantity = Integer.parseInt(scanner.nextLine());
            // Check if quantity is greater than zero
            if (quantity <= 0) {
                System.out.println("Quantity must be greater than zero.");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a numeric value for quantity.");
            return false;
        }

        try {
            float stockPrice = getStockPrice(symbol);
            // Check if stock price is greater than zero
            if (stockPrice <= 0) {
                System.out.println("Stock price must be greater than zero.");
                return false;
            }

            float totalCost = stockPrice * quantity;

            if (balance >= totalCost) {
                balance -= totalCost;
                Stock existingStock = null;
                for (Stock stock : stockPortfolio) {
                    if (stock.getSymbol().equals(symbol)) {
                        existingStock = stock;
                        break;
                    }
                }

                if (existingStock != null) {
                    existingStock.setQuantity(existingStock.getQuantity() + quantity);
                    existingStock.setOrderPrice((existingStock.getOrderPrice() + stockPrice) / 2);
                } else {
                    Stock newStock = new Stock(symbol, quantity, stockPrice, this);
                    addStock(newStock);
                }
                System.out.println("Stock purchased successfully.");
                return true;
            } else {
                System.out.println("Insufficient balance to complete the purchase.");
                return false;
            }
        } catch (IOException e) {
            System.out.println("Error purchasing stock: " + e.getMessage());
            return false;
        }
    }

    /**
     * Allows the user to sell a stock based on user input.
     *
     * @param scanner the Scanner object for user input
     * @return true if the stock was sold successfully, false otherwise
     */
    public boolean sellStock(Scanner scanner) {
        System.out.println("Enter the stock symbol:");
        String symbol = scanner.nextLine();
        Stock stockToSell = null;

        for (Stock stock : stockPortfolio) {
            if (stock.getSymbol().equals(symbol)) {
                stockToSell = stock;
                break;
            }
        }

        if (stockToSell == null) {
            System.out.println("Stock not found in portfolio.");
            return false;
        }

        System.out.println("Enter the number of shares to sell:");
        int quantity = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        if (quantity > stockToSell.getQuantity()) {
            System.out.println("Insufficient shares to sell.");
            return false;
        }

        try {
            float currentPrice = getStockPrice(symbol);
            float totalRevenue = currentPrice * quantity;

            stockToSell.setQuantity(stockToSell.getQuantity() - quantity);
            balance += totalRevenue;
            if (stockToSell.getQuantity() == 0) {
                stockPortfolio.remove(stockToSell);
            }
            System.out.println("Stock sold successfully.");
        } catch (IOException e) {
            System.out.println("Error selling stock: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Saves the user's details and stock portfolio to a file.
     *
     * @param writer the BufferedWriter object for writing to a file
     * @throws IOException if there's an error in writing to the file
     */
    public void saveUser(BufferedWriter writer) throws IOException {
        writer.write(id + "," + name + "," + age + "," + balance);
        for (Stock stock : stockPortfolio) {
            writer.write("," +  stock.getSymbol() + ":" + stock.getQuantity() + ":" + stock.getOrderPrice());
        }
        writer.write("\n");
    }

    /**
     * Loads user details and stock portfolio from a file.
     *
     * @param reader   the BufferedReader object for reading from a file
     * @param userList the list of users to add the loaded user to
     * @return the loaded User object
     * @throws IOException if there's an error in reading from the file
     */
    public static User loadUser(BufferedReader reader, List<User> userList) throws IOException {
        String line = reader.readLine();
        if (line == null || line.isEmpty()) {
            return null;
        }
        String[] parts = line.split(",");
        if (parts.length < 4) {
            throw new IOException("Invalid data format");
        }
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        int age = Integer.parseInt(parts[2]);
        float balance = Float.parseFloat(parts[3]);
        
        User user = new User(name, age, balance, id);
        if (parts.length > 4) {
            for (int i = 4; i < parts.length; i++) {
                String[] stockParts = parts[i].split(":");
                if (stockParts.length == 3) {
                    String symbol = stockParts[0];
                    int quantity = Integer.parseInt(stockParts[1]);
                    float orderPrice = Float.parseFloat(stockParts[2]);
                    Stock stock = new Stock(symbol, quantity, orderPrice, user);
                    user.addStock(stock);
                } else {
                    throw new IOException("Invalid stock data format");
                }
            }
        }
        return user;
    }

    /**
     * Updates the next available ID based on existing users in the list.
     *
     * @param userList the list of users to update from
     */
    public static void updateNextId(List<User> userList) {
        for (User user : userList) {
            if (user.id >= nextId) {
                nextId = user.id + 1;
            }
        }
    }
}
