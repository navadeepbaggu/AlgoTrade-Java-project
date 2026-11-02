package portfolio;


	

	public class TestPortfolio {

	 
	    	

	    	    public static void main(String[] args) {

	    	        Portfolio portfolio = new Portfolio();

	    	        Stock tcs = new Stock(
	    	                "TCS",
	    	                "Tata Consultancy",
	    	                3500,
	    	                "Equity"
	    	        );

	    	        Stock infosys = new Stock(
	    	                "INFY",
	    	                "Infosys",
	    	                1800,
	    	                "Equity"
	    	        );

	    	        portfolio.addHolding(tcs, 10);
	    	        portfolio.addHolding(infosys, 5);

	    	        portfolio.displayPortfolio();

	    	        System.out.println(
	    	                "\nTotal Value: ₹"
	    	                + portfolio.getTotalValue()
	    	        );
	    	    }
	    	
	    
	}
