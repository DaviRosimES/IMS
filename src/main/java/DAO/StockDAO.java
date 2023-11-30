package DAO;

import entity.assets.Stock;

import java.sql.SQLException;
import java.sql.Statement;

public class StockDAO extends ConnectionDAO{
    // Methods
    /**
     * Insere um novo ativo de ação no banco de dados, considerando o relacionamento 1 para 1 com Asset.
     * Abre uma conexão com o banco de dados, insere um novo registro na tabela assets e obtém o ID associado,
     * em seguida, insere um novo registro na tabela stocks com a chave estrangeira referente ao ID de assets,
     * e finalmente fecha a conexão com o banco de dados.
     *
     * @param stock O objeto Stock contendo informações sobre o ativo de ação.
     * @see #openConnectionToDatabase()
     * @see #closeConnectionToDatabase()
     */
    public void insertStock(Stock stock) {
        openConnectionToDatabase();
        String insertAssetSql = "INSERT INTO assets (id, name, price) VALUES (?, ?, ?)";
        try {
            pst = con.prepareStatement(insertAssetSql, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, stock.getAssetId());
            pst.setString(2, stock.getName());
            pst.setDouble(3, stock.getPrice());
            pst.executeUpdate();
            rs = pst.getGeneratedKeys();
            int assetId = 0;
            if (rs.next()) {
                assetId = rs.getInt(1);
            }
            String insertStockSql = "INSERT INTO stocks (name, price, num_shares, dividend_per_share, market_cap, industry_sector, asset_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pst = con.prepareStatement(insertStockSql);
            pst.setString(1, stock.getName());
            pst.setDouble(2, stock.getPrice());
            pst.setInt(3, stock.getNumShares());
            pst.setDouble(4, stock.getDividendPerShare());
            pst.setDouble(5, stock.getMarketCap());
            pst.setString(6, stock.getIndustrySector());
            pst.setInt(7, assetId);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting stock: " + e.getMessage());
        } finally {
            closeConnectionToDatabase();
        }
    }

}
