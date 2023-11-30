package DAO;

import entity.assets.Asset;
import entity.user.Portfolio;

import java.sql.SQLException;
import java.util.ArrayList;

public class PortfolioDAO extends ConnectionDAO{
    // Methods
    /**
     * Inserts a new portfolio into the database with the provided Portfolio information and associated investor ID.
     * Opens a connection to the database, prepares and executes an SQL INSERT statement,
     * and then closes the database connection.
     *
     * @param portfolio   The Portfolio object containing portfolio information (total balance, profitability).
     * @param investorCpf  The cpf of the associated investor in the database.
     * @see #openConnectionToDatabase()
     * @see #closeConnectionToDatabase()
     */
    public void insertPortfolio(Portfolio portfolio, String investorCpf) {
        openConnectionToDatabase();
        String sql = "INSERT INTO portfolios (id, total_balance, profitability, investor_cpf) values(?, ?, ?, ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, portfolio.getPortfolioId());
            pst.setDouble(2, portfolio.getTotalBalance());
            pst.setDouble(3, portfolio.getProfitability());
            pst.setString(4, investorCpf);
            pst.execute();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            closeConnectionToDatabase();
        }
    }

    /**
     * Updates the profitability and total balance of a portfolio in the database.
     * Opens a connection to the database, prepares and executes an SQL UPDATE statement,
     * and then closes the database connection.
     *
     * @param portfolio The Portfolio object containing updated information (total balance, profitability).
     * @see #openConnectionToDatabase()
     * @see #closeConnectionToDatabase()
     */
    public void updatePortfolio(Portfolio portfolio) {
        openConnectionToDatabase();
        String sql = "UPDATE portfolios SET total_balance = ?, profitability = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setDouble(1, portfolio.getTotalBalance());
            pst.setDouble(2, portfolio.getProfitability());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating portfolio: " + e.getMessage());
        } finally {
            closeConnectionToDatabase();
        }
    }

}
