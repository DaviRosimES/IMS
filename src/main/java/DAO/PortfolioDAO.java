package DAO;

import entity.user.Portfolio;

import java.sql.SQLException;

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
        String sql = "INSERT INTO user (total_balance, profitability, investor_cpf) values(?, ?, ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setDouble(1, portfolio.getTotalBalance());
            pst.setDouble(2, portfolio.getProfitability());
            pst.setString(3, investorCpf);
            pst.execute();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            closeConnectionToDatabase();
        }
    }
}
