package gui;

import model.User;
import utils.UserUtils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    public MainFrame() {
        setTitle("User and Stock Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Carregar a lista de usuários do arquivo
        List<User> userList = UserUtils.loadUsersFromFile("users.txt");

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Users", new UserPanel(userList));
        tabbedPane.addTab("Stocks", new StockPanel(userList));

        add(tabbedPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            }
        });
    }
}
