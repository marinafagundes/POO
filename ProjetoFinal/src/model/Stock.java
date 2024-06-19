package model;

/**
 * Represents a stock owned by a user.
 */
public class Stock {
    private String symbol;
    private int quantity;
    private float orderPrice;
    private User owner;

    /**
     * Constructs a new Stock object.
     *
     * @param symbol     the symbol of the stock
     * @param quantity   the quantity of the stock
     * @param orderPrice the price at which the stock was ordered
     * @param owner      the User who owns this stock
     */
    public Stock(String symbol, int quantity, float orderPrice, User owner) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.orderPrice = orderPrice;
        this.owner = owner;
    }

    /**
     * Retrieves the symbol of the stock.
     *
     * @return the symbol of the stock
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Sets the symbol of the stock.
     *
     * @param symbol the symbol of the stock
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Retrieves the quantity of the stock.
     *
     * @return the quantity of the stock
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the stock.
     *
     * @param quantity the quantity of the stock
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Retrieves the order price of the stock.
     *
     * @return the order price of the stock
     */
    public float getOrderPrice() {
        return orderPrice;
    }

    /**
     * Sets the order price of the stock.
     *
     * @param orderPrice the order price of the stock
     */
    public void setOrderPrice(float orderPrice) {
        this.orderPrice = orderPrice;
    }

    /**
     * Retrieves the owner of the stock.
     *
     * @return the User who owns this stock
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Sets the owner of the stock.
     *
     * @param owner the User who owns this stock
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }
}
