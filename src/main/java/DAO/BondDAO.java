package DAO;

import entity.assets.Bond;

import java.sql.SQLException;
import java.sql.Statement;

public class BondDAO extends ConnectionDAO{
    // Methods
    /**
     * Insere um novo ativo de título no banco de dados, considerando o relacionamento 1 para 1 com Asset.
     * Abre uma conexão com o banco de dados, insere um novo registro na tabela assets e obtém o ID associado,
     * em seguida, insere um novo registro na tabela bonds com a chave estrangeira referente ao ID de assets,
     * e finalmente fecha a conexão com o banco de dados.
     *
     * @param bond O objeto Bond contendo informações sobre o ativo de título.
     * @see #openConnectionToDatabase()
     * @see #closeConnectionToDatabase()
     */
    public void insertBond(Bond bond) {
        openConnectionToDatabase();
        String insertAssetSql = "INSERT INTO assets (id, name, price) VALUES (?, ?, ?)";
        try {
            pst = con.prepareStatement(insertAssetSql, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, bond.getAssetId());
            pst.setString(2, bond.getName());
            pst.setDouble(3, bond.getPrice());
            pst.executeUpdate();
            rs = pst.getGeneratedKeys();
            int assetId = 0;
            if (rs.next()) {
                assetId = rs.getInt(1);
            }
            String insertBondSql = "INSERT INTO bonds (name, price, interest_rate, duration, issuer, credit_rating, asset_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pst = con.prepareStatement(insertBondSql);
            pst.setString(1, bond.getName());
            pst.setDouble(2, bond.getPrice());
            pst.setDouble(3, bond.getInterestRate());
            pst.setInt(4, bond.getDuration());
            pst.setString(5, bond.getIssuer());
            pst.setString(6, bond.getCreditRating());
            pst.setInt(7, assetId);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting bond: " + e.getMessage());
        } finally {
            closeConnectionToDatabase();
        }
    }
}
