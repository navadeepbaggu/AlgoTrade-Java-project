package investor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import market.Stock;

public class Watchlist {
    private final List<Stock> stocks = new ArrayList<>();

    public boolean addStock(Stock stock) {
        if (stock == null) {
            throw new IllegalArgumentException("Stock cannot be empty.");
        }

        if (isWatching(stock.getSymbol())) {
            return false;
        }

        stocks.add(stock);
        return true;
    }

    public boolean removeStock(String symbol) {
        if (symbol == null || symbol.isBlank()) {
            throw new IllegalArgumentException(
                    "Stock symbol cannot be empty.");
        }

        return stocks.removeIf(stock ->
                stock.getSymbol().equalsIgnoreCase(symbol));
    }

    public boolean isWatching(String symbol) {
        if (symbol == null || symbol.isBlank()) {
            return false;
        }

        return stocks.stream()
                .anyMatch(stock ->
                        stock.getSymbol().equalsIgnoreCase(symbol));
    }

    public List<Stock> getStocks() {
        return Collections.unmodifiableList(
                new ArrayList<>(stocks));
    }

    public boolean isEmpty() {
        return stocks.isEmpty();
    }
}