package entity.assets;

public class Stock extends Asset{
    // Attributes
    private int numShares;
    private double dividendPerShare;
    private double marketCap;
    private String industrySector;

    // Constructor
    public Stock(String name, double price, int numShares, double dividendPerShare, double marketCap, String industrySector) {
        super(name, price);
        this.numShares = numShares;
        this.dividendPerShare = dividendPerShare;
        this.marketCap = marketCap;
        this.industrySector = industrySector;
    }

    // Getters
    public int getNumShares() {
        return numShares;
    }

    public double getDividendPerShare() {
        return dividendPerShare;
    }

    public double getMarketCap() {
        return marketCap;
    }

    public String getIndustrySector() {
        return industrySector;
    }

    // Methods
}
