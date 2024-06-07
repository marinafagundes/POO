package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StockDialog extends JDialog {
    private static final long serialVersionUID = 1L;
	private JTextField symbolField, quantityField, priceField;
    private JButton okButton, cancelButton;

    public StockDialog(Frame owner, String title) {
        super(owner, title, true);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Símbolo:"));
        symbolField = new JTextField();
        add(symbolField);

        add(new JLabel("Quantidade:"));
        quantityField = new JTextField();
        add(quantityField);

        add(new JLabel("Preço:"));
        priceField = new JTextField();
        add(priceField);

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancelar");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(okButton);
        add(cancelButton);

        pack();
        setLocationRelativeTo(owner);
    }

    public String getStockSymbol() {
        return symbolField.getText();
    }

    public int getStockQuantity() {
        try {
            return Integer.parseInt(quantityField.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public float getStockPrice() {
        try {
            return Float.parseFloat(priceField.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public void setStockDetails(String symbol, int quantity, float price) {
        symbolField.setText(symbol);
        quantityField.setText(String.valueOf(quantity));
        priceField.setText(String.valueOf(price));
    }
}
