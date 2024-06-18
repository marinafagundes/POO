package model;

public class Stock {
    private String symbol;
    private int quantity;
    private float orderPrice;
    private User owner;

    public Stock(String symbol, int quantity, float orderPrice, User owner) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.orderPrice = orderPrice;
        this.owner = owner;
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
