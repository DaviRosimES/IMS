package DAO;

import entity.assets.Asset;

import java.sql.SQLException;

public class AssetDAO extends ConnectionDAO{
    // Methods
    /**
     * Inserts a new asset into the database with the provided Asset information and associated portfolio ID.
     * Opens a connection to the database, prepares and executes an SQL INSERT statement
     * inserting (name, price, portfolio_id), and then closes the database connection.
     *
     * @param asset        The Asset object containing asset information (name, price).
     * @param portfolioId  The ID of the associated portfolio in the database.
     * @see #openConnectionToDatabase()
     * @see #closeConnectionToDatabase()
     */
    public void insertAsset(Asset asset, int portfolioId) {
        openConnectionToDatabase();
        String sql = "INSERT INTO asset (name, price, protfolio_id) values(?, ?, ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, asset.getName());
            pst.setDouble(2, asset.getPrice());
            pst.setInt(3, portfolioId);
            pst.execute();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            closeConnectionToDatabase();
        }
    }
}
