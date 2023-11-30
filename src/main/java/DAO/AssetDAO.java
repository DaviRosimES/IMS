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
     * @see #openConnectionToDatabase()
     * @see #closeConnectionToDatabase()
     */
    public void insertAsset(Asset asset) {
        openConnectionToDatabase();
        String sql = "INSERT INTO assets (id, name, price) values(?, ?, ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, asset.getAssetId());
            pst.setString(2, asset.getName());
            pst.setDouble(3, asset.getPrice());
            pst.execute();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            closeConnectionToDatabase();
        }
    }

    /**
     * Obtém um objeto Asset com base no ID.
     *
     * @param assetId O ID do ativo.
     * @return Um objeto Asset ou null se o ativo não for encontrado.
     * @see #openConnectionToDatabase()
     * @see #closeConnectionToDatabase()
     */
    public Asset getAssetById(int assetId) {
        openConnectionToDatabase();
        String sql = "SELECT * FROM asset WHERE id = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, assetId);
            rs = pst.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                return new Asset(name, price);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao obter ativo por ID: " + e.getMessage());
        } finally {
            closeConnectionToDatabase();
        }
        return null;
    }
}
