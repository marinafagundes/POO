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

    public void printDetails() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Balance: " + balance);
        System.out.println("Stock Portfolio: ");
        for (Stock stock : stockPortfolio) {
            System.out.println("  - Symbol: " + stock.getSymbol() + ", Quantity: " + stock.getQuantity() + ", Order Price: " + stock.getOrderPrice());
        }
    }

    public static User insertNewUser(Scanner scanner) {
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        System.out.println("Enter age:");
        int age = scanner.nextInt();
        System.out.println("Enter balance:");
        float balance = scanner.nextFloat();
        scanner.nextLine();  // Consume the newline
        int id = nextId++;
        return new User(name, age, balance, id);
    }

    public static User searchUserByID(List<User> userList, int id) {
        for (User user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public static Stock insertNewStock(Scanner scanner) {
        System.out.println("Enter stock symbol:");
        String symbol = scanner.nextLine();
        System.out.println("Enter quantity:");
        int quantity = scanner.nextInt();
        System.out.println("Enter order price:");
        float orderPrice = scanner.nextFloat();
        scanner.nextLine();  // Consume the newline
        return new Stock(symbol, quantity, orderPrice);
    }

    public void calculateGainLoss() {
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
    }

    public void updatePortfolio() {
        for (Stock stock : stockPortfolio) {
            try {
                float currentPrice = getStockPrice(stock.getSymbol());
                stock.setOrderPrice(currentPrice);
            } catch (IOException e) {
                System.out.println("Error updating the price for stock " + stock.getSymbol() + ": " + e.getMessage());
            }
        }
        System.out.println("Portfolio updated successfully.");
    }

    public void buyStock(Scanner scanner) {
        System.out.println("Enter the stock symbol:");
        String symbol = scanner.nextLine();
        System.out.println("Enter the number of shares:");
        int quantity = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        try {
            float stockPrice = getStockPrice(symbol);
            float totalCost = stockPrice * quantity;

            if (balance >= totalCost) {
                balance -= totalCost;
                Stock newStock = new Stock(symbol, quantity, stockPrice);
                this.addStock(newStock);
                System.out.println("Stock purchased successfully.");
            } else {
                System.out.println("Insufficient balance to complete the purchase.");
            }
        } catch (IOException e) {
            System.out.println("Error purchasing stock: " + e.getMessage());
        }
    }

    public static void performTransfer(List<User> userList, int originId, int destinationId, float transferAmount) {
        User originUser = searchUserByID(userList, originId);
        User destinationUser = searchUserByID(userList, destinationId);

        originUser.setBalance(originUser.getBalance() - transferAmount);
        destinationUser.setBalance(destinationUser.getBalance() + transferAmount);
        System.out.println("Transfer successful.");
    }

    public void saveUser(BufferedWriter writer) throws IOException {
        writer.write(id + ";" + name + ";" + age + ";" + balance);
        writer.newLine();
        for (Stock stock : stockPortfolio) {
            writer.write("STOCK;" + stock.getSymbol() + ";" + stock.getQuantity() + ";" + stock.getOrderPrice());
            writer.newLine();
        }
    }

    public static User loadUser(BufferedReader reader) throws IOException {
        String userLine = reader.readLine();
        if (userLine == null) {
            return null;
        }

        String[] userData = userLine.split(";");
        int id = Integer.parseInt(userData[0]);
        String name = userData[1];
        int age = Integer.parseInt(userData[2]);
        float balance = Float.parseFloat(userData[3]);

        User user = new User(name, age, balance, id);

        while (reader.ready()) {
            reader.mark(1000); // Mark the current position
            String line = reader.readLine();
            if (line.startsWith("STOCK;")) {
                String[] stockData = line.split(";");
                String symbol = stockData[1];
                int quantity = Integer.parseInt(stockData[2]);
                float orderPrice = Float.parseFloat(stockData[3]);
                Stock stock = new Stock(symbol, quantity, orderPrice);
                user.addStock(stock);
            } else {
                reader.reset(); // Reset to the marked position if the line is not a stock
                break;
            }
        }
        return user;
    }

    public static void updateNextId(List<User> userList) {
        for (User user : userList) {
            if (user.getId() >= nextId) {
                nextId = user.getId() + 1;
            }
        }
    }
}
