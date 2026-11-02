package ui;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import alerts.PriceAlert;
import investor.Investor;
import market.Stock;
import market.StockMarket;
import storage.InvestorFile;

public class PriceAlertMenu {
    private final StockMenu stockMenu;
    private final StockMarket stockMarket;
    private final InvestorFile investorFile;

    public PriceAlertMenu(
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
            System.out.println("\n=== Price Alerts ===");
            System.out.println("1. View alerts");
            System.out.println("2. Add an alert");
            System.out.println("3. Check triggered alerts");
            System.out.println("4. Remove an alert");
            System.out.println("0. Return to main menu");
            System.out.print("Choose an option: ");

            String choice = input.nextLine().trim();

            switch (choice) {
                case "1" -> showAlerts(investor);
                case "2" -> addAlert(input, investor);
                case "3" -> showTriggeredAlerts(investor);
                case "4" -> removeAlert(input, investor);
                case "0" -> managing = false;
                default -> System.out.println(
                        "Please choose 0, 1, 2, 3, or 4.");
            }
        }
    }

    private void addAlert(Scanner input, Investor investor)
            throws IOException {
        stockMenu.displayStocks();

        System.out.print("Enter the stock symbol for the alert: ");
        String symbol = input.nextLine().trim();

        Optional<Stock> selectedStock =
                stockMarket.findBySymbol(symbol);

        if (selectedStock.isEmpty()) {
            System.out.println(
                    "That stock symbol is not in the market list.");
            return;
        }

        if (!investor.getWatchlist().isWatching(symbol)) {
            System.out.println(
                    "Add this stock to your watchlist before creating an alert.");
            return;
        }

        System.out.print("Enter the target price: INR ");
        double targetPrice;

        try {
            targetPrice = Double.parseDouble(input.nextLine().trim());
        } catch (NumberFormatException exception) {
            System.out.println("Enter a valid price.");
            return;
        }

        if (targetPrice <= 0) {
            System.out.println(
                    "Target price must be greater than zero.");
            return;
        }

        System.out.println(
                "1. Alert when price reaches or goes above target");
        System.out.println(
                "2. Alert when price reaches or goes below target");
        System.out.print("Choose an alert type: ");
        String alertChoice = input.nextLine().trim();

        PriceAlert alert;

        if (alertChoice.equals("1")) {
            alert = PriceAlert.whenPriceGoesAbove(
                    selectedStock.get(), targetPrice);
        } else if (alertChoice.equals("2")) {
            alert = PriceAlert.whenPriceGoesBelow(
                    selectedStock.get(), targetPrice);
        } else {
            System.out.println("Please choose 1 or 2.");
            return;
        }

        boolean added =
                investor.getPriceAlertManager().addAlert(alert);

        if (!added) {
            System.out.println("That alert already exists.");
            return;
        }

        investorFile.saveInvestor(investor);
        System.out.println("Price alert saved.");
    }

    private void showAlerts(Investor investor) {
        List<PriceAlert> alerts =
                investor.getPriceAlertManager().getAlerts();

        System.out.println("\n=== Your Price Alerts ===");

        if (alerts.isEmpty()) {
            System.out.println("You have no price alerts.");
            return;
        }

        for (int i = 0; i < alerts.size(); i++) {
            PriceAlert alert = alerts.get(i);
            String status =
                    alert.isTriggered() ? "TRIGGERED" : "Waiting";

            System.out.printf(
                    "%d. %s | %s INR %,.2f | Current INR %,.2f | %s%n",
                    i + 1,
                    alert.getStock().getSymbol(),
                    alert.getConditionName(),
                    alert.getTargetPrice(),
                    alert.getStock().getPrice(),
                    status);
        }
    }

    private void showTriggeredAlerts(Investor investor) {
        List<PriceAlert> triggered =
                investor.getPriceAlertManager().getTriggeredAlerts();

        System.out.println("\n=== Triggered Alerts ===");

        if (triggered.isEmpty()) {
            System.out.println(
                    "No alerts are triggered at the current sample prices.");
            return;
        }

        for (PriceAlert alert : triggered) {
            System.out.printf(
                    "%s is at INR %,.2f; your %s target is INR %,.2f.%n",
                    alert.getStock().getSymbol(),
                    alert.getStock().getPrice(),
                    alert.getConditionName(),
                    alert.getTargetPrice());
        }
    }

    private void removeAlert(Scanner input, Investor investor)
            throws IOException {
        showAlerts(investor);

        if (investor.getPriceAlertManager().isEmpty()) {
            return;
        }

        System.out.print("Enter the alert number to remove: ");
        int number;

        try {
            number = Integer.parseInt(input.nextLine().trim());
        } catch (NumberFormatException exception) {
            System.out.println("Enter a valid alert number.");
            return;
        }

        try {
            PriceAlert removed =
                    investor.getPriceAlertManager().removeAlert(number);
            investorFile.saveInvestor(investor);
            System.out.println("Alert for "
                    + removed.getStock().getSymbol() + " removed.");
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }
}