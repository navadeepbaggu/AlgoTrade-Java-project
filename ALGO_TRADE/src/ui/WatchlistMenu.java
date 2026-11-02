package ui;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import investor.Investor;
import market.Stock;
import market.StockMarket;
import storage.InvestorFile;

public class WatchlistMenu {
    private final StockMenu stockMenu;
    private final StockMarket stockMarket;
    private final InvestorFile investorFile;

    public WatchlistMenu(
            StockMenu stockMenu,
            StockMarket stockMarket,
            InvestorFile investorFile) {
        this.stockMenu = stockMenu;
        this.stockMarket = stockMarket;
        this.investorFile = investorFile;
    }

    public void manage(Scanner input, Investor investor)
            throws IOException {
        boolean managing = true;

        while (managing) {
            System.out.println("\n=== Watchlist ===");
            System.out.println("1. View watchlist");
            System.out.println("2. Add a stock");
            System.out.println("3. Remove a stock");
            System.out.println("0. Return to main menu");
            System.out.print("Choose an option: ");

            String choice = input.nextLine().trim();

            switch (choice) {
                case "1" -> showWatchlist(investor);
                case "2" -> addStock(input, investor);
                case "3" -> removeStock(input, investor);
                case "0" -> managing = false;
                default -> System.out.println(
                        "Please choose 0, 1, 2, or 3.");
            }
        }
    }

    private void addStock(Scanner input, Investor investor)
            throws IOException {
        stockMenu.displayStocks();

        System.out.print("Enter the symbol to add: ");
        String symbol = input.nextLine().trim();

        Optional<Stock> stock = stockMarket.findBySymbol(symbol);

        if (stock.isEmpty()) {
            System.out.println(
                    "That stock symbol is not in the market list.");
        } else if (investor.getWatchlist().addStock(stock.get())) {
            investorFile.saveInvestor(investor);
            System.out.println(symbol + " added to your watchlist.");
        } else {
            System.out.println(
                    symbol + " is already in your watchlist.");
        }
    }

    private void removeStock(Scanner input, Investor investor)
            throws IOException {
        showWatchlist(investor);

        if (investor.getWatchlist().isEmpty()) {
            return;
        }

        System.out.print("Enter the symbol to remove: ");
        String symbol = input.nextLine().trim();

        try {
            if (investor.getWatchlist().removeStock(symbol)) {
                investorFile.saveInvestor(investor);
                System.out.println(symbol + " removed from your watchlist.");
            } else {
                System.out.println(
                        symbol + " was not in your watchlist.");
            }
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void showWatchlist(Investor investor) {
        System.out.println("\n=== Your Watchlist ===");

        if (investor.getWatchlist().isEmpty()) {
            System.out.println("Your watchlist is empty.");
            return;
        }

        System.out.printf("%-14s %-32s %14s%n",
                "Symbol", "Company", "Price (INR)");
        System.out.println(
                "--------------------------------------------------------------");

        for (Stock stock : investor.getWatchlist().getStocks()) {
            System.out.printf("%-14s %-32s ₹%,13.2f%n",
                    stock.getSymbol(),
                    stock.getCompanyName(),
                    stock.getPrice());
        }
    }
}