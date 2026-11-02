package ui;

import java.util.List;
import java.util.Scanner;
import java.util.Optional;
import market.Stock;
import market.StockMarket;

public class StockMenu {
    private final StockMarket stockMarket;

    public StockMenu(StockMarket stockMarket) {
        this.stockMarket = stockMarket;
    }

    // Used when the user opens the market from the main menu.
    public void showStocks(Scanner input) {
        displayStocks();

        System.out.println("0. Return to main menu");
        System.out.print("Choose an option: ");

        while (!input.nextLine().trim().equals("0")) {
            System.out.println("Please enter 0 to return to the main menu.");
            System.out.print("Choose an option: ");
        }
    }

    // Used by other features that need to show stocks without leaving their flow.
    public void displayStocks() {
        List<Stock> stocks = stockMarket.getAllStocks();

        System.out.println();
        System.out.println("==============================================================");
        System.out.println("                    ALGOTRADE MARKET");
        System.out.println("==============================================================");

        if (stocks.isEmpty()) {
            System.out.println("No stocks are currently available.");
            System.out.println("Please check again later.");
            System.out.println("==============================================================");
            return;
        }

        System.out.printf("%-5s %-14s %-32s %14s%n",
                "No.", "Symbol", "Company", "Price (INR)");
        System.out.println("--------------------------------------------------------------");

        int number = 1;

        for (Stock stock : stocks) {
            System.out.printf("%-5d %-14s %-32s ₹%,13.2f%n",
                    number,
                    stock.getSymbol(),
                    stock.getCompanyName(),
                    stock.getPrice());
            number++;
        }

        System.out.println("--------------------------------------------------------------");
        System.out.println("Stocks available: " + stocks.size());
        System.out.println("Prices shown are sample values for this project.");
        System.out.println("==============================================================");
    }
    public void searchStocks(Scanner input) {
        System.out.print("Search by stock symbol or company name: ");
        String query = input.nextLine().trim();

        if (query.isBlank()) {
            System.out.println("Please enter a search term.");
            return;
        }

        List<Stock> matches = stockMarket.search(query);

        System.out.println("\n=== Stock Search Results ===");

        if (matches.isEmpty()) {
            System.out.println("No matching stocks found.");
        } else {
            System.out.printf("%-14s %-32s %14s%n",
                    "Symbol", "Company", "Price (INR)");
            System.out.println(
                    "--------------------------------------------------------------");

            for (Stock stock : matches) {
                System.out.printf("%-14s %-32s ₹%,13.2f%n",
                        stock.getSymbol(),
                        stock.getCompanyName(),
                        stock.getPrice());
            }
        }

        System.out.println("Press Enter to return to the main menu.");
        input.nextLine();
    }
    public Optional<Stock> searchAndSelectStock(Scanner input) {
        while (true) {
            System.out.println("\n=== Select a Stock ===");
            System.out.println("1. Search by symbol or company name");
            System.out.println("2. Browse all stocks");
            System.out.println("0. Cancel");
            System.out.print("Choose an option: ");

            String choice = input.nextLine().trim();
            List<Stock> choices;

            if (choice.equals("0")) {
                return Optional.empty();
            } else if (choice.equals("1")) {
                System.out.print("Enter a search term: ");
                String query = input.nextLine().trim();

                if (query.isBlank()) {
                    System.out.println("Please enter a search term.");
                    continue;
                }

                choices = stockMarket.search(query);
            } else if (choice.equals("2")) {
                choices = stockMarket.getAllStocks();
            } else {
                System.out.println("Please choose 0, 1, or 2.");
                continue;
            }

            if (choices.isEmpty()) {
                System.out.println("No matching stocks found.");
                continue;
            }

            displayStockChoices(choices);

            while (true) {
                System.out.print(
                        "Enter a listed symbol, M for the previous menu, "
                                + "or 0 to cancel: ");
                String symbol = input.nextLine().trim();

                if (symbol.equals("0")) {
                    return Optional.empty();
                }

                if (symbol.equalsIgnoreCase("M")) {
                    break;
                }

                Optional<Stock> selected = choices.stream()
                        .filter(stock ->
                                stock.getSymbol().equalsIgnoreCase(symbol))
                        .findFirst();

                if (selected.isPresent()) {
                    return selected;
                }

                System.out.println("Please enter one of the symbols shown.");
            }
        }
    }

    private void displayStockChoices(List<Stock> stocks) {
        System.out.printf("%-14s %-32s %14s%n",
                "Symbol", "Company", "Price (INR)");
        System.out.println(
                "--------------------------------------------------------------");

        for (Stock stock : stocks) {
            System.out.printf("%-14s %-32s ₹%,13.2f%n",
                    stock.getSymbol(),
                    stock.getCompanyName(),
                    stock.getPrice());
        }
    }
}