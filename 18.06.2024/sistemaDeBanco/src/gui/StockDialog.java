package gui;

import model.Stock;
import model.User;

import javax.swing.*;
import java.awt.*;

public class StockDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    private JTextField symbolField, quantityField, priceField;
    private JButton okButton, cancelButton;
    private User owner;

    public StockDialog(Frame frame, String title, User owner) {
        super();
        this.owner = owner;
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Symbol:"));
        symbolField = new JTextField();
        add(symbolField);

        add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        add(quantityField);

        add(new JLabel("Price:"));
        priceField = new JTextField();
        add(priceField);

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");

        okButton.addActionListener(e -> dispose());
        cancelButton.addActionListener(e -> dispose());

        add(okButton);
        add(cancelButton);

        pack();
        setLocationRelativeTo(frame);
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

    public Stock getStock() {
        String symbol = getStockSymbol();
        int quantity = getStockQuantity();
        float price = getStockPrice();
        if (!symbol.isEmpty() && quantity > 0 && price > 0) {
            return new Stock(symbol, quantity, price, owner);
        } else {
            return null;
        }
    }

    public void setStockDetails(String symbol, int quantity, float price) {
        symbolField.setText(symbol);
        quantityField.setText(String.valueOf(quantity));
        priceField.setText(String.valueOf(price));
    }
}
