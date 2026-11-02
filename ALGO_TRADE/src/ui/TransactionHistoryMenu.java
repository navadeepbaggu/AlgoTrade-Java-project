package ui;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import investor.Investor;
import market.StockMarket;
import storage.TransactionFile;
import trading.Transaction;

public class TransactionHistoryMenu {
    private final TransactionFile transactionFile;
    private final StockMarket stockMarket;

    public TransactionHistoryMenu(
            TransactionFile transactionFile,
            StockMarket stockMarket) {
        this.transactionFile = transactionFile;
        this.stockMarket = stockMarket;
    }

    public void showHistory(Investor investor) throws IOException {
        List<Transaction> transactions =
                transactionFile.loadTransactions(
                        investor.getUsername(), stockMarket);

        System.out.println("\n=== Transaction History ===");

        if (transactions.isEmpty()) {
            System.out.println("No transactions have been made yet.");
            return;
        }

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        for (Transaction transaction : transactions) {
            System.out.printf(
                    "%s | %-4s | %-10s | %3d shares | INR %,.2f%n",
                    transaction.getDateTime().format(formatter),
                    transaction.getType(),
                    transaction.getStock().getSymbol(),
                    transaction.getQuantity(),
                    transaction.getTotalAmount());
        }
    }
}