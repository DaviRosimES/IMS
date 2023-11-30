package DAO;

import entity.assets.Asset;
import entity.user.Portfolio;

import java.sql.SQLException;

public class PortfolioAssetDAO extends ConnectionDAO{
    /**
     * Adiciona um ativo ao portfólio no banco de dados.
     * Abre uma conexão com o banco, prepara e executa uma instrução SQL INSERT
     * e depois fecha a conexão com o banco de dados.
     *
     * @param portfolio O objeto Portfolio ao qual o ativo será adicionado.
     * @param asset     O ativo a ser adicionado ao portfólio.
     * @see #openConnectionToDatabase()
     * @see #closeConnectionToDatabase()
     */
    public void addAssetToPortfolio(Portfolio portfolio, Asset asset) {
        openConnectionToDatabase();
        String sql = "INSERT INTO portfolio_has_assets (portfolio_id, asset_id) VALUES (?, ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, portfolio.getPortfolioId());
            pst.setInt(2, asset.getAssetId());
            portfolio.updateTotalBalance(asset);
            PortfolioDAO portfolioDAO = new PortfolioDAO();
            portfolioDAO.updatePortfolio(portfolio);
            pst.execute();
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar ativo ao portfólio: " + e.getMessage());
        } finally {
            closeConnectionToDatabase();
        }
    }

    /**
     * Atualiza as informações de um ativo associado a um portfólio.
     *
     * @param portfolio O portfólio ao qual o ativo está associado.
     * @param asset     O ativo a ser atualizado.
     * @see #openConnectionToDatabase()
     * @see #closeConnectionToDatabase()
     */
    public void updateAssetInPortfolio(Portfolio portfolio, Asset asset) {
        openConnectionToDatabase();
        String sql = "UPDATE portfolio_has_assets SET asset_id = ? WHERE portfolio_id = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, asset.getAssetId());
            pst.setInt(2, portfolio.getPortfolioId());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar ativo no portfólio: " + e.getMessage());
        } finally {
            closeConnectionToDatabase();
        }
    }

    /**
     * Exibe informações de todos os ativos associados a um portfólio.
     *
     * @param portfolio O portfólio para o qual exibir os ativos.
     * @see #openConnectionToDatabase()
     * @see #closeConnectionToDatabase()
     */
    public void displayAllAssetsFromPortfolio(Portfolio portfolio) {
        openConnectionToDatabase();
        String sql = "SELECT asset_id FROM portfolio_has_assets WHERE portfolio_id = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, portfolio.getPortfolioId());
            rs = pst.executeQuery();
            while (rs.next()) {
                int assetId = rs.getInt("asset_id");
                displayAssetDetails(assetId);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao exibir ativos do portfólio: " + e.getMessage());
        } finally {
            closeConnectionToDatabase();
        }
    }

    /**
     * Remove um ativo do portfólio no banco de dados.
     *
     * @param portfolio O portfólio do qual remover o ativo.
     * @param asset     O ativo a ser removido.
     * @see #openConnectionToDatabase()
     * @see #closeConnectionToDatabase()
     */
    public void removeAssetFromPortfolio(Portfolio portfolio, Asset asset) {
        openConnectionToDatabase();
        String sql = "DELETE FROM portfolio_has_assets WHERE portfolio_id = ? AND asset_id = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, portfolio.getPortfolioId());
            pst.setInt(2, asset.getAssetId());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao remover ativo do portfólio: " + e.getMessage());
        } finally {
            closeConnectionToDatabase();
        }
    }


    /**
     * Exibe informações de um ativo com base no ID.
     *
     * @param assetId O ID do ativo.
     */
    private void displayAssetDetails(int assetId) {
        AssetDAO assetDAO = new AssetDAO();
        Asset asset = assetDAO.getAssetById(assetId);

        if (asset != null) {
            System.out.println("Detalhes do Ativo com ID " + assetId + ":");
            System.out.println("Nome: " + asset.getName());
            System.out.println("Preço: " + asset.getPrice());
        } else {
            System.out.println("Ativo com ID " + assetId + " não encontrado.");
        }
    }
}
