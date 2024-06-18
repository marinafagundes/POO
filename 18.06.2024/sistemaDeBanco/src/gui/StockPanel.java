package gui;


import model.Stock;
import model.User;
import utils.UserUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

public class StockPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private List<User> userList;
    private User currentUser;
    private JTextField userIdField;
    private JButton verifyUserButton;
    private JLabel userVerificationMessageLabel;
    private JTable stockTable;
    private JButton addStockButton;
    private JButton buyStockButton;
    private JButton editStockButton;
    private JButton calculateGainLossButton;
    private JButton removeStockButton;
    private JButton sellStockButton;
    
    private static final String FILENAME = "users.txt";

    public StockPanel(List<User> userList) {
        this.userList = userList;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        userIdField = new JTextField(10);
        verifyUserButton = new JButton("Verify User");
        userVerificationMessageLabel = new JLabel("");

        topPanel.add(new JLabel("User ID:"));
        topPanel.add(userIdField);
        topPanel.add(verifyUserButton);
        topPanel.add(userVerificationMessageLabel);

        add(topPanel, BorderLayout.NORTH);

        stockTable = new JTable();
        add(new JScrollPane(stockTable), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        addStockButton = new JButton("Add Stock");
        buyStockButton = new JButton("Buy Stock");
        editStockButton = new JButton("Edit Stock");
        calculateGainLossButton = new JButton("Calculate Gain/Loss");
        removeStockButton = new JButton("Remove Stock");
        sellStockButton = new JButton("Sell Stock");

        bottomPanel.add(addStockButton);
        bottomPanel.add(buyStockButton);
        bottomPanel.add(editStockButton);
        bottomPanel.add(calculateGainLossButton);
        bottomPanel.add(removeStockButton);
        bottomPanel.add(sellStockButton);

        add(bottomPanel, BorderLayout.SOUTH);
        
        userIdField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    verifyUser();
                }
            }
        });

        verifyUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verifyUser();
            }
        });

        addStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStock();
            }
        });

        buyStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyStock();
            }
        });

        editStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editStock();
            }
        });

        calculateGainLossButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateGainLoss();
            }
        });

        removeStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeStock();
            }
        });

        sellStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sellStock();
            }
        });
    }

    private void verifyUser() {
        String userIdText = userIdField.getText();
        if (userIdText.isEmpty()) {
            userVerificationMessageLabel.setText("User ID cannot be empty.");
            clearStockTable();
            return;
        }

        try {
            int userId = Integer.parseInt(userIdText);
            currentUser = User.searchUserByID(userList, userId);

            if (currentUser != null) {
                userVerificationMessageLabel.setText("User verified: " + currentUser.getName());
                updateStockTable();
            } else {
                userVerificationMessageLabel.setText("User not found.");
                clearStockTable();
            }
        } catch (NumberFormatException ex) {
            userVerificationMessageLabel.setText("Invalid User ID. Please enter a numeric value.");
            clearStockTable();
        }
    }

    private void updateStockTable() {
        if (currentUser != null) {
            String[] columnNames = {"User ID", "Symbol", "Quantity", "Order Price"};
            Object[][] data = new Object[currentUser.getStockPortfolio().size()][4];

            for (int i = 0; i < currentUser.getStockPortfolio().size(); i++) {
                Stock stock = currentUser.getStockPortfolio().get(i);
                data[i][0] = currentUser.getId();
                data[i][1] = stock.getSymbol();
                data[i][2] = stock.getQuantity();
                data[i][3] = stock.getOrderPrice();
            }

            DefaultTableModel model = new DefaultTableModel(data, columnNames);
            stockTable.setModel(model);
        }
    }

    private void clearStockTable() {
        DefaultTableModel model = new DefaultTableModel();
        stockTable.setModel(model);
    }

    private void addStock() {
        if (currentUser != null) {
            String symbol = JOptionPane.showInputDialog("Enter stock symbol:");
            if (symbol != null && !symbol.trim().isEmpty()) {
                try {
                    int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity:"));
                    if (quantity <= 0) {
                        JOptionPane.showMessageDialog(this, "Quantity must be greater than 0.");
                        return;
                    }
                    float orderPrice = Float.parseFloat(JOptionPane.showInputDialog("Enter order price:"));
                    if (orderPrice <= 0) {
                        JOptionPane.showMessageDialog(this, "Order price must be greater than 0.");
                        return;
                    }
                    Stock newStock = new Stock(symbol, quantity, orderPrice, currentUser);
                    currentUser.addStock(newStock);
                    UserUtils.saveUsersToFile(userList, FILENAME); // Salvar o usuário no arquivo
                    updateStockTable();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid input. Please enter numeric values for quantity and order price.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Stock symbol cannot be empty.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "No user verified. Please verify a user first.");
        }
    }

    private void buyStock() {
        if (currentUser != null) {
            String symbol = JOptionPane.showInputDialog("Enter stock symbol:");
            if (symbol != null && !symbol.trim().isEmpty()) {
                try {
                    int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity:"));
                    if (quantity <= 0) {
                        JOptionPane.showMessageDialog(this, "Quantity must be greater than 0.");
                        return;
                    }
                    float stockPrice = currentUser.getStockPrice(symbol);
                    float totalCost = stockPrice * quantity;

                    if (currentUser.getBalance() >= totalCost) {
                        currentUser.setBalance(currentUser.getBalance() - totalCost);
                        Stock existingStock = null;
                        for (Stock stock : currentUser.getStockPortfolio()) {
                            if (stock.getSymbol().equals(symbol)) {
                                existingStock = stock;
                                break;
                            }
                        }

                        if (existingStock != null) {
                            existingStock.setQuantity(existingStock.getQuantity() + quantity);
                            existingStock.setOrderPrice((existingStock.getOrderPrice() * existingStock.getQuantity() + stockPrice * quantity) / (existingStock.getQuantity() + quantity));  // Average price
                        } else {
                            Stock newStock = new Stock(symbol, quantity, stockPrice, currentUser);
                            currentUser.addStock(newStock);
                        }
                        UserUtils.saveUsersToFile(userList, FILENAME); // Salvar o usuário no arquivo
                        updateStockTable();
                        JOptionPane.showMessageDialog(this, "Stock purchased successfully.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Insufficient balance to complete the purchase.");
                    }
                } catch (NumberFormatException | IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error purchasing stock: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Stock symbol cannot be empty.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "No user verified. Please verify a user first.");
        }
    }


    private void editStock() {
        if (currentUser != null) {
            String symbol = JOptionPane.showInputDialog("Enter stock symbol to edit:");
            if (symbol != null && !symbol.trim().isEmpty()) {
                Stock stockToEdit = null;
                for (Stock stock : currentUser.getStockPortfolio()) {
                    if (stock.getSymbol().equals(symbol)) {
                        stockToEdit = stock;
                        break;
                    }
                }

                if (stockToEdit != null) {
                    try {
                        int newQuantity = Integer.parseInt(JOptionPane.showInputDialog("Enter new quantity:"));
                        if (newQuantity <= 0) {
                            JOptionPane.showMessageDialog(this, "Quantity must be greater than 0.");
                            return;
                        }
                        float newOrderPrice = Float.parseFloat(JOptionPane.showInputDialog("Enter new order price:"));
                        if (newOrderPrice <= 0) {
                            JOptionPane.showMessageDialog(this, "Order price must be greater than 0.");
                            return;
                        }
                        stockToEdit.setQuantity(newQuantity);
                        stockToEdit.setOrderPrice(newOrderPrice);
                        UserUtils.saveUsersToFile(userList, FILENAME); // Salvar o usuário no arquivo
                        updateStockTable();
                        JOptionPane.showMessageDialog(this, "Stock edited successfully.");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Invalid input. Please enter numeric values for quantity and order price.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Stock symbol not found in the portfolio.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Stock symbol cannot be empty.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "No user verified. Please verify a user first.");
        }
    }

    private void calculateGainLoss() {
        if (currentUser != null) {
            float totalGainLoss = currentUser.calculateGainLoss();
            JOptionPane.showMessageDialog(this, "Total Gain/Loss: " + totalGainLoss);
        } else {
            JOptionPane.showMessageDialog(this, "No user verified. Please verify a user first.");
        }
    }

    private void removeStock() {
        if (currentUser != null) {
            String symbol = JOptionPane.showInputDialog("Enter stock symbol to remove:");
            if (symbol != null && !symbol.trim().isEmpty()) {
                Stock stockToRemove = null;
                for (Stock stock : currentUser.getStockPortfolio()) {
                    if (stock.getSymbol().equals(symbol)) {
                        stockToRemove = stock;
                        break;
                    }
                }

                if (stockToRemove != null) {
                    currentUser.getStockPortfolio().remove(stockToRemove);
                    UserUtils.saveUsersToFile(userList, FILENAME); // Salvar o usuário no arquivo
                    updateStockTable();
                    JOptionPane.showMessageDialog(this, "Stock removed successfully.");
                } else {
                    JOptionPane.showMessageDialog(this, "Stock symbol not found in the portfolio.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Stock symbol cannot be empty.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "No user verified. Please verify a user first.");
        }
    }

    private void sellStock() {
        if (currentUser != null) {
            String symbol = JOptionPane.showInputDialog("Enter stock symbol to sell:");
            if (symbol != null && !symbol.trim().isEmpty()) {
                try {
                    int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity to sell:"));
                    if (quantity <= 0) {
                        JOptionPane.showMessageDialog(this, "Quantity must be greater than 0.");
                        return;
                    }

                    Stock stockToSell = null;
                    for (Stock stock : currentUser.getStockPortfolio()) {
                        if (stock.getSymbol().equals(symbol)) {
                            stockToSell = stock;
                            break;
                        }
                    }

                    if (stockToSell != null) {
                        if (quantity <= stockToSell.getQuantity()) {
                            float stockPrice = currentUser.getStockPrice(symbol);
                            float totalGain = stockPrice * quantity;
                            currentUser.setBalance(currentUser.getBalance() + totalGain);
                            stockToSell.setQuantity(stockToSell.getQuantity() - quantity);

                            if (stockToSell.getQuantity() == 0) {
                                currentUser.getStockPortfolio().remove(stockToSell);
                            }
                            UserUtils.saveUsersToFile(userList, FILENAME); // Salvar o usuário no arquivo
                            updateStockTable();
                            JOptionPane.showMessageDialog(this, "Stock sold successfully.");
                        } else {
                            JOptionPane.showMessageDialog(this, "You cannot sell more than you own.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Stock symbol not found in the portfolio.");
                    }
                } catch (NumberFormatException | IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error selling stock: " + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Stock symbol cannot be empty.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "No user verified. Please verify a user first.");
        }
    }
}
