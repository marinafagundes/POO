package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A dialog window for entering or editing user's name.
 */
public class UserDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    private JTextField nameField;
    private JButton okButton, cancelButton;
    private String userName;

    /**
     * Constructs a modal dialog for entering or editing user's name.
     *
     * @param owner The Frame from which the dialog is displayed
     * @param title The title of the dialog window
     */
    public UserDialog(Frame owner, String title) {
        super(owner, title, true); // Set modal dialog
        setLayout(new BorderLayout());

        nameField = new JTextField(20);

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action listener for OK button
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userName = nameField.getText();
                setVisible(false); // Hide the dialog
            }
        });

        // Action listener for Cancel button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userName = ""; // Reset userName
                setVisible(false); // Hide the dialog
            }
        });

        pack(); // Size the dialog to fit its contents
        setLocationRelativeTo(owner); // Center the dialog relative to its owner frame
    }

    /**
     * Returns the user's name entered in the dialog.
     *
     * @return The user's name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the initial text for the name field in the dialog.
     *
     * @param name The initial name to set
     */
    public void setUserDetails(String name) {
        nameField.setText(name);
    }
}
