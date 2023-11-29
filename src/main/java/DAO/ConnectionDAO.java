package DAO;

import java.sql.*;

public abstract class ConnectionDAO {
    // Attributes
    Connection con; //conexão
    PreparedStatement pst; // declaração(query) preparada - código em sql
    Statement st; //declaração(query) - código em sql
    ResultSet rs; //resposta do banco

    String database = "ims";
    String user = "root";
    String password = "root";
    String url = "jdbc:mysql://localhost:3306/" + database + "?useTimezone=true&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";

    // Methods

    /**
     * Opens a connection to the database using the specified URL, username, and password.
     * The connection details are set by the 'url', 'user', and 'password' member variables.
     * Upon successful connection, a message is printed to the console.
     */
    public void openConnectionToDatabase() {
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexao deu certo!");
        } catch(SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * Closes the database connection, PreparedStatement, Statement, and ResultSet objects if they are not null.
     * Prints a success message to the console upon successful closure.
     */
    public void closeConnectionToDatabase(){
        try{
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
                System.out.println("Conexão fechada com sucesso!");
            }
        } catch (SQLException e){
            System.out.println("Erro: " + e.getMessage());
        }
    }
}