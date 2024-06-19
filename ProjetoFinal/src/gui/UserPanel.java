package gui;

import model.Stock;
import model.User;
import utils.UserUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Panel containing a table of users and various user management functionalities.
 */
public class UserPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTable userTable;
    private DefaultTableModel tableModel;
    private final String filename = "users.txt";
    private List<User> userList;

    /**
     * Constructs a UserPanel with a list of users to display and manage.
     *
     * @param userList The list of users to display and manage
     */
    public UserPanel(List<User> userList) {
        this.userList = userList;
        setLayout(new BorderLayout());
        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Age", "Balance"}, 0);
        userTable = new JTable(tableModel);

        loadUsersIntoTable();

        JScrollPane scrollPane = new JScrollPane(userTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add User");
        JButton removeButton = new JButton("Remove User");
        JButton transferButton = new JButton("Transfer Funds");
        JButton searchButton = new JButton("Search User by ID");
        JButton editButton = new JButton("Edit User");
        
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(transferButton);

        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUser();
            }
        });
        
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editUser();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeUser();
            }
        });
        
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchUserById();
            }
        });

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transferFunds();
            }
        });
    }

    /**
     * Loads the list of users into the table for display.
     */
    private void loadUsersIntoTable() {
        // Clear the table before loading users
        tableModel.setRowCount(0);

        for (User user : userList) {
            tableModel.addRow(new Object[]{user.getId(), user.getName(), user.getAge(), user.getBalance()});
        }
    }

    /**
     * Adds a new user to the list and updates the table display.
     */
    private void addUser() {
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

    /**
     * Edits an existing user's details and updates the table display.
     */
    private void editUser() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(UserPanel.this, "Select a user to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int userId = (int) tableModel.getValueAt(selectedRow, 0);
        User user = User.searchUserByID(userList, userId);
        if (user == null) {
            JOptionPane.showMessageDialog(UserPanel.this, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String newName = JOptionPane.showInputDialog(UserPanel.this, "Enter the new name for the user:", user.getName());
        if (newName == null || newName.isEmpty() || newName.length() > 100 || newName.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(UserPanel.this, "Invalid name. The name must be between 1 and 100 characters and contain no numeric characters.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String newAgeStr = JOptionPane.showInputDialog(UserPanel.this, "Enter the new age for the user:", user.getAge());
        int newAge;
        try {
            newAge = Integer.parseInt(newAgeStr);
            if (newAge < 0 || newAge > 200) {
                JOptionPane.showMessageDialog(UserPanel.this, "Invalid age. The age must be between 0 and 200 years.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(UserPanel.this, "Invalid age format.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String newBalanceStr = JOptionPane.showInputDialog(UserPanel.this, "Enter the new balance for the user:", user.getBalance());
        float newBalance;
        try {
            newBalance = Float.parseFloat(newBalanceStr);
            if (newBalance < 0) {
                JOptionPane.showMessageDialog(UserPanel.this, "Invalid balance. The balance must be a positive number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(UserPanel.this, "Invalid balance format.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        user.setName(newName);
        user.setAge(newAge);
        user.setBalance(newBalance);
        UserUtils.saveUsersToFile(userList, filename);

        tableModel.setValueAt(newName, selectedRow, 1);
        tableModel.setValueAt(newAge, selectedRow, 2);
        tableModel.setValueAt(newBalance, selectedRow, 3);
    }

    /**
     * Removes an existing user from the list and updates the table display.
     */
    private void removeUser() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(UserPanel.this, "Select a user to remove.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int userId = (int) tableModel.getValueAt(selectedRow, 0);
        tableModel.removeRow(selectedRow);
        UserUtils.removeUser(userId, userList, filename);
    }
    
    /**
     * Searches for a user by ID and displays their details.
     */
    private void searchUserById() {
        String userIdStr = JOptionPane.showInputDialog(UserPanel.this, "Enter the user's ID to search:");
        if (userIdStr == null || userIdStr.isEmpty()) {
            JOptionPane.showMessageDialog(UserPanel.this, "User ID is required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int userId;
        try {
            userId = Integer.parseInt(userIdStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(UserPanel.this, "Invalid user ID format.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User user = User.searchUserByID(userList, userId);
        if (user != null) {
            StringBuilder portfolio = new StringBuilder();
            for (Stock stock : user.getStockPortfolio()) {
                portfolio.append("Symbol: ").append(stock.getSymbol())
                         .append(", Quantity: ").append(stock.getQuantity())
                         .append(", Order Price: ").append(stock.getOrderPrice())
                         .append("\n");
            }

            JOptionPane.showMessageDialog(UserPanel.this, 
                "User Details:\n" +
                "ID: " + user.getId() + "\n" +
                "Name: " + user.getName() + "\n" +
                "Age: " + user.getAge() + "\n" +
                "Balance: " + user.getBalance() + "\n" +
                "Stock Portfolio: \n" + portfolio.toString(),
                "User Found", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(UserPanel.this, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Transfers funds from one user to another and updates the table display.
     */
    private void transferFunds() {
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

        if (senderId == recipientId) {
            JOptionPane.showMessageDialog(UserPanel.this, "Sender and recipient cannot be the same user.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

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

        if (sender.getBalance() < quantity) {
            JOptionPane.showMessageDialog(UserPanel.this, "Insufficient balance.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        sender.setBalance(sender.getBalance() - quantity);
        recipient.setBalance(recipient.getBalance() + quantity);
        UserUtils.saveUsersToFile(userList, filename);

        tableModel.setValueAt(sender.getBalance(), findUserRowById(senderId), 3);
        tableModel.setValueAt(recipient.getBalance(), findUserRowById(recipientId), 3);

        JOptionPane.showMessageDialog(UserPanel.this, "Transfer successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Finds the row index in the table corresponding to a user ID.
     *
     * @param userId The ID of the user to find in the table
     * @return The index of the row in the table, or -1 if not found
     */
    private int findUserRowById(int userId) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if ((int) tableModel.getValueAt(i, 0) == userId) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Updates the table with the current list of users.
     */
    public void updateUserTable() {
        loadUsersIntoTable();
    }
}
