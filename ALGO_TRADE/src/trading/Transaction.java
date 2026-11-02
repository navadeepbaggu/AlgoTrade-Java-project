package trading;

import java.time.LocalDateTime;
import java.util.Locale;

import market.Stock;

public class Transaction {
    private final String type;
    private final Stock stock;
    private final int quantity;
    private final double pricePerShare;
    private final LocalDateTime dateTime;

    public Transaction(
            String type,
            Stock stock,
            int quantity,
            double pricePerShare) {
        this(type, stock, quantity, pricePerShare, LocalDateTime.now());
    }

    public Transaction(
            String type,
            Stock stock,
            int quantity,
            double pricePerShare,
            LocalDateTime dateTime) {

        if (!"BUY".equalsIgnoreCase(type)
                && !"SELL".equalsIgnoreCase(type)) {
            throw new IllegalArgumentException(
                    "Transaction type must be BUY or SELL.");
        }
        if (stock == null || dateTime == null) {
            throw new IllegalArgumentException(
                    "Stock and transaction date are required.");
        }
        if (quantity <= 0 || pricePerShare <= 0) {
            throw new IllegalArgumentException(
                    "Quantity and price must be greater than zero.");
        }

        this.type = type.toUpperCase(Locale.ROOT);
        this.stock = stock;
        this.quantity = quantity;
        this.pricePerShare = pricePerShare;
        this.dateTime = dateTime;
    }

    public String getType() {
        return type;
    }

    public Stock getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPricePerShare() {
        return pricePerShare;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public double getTotalAmount() {
        return pricePerShare * quantity;
    }
}