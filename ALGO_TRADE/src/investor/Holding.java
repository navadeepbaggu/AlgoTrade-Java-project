package investor;

import market.Stock;

public class Holding implements Comparable<Holding> {
    private final Stock stock;
    private int quantity;

    public Holding(Stock stock, int quantity) {
        if (stock == null) {
            throw new IllegalArgumentException("Stock cannot be empty.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        this.stock = stock;
        this.quantity = quantity;
    }

    public Stock getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

  
    public void addShares(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        quantity += amount;
    }

    public void removeShares(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        if (amount > quantity) {
            throw new IllegalArgumentException(
                    "You cannot sell more shares than you own.");
        }
        quantity -= amount;
    }

    @Override
    public int compareTo(Holding other) {
        return stock.getSymbol()
                .compareToIgnoreCase(other.stock.getSymbol());
    }
    
    
    public double getMarketValue() {
        return stock.calculateValue(quantity);
    }
}