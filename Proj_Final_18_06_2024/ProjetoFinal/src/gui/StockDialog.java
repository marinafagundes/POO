package gui;

import model.Stock;
import model.User;

import javax.swing.*;
import java.awt.*;

/**
 * Dialog window for entering stock details.
 */
public class StockDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    private JTextField symbolField, quantityField, priceField;
    private JButton okButton, cancelButton;
    private User owner;

    /**
     * Constructs a StockDialog with specified owner and title.
     *
     * @param frame The parent frame to center the dialog on
     * @param title The title of the dialog window
     * @param owner The owner (User) associated with the stock being entered
     */
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

        // Actions for OK and Cancel buttons
        okButton.addActionListener(e -> dispose());
        cancelButton.addActionListener(e -> dispose());

        add(okButton);
        add(cancelButton);

        pack();
        setLocationRelativeTo(frame);
    }

    /**
     * Gets the stock symbol entered in the dialog.
     *
     * @return The stock symbol
     */
    public String getStockSymbol() {
        return symbolField.getText();
    }

    /**
     * Gets the stock quantity entered in the dialog.
     *
     * @return The stock quantity
     */
    public int getStockQuantity() {
        try {
            return Integer.parseInt(quantityField.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Gets the stock price entered in the dialog.
     *
     * @return The stock price
     */
    public float getStockPrice() {
        try {
            return Float.parseFloat(priceField.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Constructs a Stock object with the details entered in the dialog.
     *
     * @return The Stock object created, or null if any field is invalid
     */
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

    /**
     * Sets the details (symbol, quantity, price) to display in the dialog.
     *
     * @param symbol   The stock symbol to set
     * @param quantity The stock quantity to set
     * @param price    The stock price to set
     */
    public void setStockDetails(String symbol, int quantity, float price) {
        symbolField.setText(symbol);
        quantityField.setText(String.valueOf(quantity));
        priceField.setText(String.valueOf(price));
    }
}
