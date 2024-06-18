package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
    private String name;
    private int age;
    private float balance;
    private int id;
    private List<Stock> stockPortfolio;
    private static int nextId = 1;

    public User(String name, int age, float balance, int id) {
        this.name = name;
        this.age = age;
        this.balance = balance;
        this.id = id;
        this.stockPortfolio = new ArrayList<>();
    }

    public User(String name, int age, float balance) {
        this(name, age, balance, nextId++);
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public List<Stock> getStockPortfolio() {
        return stockPortfolio;
    }

    public void addStock(Stock stock) {
        stockPortfolio.add(stock);
    }

    public boolean removeStock(String stockSymbol) {
        return stockPortfolio.removeIf(stock -> stock.getSymbol().equals(stockSymbol));
    }

    public float getStockPrice(String symbol) throws IOException {
        return StockPriceRetriever.getStockPrice(symbol);
    }
    
    public void displayStockPortfolio() {
        System.out.println("Stock Portfolio for " + name + ":");
        for (Stock stock : stockPortfolio) {
            System.out.println("Symbol: " + stock.getSymbol() + ", Quantity: " + stock.getQuantity() + ", Order Price: " + stock.getOrderPrice());
        }
    }

    public void printDetails() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Balance: " + balance);
        displayStockPortfolio();
    }

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

    public static User searchUserByID(List<User> userList, int id) {
        for (User user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
    
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
                    existingStock.setOrderPrice((existingStock.getOrderPrice() + stockPrice) / 2);  // Average price
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

    public void saveUser(BufferedWriter writer) throws IOException {
        writer.write(id + "," + name + "," + age + "," + balance);
        for (Stock stock : stockPortfolio) {
            writer.write("," +  stock.getSymbol() + ":" + stock.getQuantity() + ":" + stock.getOrderPrice());
        }
        writer.write("\n");
    }

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

    public static void updateNextId(List<User> userList) {
        for (User user : userList) {
            if (user.id >= nextId) {
                nextId = user.id + 1;
            }
        }
    }
}
