package utils;

import model.Stock;

public class StockUtils {

    public static float calculateGainLoss(Stock stock, float currentPrice) {
        float purchasePrice = stock.getOrderPrice();
        int quantity = stock.getQuantity();
        return (currentPrice - purchasePrice) * quantity;
    }

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
