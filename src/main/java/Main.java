import DAO.AssetDAO;
import DAO.InvestorDAO;
import DAO.PortfolioAssetDAO;
import DAO.PortfolioDAO;
import entity.assets.Asset;
import entity.assets.PortfolioAsset;
import entity.user.Investor;

public class Main {
    public static void main(String[] args) {
        Investor user = new Investor("Davi", "147258369", "root123", "asd@gmail.com");
        InvestorDAO investorDAO = new InvestorDAO();
        investorDAO.insertInvestor(user);
        user.getPortfolio().addPortfolio(user.getCpf());

        PortfolioAssetDAO portfolioAssetDAO = new PortfolioAssetDAO();
        Asset asset = new Asset("PETR4", 20.00);
        AssetDAO assetDAO = new AssetDAO();
        assetDAO.insertAsset(asset);

        portfolioAssetDAO.addAssetToPortfolio(user.getPortfolio(), asset);
    }
}
