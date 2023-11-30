package entity.assets;

import entity.user.Portfolio;

public class PortfolioAsset {
    // Attributes
    private Portfolio portfolio;
    private Asset asset;

    // Constructor
    public PortfolioAsset(Portfolio portfolio, Asset asset) {
        this.portfolio = portfolio;
        this.asset = asset;
    }

    // Getters
    public Portfolio getPortfolio() {
        return portfolio;
    }

    public Asset getAsset() {
        return asset;
    }
}
