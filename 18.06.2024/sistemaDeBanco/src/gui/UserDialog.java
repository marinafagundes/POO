package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserDialog extends JDialog {
    private static final long serialVersionUID = 1L;
	private JTextField nameField;
    private JButton okButton, cancelButton;
    private String userName;

    public UserDialog(Frame owner, String title) {
        super(owner, title, true);
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

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userName = nameField.getText();
                setVisible(false);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userName = "";
                setVisible(false);
            }
        });

        pack();
        setLocationRelativeTo(owner);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserDetails(String name) {
        nameField.setText(name);
    }
}
