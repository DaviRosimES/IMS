package DAO;

import entity.assets.Stock;

import java.sql.SQLException;

public class StockDAO extends ConnectionDAO{
    // Methods
    /**
     * Inserts a new stock into the database with the provided Stock information and associated asset ID.
     * Opens a connection to the database, prepares and executes an SQL INSERT statement
     * inserting (name, price, num_shares, dividend_per_share, market_cap, industry_sector, asset_id),
     * and then closes the database connection.
     *
     * @param stock     The Stock object containing stock information (name, price, num_shares, dividend_per_share, market_cap, industry_sector).
     * @param assetId   The ID of the associated asset in the database.
     * @see #openConnectionToDatabase()
     * @see #closeConnectionToDatabase()
     */
    public void insertStock(Stock stock, int assetId) {
        openConnectionToDatabase();
        String sql = "INSERT INTO stock (name, price, num_shares, dividend_per_share, market_cap, industry_sector, asset_id) values(?, ?, ?, ?, ?, ?, ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, stock.getName());
            pst.setDouble(2, stock.getPrice());
            pst.setInt(3, stock.getNumShares());
            pst.setDouble(4, stock.getDividendPerShare());
            pst.setDouble(5, stock.getMarketCap());
            pst.setString(6, stock.getIndustrySector());
            pst.setInt(7, assetId);
            pst.execute();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            closeConnectionToDatabase();
        }
    }

}
