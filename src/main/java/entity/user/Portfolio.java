package entity.user;

import DAO.AssetDAO;
import DAO.PortfolioDAO;
import entity.assets.Asset;

import java.util.List;

public class Portfolio {
    // Attributes
    private static int id = 1;
    private int portfolioId;
    private double totalBalance;
    private double profitability;

    // Constructor
    public Portfolio(){
        this.totalBalance = 0.0;
        this.profitability = 0.0;
        this.portfolioId = id++;
    }

    // Getters
    public double getTotalBalance() {
        return totalBalance;
    }

    public double getProfitability() {
        return profitability;
    }

    public int getPortfolioId() {
        return portfolioId;
    }

    // Methods
    public void addPortfolio(String investorCpf){
        PortfolioDAO portfolioDAO = new PortfolioDAO();
        portfolioDAO.insertPortfolio(this, investorCpf);
    }

    public void updateTotalBalance(Asset asset){
        this.totalBalance = asset.getPrice();
        PortfolioDAO portfolioDAO = new PortfolioDAO();
        portfolioDAO.updatePortfolio(this);
    }

}
