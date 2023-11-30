package entity.user;

import DAO.PortfolioDAO;

public class Portfolio {
    // Attributes
    private double totalBalance;
    private double profitability;

    // Constructor
    public Portfolio(String investorCpf){
        this.totalBalance = 0.0;
        this.profitability = 0.0;

        PortfolioDAO portfolioDAO = new PortfolioDAO();
        portfolioDAO.insertPortfolio(this, investorCpf);
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
