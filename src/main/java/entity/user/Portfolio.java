package entity.user;

public class Portfolio {
    // Attributes
    private double totalBalance;
    private double profitability;

    // Constructor
    public Portfolio(){
        this.totalBalance = 0.0;
        this.profitability = 0.0;
    }

    // Getters
    public double getTotalBalance() {
        return totalBalance;
    }

    public double getProfitability() {
        return profitability;
    }

    // Methods
}
