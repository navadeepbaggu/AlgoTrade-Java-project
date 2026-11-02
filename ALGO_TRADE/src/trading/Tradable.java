package trading;

import java.util.List;

import investor.Investor;

public interface Tradable {
    String buyStock(Investor investor, String symbol, int quantity);

    String sellStock(Investor investor, String symbol, int quantity);

    List<Transaction> getTransactions();
}