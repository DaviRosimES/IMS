package DAO;

import entity.assets.Bond;

import java.sql.SQLException;

public class BondDAO extends ConnectionDAO{
    // Methods
    /**
     * Inserts a new bond into the database with the provided Bond information and associated asset ID.
     * Opens a connection to the database, prepares and executes an SQL INSERT statement
     * inserting (name, price, interest_rate, duration, issuer, credit_rating, asset_id),
     * and then closes the database connection.
     *
     * @param bond      The Bond object containing bond information (name, price, interest_rate, duration, issuer, credit_rating).
     * @param assetId   The ID of the associated asset in the database.
     * @see #openConnectionToDatabase()
     * @see #closeConnectionToDatabase()
     */
    public void insertBond(Bond bond, int assetId) {
        openConnectionToDatabase();
        String sql = "INSERT INTO bond (name, price, interest_rate, duration, issuer, credit_rating, asset_id) values(?, ?, ?, ?, ?, ?, ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, bond.getName());
            pst.setDouble(2, bond.getPrice());
            pst.setDouble(3, bond.getInterestRate());
            pst.setInt(4, bond.getDuration());
            pst.setString(5, bond.getIssuer());
            pst.setString(6, bond.getCreditRating());
            pst.setInt(7, assetId);
            pst.execute();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            closeConnectionToDatabase();
        }
    }
}
