package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Stock;

public class StockPanel extends JPanel {
    private static final long serialVersionUID = 1L;
	private JTable stockTable;
    private DefaultTableModel tableModel;
    private JButton addButton, editButton, deleteButton, retrievePriceButton;

    public StockPanel() {
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(new String[]{"Symbol", "Quantity", "Price"}, 0);
        stockTable = new JTable(tableModel);

        add(new JScrollPane(stockTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        retrievePriceButton = new JButton("Retrieve Price");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(retrievePriceButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Add Stock
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StockDialog dialog = new StockDialog((Frame) SwingUtilities.getWindowAncestor(StockPanel.this), "Add Stock");
                dialog.setVisible(true);
                String symbol = dialog.getStockSymbol();
                int quantity = dialog.getStockQuantity();
                float price = dialog.getStockPrice();
                if (!symbol.isEmpty() && quantity > 0 && price > 0) {
                    Stock stock = new Stock(symbol, quantity, price);
                    tableModel.addRow(new Object[]{stock.getSymbol(), stock.getQuantity(), stock.getOrderPrice()});
                    JOptionPane.showMessageDialog(StockPanel.this, "Stock added successfully!");
                } else {
                    JOptionPane.showMessageDialog(StockPanel.this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Edit Stock
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = stockTable.getSelectedRow();
                if (selectedRow != -1) {
                    String symbol = (String) tableModel.getValueAt(selectedRow, 0);
                    int quantity = (int) tableModel.getValueAt(selectedRow, 1);
                    float price = (float) tableModel.getValueAt(selectedRow, 2);
                    StockDialog dialog = new StockDialog((Frame) SwingUtilities.getWindowAncestor(StockPanel.this), "Edit Stock");
                    dialog.setStockDetails(symbol, quantity, price);
                    dialog.setVisible(true);
                    symbol = dialog.getStockSymbol();
                    quantity = dialog.getStockQuantity();
                    price = dialog.getStockPrice();
                    if (!symbol.isEmpty() && quantity > 0 && price > 0) {
                        tableModel.setValueAt(symbol, selectedRow, 0);
                        tableModel.setValueAt(quantity, selectedRow, 1);
                        tableModel.setValueAt(price, selectedRow, 2);
                        JOptionPane.showMessageDialog(StockPanel.this, "Stock edited successfully!");
                    } else {
                        JOptionPane.showMessageDialog(StockPanel.this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(StockPanel.this, "Select a stock to edit.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Delete Stock
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = stockTable.getSelectedRow();
                if (selectedRow != -1) {
                    int confirm = JOptionPane.showConfirmDialog(StockPanel.this, "Are you sure you want to delete this stock?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        tableModel.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(StockPanel.this, "Stock deleted successfully!");
                    }
                } else {
                    JOptionPane.showMessageDialog(StockPanel.this, "Select a stock to delete.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Retrieve Stock Price
        retrievePriceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement logic to retrieve stock price
                JOptionPane.showMessageDialog(StockPanel.this, "Retrieve price functionality not implemented.");
            }
        });
    }
}
