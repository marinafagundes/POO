package model;

public class Stock {

    private String symbol;
    private int quantity;
    private float orderPrice;

    public Stock(String symbol, int quantity, float orderPrice) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.orderPrice = orderPrice;
    }

	// Getters and setters
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(float orderPrice) {
        this.orderPrice = orderPrice;
    }
}
