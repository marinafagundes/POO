package gui;

import model.User;
import utils.UserUtils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * The main frame of the User and Stock Management application.
 * This frame contains tabs for managing users and stocks.
 */
public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTabbedPane tabbedPane;
    private UserPanel userPanel;
    private StockPanel stockPanel;
    private List<User> userList;

    /**
     * Constructs the main frame for the User and Stock Management application.
     * Loads the list of users from a file and initializes the tabbed pane with
     * UserPanel and StockPanel.
     */
    public MainFrame() {
        setTitle("User and Stock Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Load the list of users from the file
        userList = UserUtils.loadUsersFromFile("users.txt");

        tabbedPane = new JTabbedPane();
        userPanel = new UserPanel(userList);
        stockPanel = new StockPanel(userList);

        tabbedPane.addTab("Users", userPanel);
        tabbedPane.addTab("Stocks", stockPanel);

        // Add a ChangeListener to update user data when the "Users" tab is selected
        tabbedPane.addChangeListener(e -> {
            if (tabbedPane.getSelectedComponent() == userPanel) {
                userPanel.updateUserTable(); // Method to update user data in the UserPanel
            }
        });

        add(tabbedPane, BorderLayout.CENTER);
    }

    /**
     * Main method to start the User and Stock Management application.
     * Creates an instance of MainFrame and sets it visible.
     *
     * @param args Command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
