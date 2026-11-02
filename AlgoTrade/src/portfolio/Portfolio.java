package portfolio;
import java.util.ArrayList;
public class Portfolio implements ReportGenerator {

	    private ArrayList<Stock> stocks;
	    private ArrayList<Integer> quantities;

	    public Portfolio() {
	        stocks = new ArrayList<>();
	        quantities = new ArrayList<>();
	    }

	    // Add stock to portfolio
	    public void addHolding(Stock stock, int quantity) {

	        int index = findStockIndex(stock);

	        if (index == -1) {

	            stocks.add(stock);
	            quantities.add(quantity);

	        } else {

	            int oldQuantity = quantities.get(index);

	            quantities.set(
	                    index,
	                    oldQuantity + quantity
	            );
	        }
	    }

	    // Remove stock from portfolio
	    public void removeHolding(Stock stock, int quantity) {

	        int index = findStockIndex(stock);

	        if (index != -1) {

	            int oldQuantity = quantities.get(index);

	            int newQuantity = oldQuantity - quantity;

	            if (newQuantity <= 0) {

	                stocks.remove(index);
	                quantities.remove(index);

	            } else {

	                quantities.set(index, newQuantity);
	            }
	        }
	    }

	    // Find stock position in ArrayList
	    private int findStockIndex(Stock stock) {

	        for (int i = 0; i < stocks.size(); i++) {

	            if (stocks.get(i)
	                    .getSymbol()
	                    .equalsIgnoreCase(stock.getSymbol())) {

	                return i;
	            }
	        }

	        return -1;
	    }

	    // Check whether investor owns enough shares
	    public boolean hasStock(
	            Stock stock,
	            int quantity) {

	        int index = findStockIndex(stock);

	        if (index == -1) {
	            return false;
	        }

	        return quantities.get(index) >= quantity;
	    }

	    // Calculate total current portfolio value
	    public double getTotalValue() {

	        double total = 0;

	        for (int i = 0; i < stocks.size(); i++) {

	            total += stocks.get(i).getPrice()
	                    * quantities.get(i);
	        }

	        return total;
	    }

	    // Display portfolio
	    public void displayPortfolio() {

	        System.out.println();
	        System.out.println("========== PORTFOLIO ==========");

	        if (stocks.isEmpty()) {

	            System.out.println("Portfolio is empty.");
	            return;
	        }

	        System.out.printf(
	                "%-10s %-20s %-10s %-15s%n",
	                "Symbol",
	                "Stock",
	                "Quantity",
	                "Value"
	        );

	        System.out.println(
	                "------------------------------------------------"
	        );

	        for (int i = 0; i < stocks.size(); i++) {

	            Stock stock = stocks.get(i);

	            int quantity = quantities.get(i);

	            double value =
	                    stock.getPrice() * quantity;

	            System.out.printf(
	                    "%-10s %-20s %-10d ₹%-15.2f%n",
	                    stock.getSymbol(),
	                    stock.getName(),
	                    quantity,
	                    value
	            );
	        }

	        System.out.println(
	                "------------------------------------------------"
	        );

	        System.out.printf(
	                "Total Portfolio Value : ₹%.2f%n",
	                getTotalValue()
	        );
	    }

	    // Generate portfolio report
	    @Override
	    public void generateReport() {

	        System.out.println();
	        System.out.println(
	                "========== PORTFOLIO REPORT =========="
	        );

	        displayPortfolio();

	        System.out.println(
	                "======================================"
	        );
	    }

	    // Generate portfolio analytics
	    @Override
	    public void generateAnalytics() {

	        System.out.println();
	        System.out.println(
	                "========== PORTFOLIO ANALYTICS =========="
	        );

	        System.out.printf(
	                "Portfolio Value : ₹%.2f%n",
	                getTotalValue()
	        );

	        System.out.println(
	                "=========================================="
	        );
	    }

	    // Get stocks
	    public ArrayList<Stock> getStocks() {
	        return stocks;
	    }

	    // Get quantities
	    public ArrayList<Integer> getQuantities() {
	        return quantities;
	    }
	}

