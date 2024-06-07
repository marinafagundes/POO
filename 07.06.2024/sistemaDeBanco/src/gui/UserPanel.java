package gui;

import model.User;
import model.StockPriceRetriever;
import utils.UserUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class UserPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTable userTable;
    private DefaultTableModel tableModel;
    private List<User> userList;
    private final String filename = "users.txt";

    public UserPanel() {
        setLayout(new BorderLayout());

        userList = UserUtils.loadUsersFromFile(filename);
        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Age", "Balance"}, 0);
        userTable = new JTable(tableModel);

        // Load existing users into the table
        for (User user : userList) {
            tableModel.addRow(new Object[]{user.getId(), user.getName(), user.getAge(), user.getBalance()});
        }

        JScrollPane scrollPane = new JScrollPane(userTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add User");
        JButton removeButton = new JButton("Remove User");
        JButton transferButton = new JButton("Transfer Funds");
        JButton stockButton = new JButton("Get Stock Price");
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(transferButton);
        buttonPanel.add(stockButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name;
                do {
                    name = JOptionPane.showInputDialog(UserPanel.this, "Enter the user's name (maximum 100 characters, no numeric characters):");
                    if (name == null) return; // Cancel pressed
                    if (name.isEmpty() || name.length() > 100 || name.matches(".*\\d.*")) {
                        JOptionPane.showMessageDialog(UserPanel.this, "Error! Invalid name. The name must be between 1 and 100 characters and contain no numeric characters.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } while (name.isEmpty() || name.length() > 100 || name.matches(".*\\d.*"));

                int age;
                do {
                    String ageStr = JOptionPane.showInputDialog(UserPanel.this, "Enter the user's age:");
                    if (ageStr == null) return; // Cancel pressed
                    try {
                        age = Integer.parseInt(ageStr);
                        if (age < 0 || age > 200) {
                            JOptionPane.showMessageDialog(UserPanel.this, "Error! Invalid age. The age must be between 0 and 200 years.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        age = -1; // Invalid age
                        JOptionPane.showMessageDialog(UserPanel.this, "Error! Invalid age format.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } while (age < 0 || age > 200);

                float balance;
                do {
                    String balanceStr = JOptionPane.showInputDialog(UserPanel.this, "Enter the user's balance:");
                    if (balanceStr == null) return; // Cancel pressed
                    try {
                        balance = Float.parseFloat(balanceStr);
                        if (balance < 0) {
                            JOptionPane.showMessageDialog(UserPanel.this, "Error! Invalid balance. The balance cannot be negative.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        balance = -1; // Invalid balance
                        JOptionPane.showMessageDialog(UserPanel.this, "Error! Invalid balance format.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } while (balance < 0);

                User user = new User(name, age, balance);
                userList.add(user);
                tableModel.addRow(new Object[]{user.getId(), user.getName(), user.getAge(), user.getBalance()});
                UserUtils.saveUsersToFile(userList, filename);
            }
        });


        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = userTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(UserPanel.this, "Select a user to remove.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int userId = (int) tableModel.getValueAt(selectedRow, 0);
                tableModel.removeRow(selectedRow);
                UserUtils.removeUser(userId, userList, filename);
            }
        });
        
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Solicitar ID do remetente
                String senderIdStr = JOptionPane.showInputDialog(UserPanel.this, "Enter the sender's ID:");
                if (senderIdStr == null || senderIdStr.isEmpty()) {
                    JOptionPane.showMessageDialog(UserPanel.this, "Sender ID is required.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int senderId;
                try {
                    senderId = Integer.parseInt(senderIdStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(UserPanel.this, "Invalid sender ID format.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                User sender = User.searchUserByID(userList, senderId);
                if (sender == null) {
                    JOptionPane.showMessageDialog(UserPanel.this, "Sender not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Solicitar ID do destinatário
                String recipientIdStr = JOptionPane.showInputDialog(UserPanel.this, "Enter the recipient's ID:");
                if (recipientIdStr == null || recipientIdStr.isEmpty()) {
                    JOptionPane.showMessageDialog(UserPanel.this, "Recipient ID is required.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int recipientId;
                try {
                    recipientId = Integer.parseInt(recipientIdStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(UserPanel.this, "Invalid recipient ID format.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                User recipient = User.searchUserByID(userList, recipientId);
                if (recipient == null) {
                    JOptionPane.showMessageDialog(UserPanel.this, "Recipient not found.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Verificar se remetente e destinatário são o mesmo usuário
                if (senderId == recipientId) {
                    JOptionPane.showMessageDialog(UserPanel.this, "Sender and recipient cannot be the same user.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Solicitar quantidade
                String quantityStr = JOptionPane.showInputDialog(UserPanel.this, "Enter the quantity to transfer:");
                if (quantityStr == null || quantityStr.isEmpty()) {
                    JOptionPane.showMessageDialog(UserPanel.this, "Quantity is required.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                float quantity;
                try {
                    quantity = Float.parseFloat(quantityStr);
                    if (quantity <= 0) {
                        JOptionPane.showMessageDialog(UserPanel.this, "The quantity must be positive.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(UserPanel.this, "Invalid quantity format.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Verificar saldo insuficiente
                if (sender.getBalance() < quantity) {
                    JOptionPane.showMessageDialog(UserPanel.this, "Insufficient balance.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Realizar a transferência
                sender.setBalance(sender.getBalance() - quantity);
                recipient.setBalance(recipient.getBalance() + quantity);
                UserUtils.saveUsersToFile(userList, filename);

                // Atualizar o modelo da tabela
                tableModel.setValueAt(sender.getBalance(), findUserRowById(senderId), 3);
                tableModel.setValueAt(recipient.getBalance(), findUserRowById(recipientId), 3);

                JOptionPane.showMessageDialog(UserPanel.this, "Transfer successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });



        stockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String symbol = JOptionPane.showInputDialog(UserPanel.this, "Enter the stock symbol:");
                if (symbol == null || symbol.isEmpty()) {
                    JOptionPane.showMessageDialog(UserPanel.this, "Stock symbol is required.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    float stockPrice = StockPriceRetriever.getStockPrice(symbol);
                    JOptionPane.showMessageDialog(UserPanel.this, "The stock price for " + symbol + " is $" + stockPrice, "Stock Price", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(UserPanel.this, "Error retrieving stock price.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private int findUserRowById(int userId) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if ((int) tableModel.getValueAt(i, 0) == userId) {
                return i;
            }
        }
        return -1;
    }
}
