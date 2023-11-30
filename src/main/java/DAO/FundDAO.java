package DAO;

import entity.assets.Fund;

import java.sql.SQLException;
import java.sql.Statement;


public class FundDAO extends ConnectionDAO{
    // Methods
    /**
     * Insere um novo fundo no banco de dados, considerando o relacionamento 1 para 1 com Asset.
     * Abre uma conexão com o banco de dados, insere um novo registro na tabela assets e obtém o ID associado,
     * em seguida, insere um novo registro na tabela funds com a chave estrangeira referente ao ID de assets,
     * e finalmente fecha a conexão com o banco de dados.
     *
     * @param fund O objeto Fund contendo informações sobre o fundo.
     * @see #openConnectionToDatabase()
     * @see #closeConnectionToDatabase()
     */
    public void insertFund(Fund fund) {
        openConnectionToDatabase();
        String insertAssetSql = "INSERT INTO assets (id, name, price) VALUES (?, ?, ?)";
        try {
            pst = con.prepareStatement(insertAssetSql, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, fund.getAssetId());
            pst.setString(2, fund.getName());
            pst.setDouble(3, fund.getPrice());
            pst.executeUpdate();
            rs = pst.getGeneratedKeys();
            int assetId = 0;
            if (rs.next()) {
                assetId = rs.getInt(1);
            }
            String insertFundSql = "INSERT INTO funds (name, price, type, management_fee, manager, historical_performance, asset_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pst = con.prepareStatement(insertFundSql);
            pst.setString(1, fund.getName());
            pst.setDouble(2, fund.getPrice());
            pst.setString(3, fund.getType());
            pst.setDouble(4, fund.getManagementFee());
            pst.setString(5, fund.getManager());
            pst.setDouble(6, fund.getHistoricalPerformance());
            pst.setInt(7, assetId);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting fund: " + e.getMessage());
        } finally {
            closeConnectionToDatabase();
        }
    }
}
