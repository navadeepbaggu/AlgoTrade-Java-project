package portfolio;

public class Stock {
	

	    private String symbol;
	    private String name;
	    private double price;
	    private String securityType;

	    public Stock(String symbol, String name,
	                 double price, String securityType) {

	        this.symbol = symbol;
	        this.name = name;
	        this.price = price;
	        this.securityType = securityType;
	    }

	    public String getSymbol() {
	        return symbol;
	    }

	    public String getName() {
	        return name;
	    }

	    public double getPrice() {
	        return price;
	    }

	    public String getSecurityType() {
	        return securityType;
	    }

	    public void setPrice(double price) {
	        this.price = price;
	    }
	

}
