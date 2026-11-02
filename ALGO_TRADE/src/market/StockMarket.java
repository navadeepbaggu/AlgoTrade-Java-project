package market;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Comparator;
import java.util.Locale;
public class StockMarket {
    private final List<Stock> stocks = new ArrayList<>();

    public StockMarket() {
        stocks.add(new Stock("INFY", "Infosys", 1450.50));
        stocks.add(new Stock("TCS", "Tata Consultancy Services", 3820.00));
        stocks.add(new Stock("RELIANCE", "Reliance Industries", 2940.75));
        stocks.add(new Stock("HDFCBANK", "HDFC Bank", 1675.25));
        stocks.add(new Stock("WIPRO", "Wipro", 520.00));
        stocks.add(new Stock("ICICIBANK", "ICICI Bank", 1250.00));
        stocks.add(new Stock("SBIN", "State Bank of India", 780.00));
        stocks.add(new Stock("ITC", "ITC Limited", 450.00));
        stocks.add(new Stock("LT", "Larsen & Toubro", 3600.00));
        stocks.add(new Stock("BHARTIARTL", "Bharti Airtel", 1800.00));
        stocks.add(new Stock("MARUTI", "Maruti Suzuki India", 12500.00));
        stocks.add(new Stock("TITAN", "Titan Company", 3400.00));
        stocks.add(new Stock("AXISBANK", "Axis Bank", 1150.00));
        stocks.add(new Stock("KOTAKBANK", "Kotak Mahindra Bank", 1750.00));
        stocks.add(new Stock("HINDUNILVR", "Hindustan Unilever", 2450.00));
        stocks.add(new Stock("TATAMOTORS", "Tata Motors", 950.00));
        stocks.add(new Stock("SUNPHARMA", "Sun Pharmaceutical Industries", 1650.00));
        stocks.add(new Stock("TATASTEEL", "Tata Steel", 160.00));
        stocks.add(new Stock("BAJFINANCE", "Bajaj Finance", 7200.00));
    }

    public List<Stock> getAllStocks() {
        return new ArrayList<>(stocks);
    }

    public Optional<Stock> findBySymbol(String symbol) {
        return stocks.stream()
                .filter(stock -> stock.getSymbol().equalsIgnoreCase(symbol))
                .findFirst();
    }
    public List<Stock> search(String query) {
        if (query == null || query.isBlank()) {
            return List.of();
        }

        String searchText = query.trim().toLowerCase(Locale.ROOT);

        return stocks.stream()
                .filter(stock ->
                        stock.getSymbol()
                                .toLowerCase(Locale.ROOT)
                                .contains(searchText)
                        || stock.getCompanyName()
                                .toLowerCase(Locale.ROOT)
                                .contains(searchText))
                .sorted(Comparator.comparing(
                        Stock::getSymbol,
                        String.CASE_INSENSITIVE_ORDER))
                .toList();
    }
}