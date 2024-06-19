package utils;

import model.Stock;

/**
 * Utility class for performing operations related to stocks.
 */
public class StockUtils {

    /**
     * Calculates the gain or loss for a stock based on current price.
     *
     * @param stock       the stock for which to calculate gain/loss
     * @param currentPrice the current price of the stock
     * @return the gain or loss amount
     */
    public static float calculateGainLoss(Stock stock, float currentPrice) {
        float purchasePrice = stock.getOrderPrice();
        int quantity = stock.getQuantity();
        return (currentPrice - purchasePrice) * quantity;
    }

    /**
     * Transfers a specified quantity of stock from one stock object to another.
     *
     * @param fromStock the stock to transfer from
     * @param toStock   the stock to transfer to
     * @param quantity  the quantity of stock to transfer
     * @return true if the transfer was successful, false otherwise
     */
    public static boolean transferStock(Stock fromStock, Stock toStock, int quantity) {
        if (fromStock.getQuantity() < quantity) {
            System.out.println("Error: Insufficient quantity for transfer.");
            return false;
        }

        fromStock.setQuantity(fromStock.getQuantity() - quantity);
        toStock.setQuantity(toStock.getQuantity() + quantity);
        System.out.println("Stock transfer successful.");
        return true;
    }
}
