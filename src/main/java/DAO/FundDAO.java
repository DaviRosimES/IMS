package DAO;

import entity.assets.Fund;

import java.sql.SQLException;


public class FundDAO extends ConnectionDAO{
    // Methods
    /**
     * Inserts a new fund into the database with the provided Fund information and associated asset ID.
     * Opens a connection to the database, prepares and executes an SQL INSERT statement
     * inserting (name, price, type, management_fee, manager, historical_performance, asset_id),
     * and then closes the database connection.
     *
     * @param fund      The Fund object containing fund information (name, price, type, management_fee, manager, historical_performance).
     * @param assetId   The ID of the associated asset in the database.
     * @see #openConnectionToDatabase()
     * @see #closeConnectionToDatabase()
     */
    public void insertFund(Fund fund, int assetId) {
        openConnectionToDatabase();
        String sql = "INSERT INTO fund (name, price, type, management_fee, manager, historical_performance, asset_id) values(?, ?, ?, ?, ?, ?, ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, fund.getName());
            pst.setDouble(2, fund.getPrice());
            pst.setString(3, fund.getType());
            pst.setDouble(4, fund.getManagementFee());
            pst.setString(5, fund.getManager());
            pst.setDouble(6, fund.getHistoricalPerformance());
            pst.setInt(7, assetId);
            pst.execute();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            closeConnectionToDatabase();
        }
    }
}
