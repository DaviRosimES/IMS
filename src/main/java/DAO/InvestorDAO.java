package DAO;

import entity.user.Investor;

import java.sql.SQLException;
import java.util.ArrayList;

public class InvestorDAO extends ConnectionDAO{
    // Methods
    /**
     * Inserts a new user into the database with the provided Investor information.
     * Opens a connection to the database, prepares and executes an SQL INSERT statement
     * inserting (name, password, email), and then closes the database connection.
     *
     * @param investor The Investor object containing user information (name, password, email).
     * @see #openConnectionToDatabase()
     * @see #closeConnectionToDatabase()
     */
    public void insertInvestor(Investor investor) {
        openConnectionToDatabase();
        String sql = "INSERT INTO investors (cpf, name, password, email) values(?, ?, ?, ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, investor.getCpf());
            pst.setString(2, investor.getName());
            pst.setString(3, investor.getPassword());
            pst.setString(4, investor.getEmail());
            pst.execute();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            closeConnectionToDatabase();
        }
    }

    /**
     * Retrieves a list of all investors from the database.
     * Opens a connection to the database, executes an SQL SELECT statement,
     * creates Investor objects from the result set, prints information to the console,
     * and then closes the database connection.
     *
     * @see #openConnectionToDatabase()
     * @see #closeConnectionToDatabase()
     */
    public void selectAllInvestors() {
        openConnectionToDatabase();
        String sql = "SELECT * FROM investors";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("List of investors: ");
            while (rs.next()) {
                Investor investorAux = new Investor(rs.getString("name"),rs.getString("cpf"),rs.getString("password"), rs.getString("email"));
                System.out.println("nome = " + investorAux.getName());
                System.out.println("cpf = " + investorAux.getCpf());
                System.out.println("email = " + investorAux.getEmail());
                System.out.println("--------------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            closeConnectionToDatabase();
        }
    }
}
