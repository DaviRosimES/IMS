import DAO.*;
import entity.assets.Asset;
import entity.assets.Bond;
import entity.assets.Fund;
import entity.assets.Stock;
import entity.user.Investor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Investor investor = null;

        while (true) {
            System.out.println("---- Menu ----");
            System.out.println("1. Logar como investidor");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1){
                System.out.print("Nome do Investidor: ");
                String name = scanner.nextLine();
                System.out.print("CPF do Investidor: ");
                String cpf = scanner.nextLine();
                System.out.print("Senha do Investidor: ");
                String password = scanner.nextLine();
                System.out.print("Email do Investidor: ");
                String email = scanner.nextLine();

                investor = new Investor(name, cpf, password, email);
                InvestorDAO investorDAO = new InvestorDAO();
                investorDAO.insertInvestor(investor);
                investor.getPortfolio().addPortfolio(investor.getCpf());
                while (true) {
                    System.out.println("---- Menu ----");
                    System.out.println("1. Adicionar ativo");
                    System.out.println("2. Mostrar todos os ativos");
                    System.out.println("3. Atualizar um ativo");
                    System.out.println("4. Remover um ativo");
                    System.out.println("0. Sair");
                    System.out.print("Escolha uma opção: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();

                    PortfolioAssetDAO portfolioAssetDAO = new PortfolioAssetDAO();

                    switch (choice) {
                        case 1:
                            System.out.println("Escolha o tipo de ativo:");
                            System.out.println("1. Stock");
                            System.out.println("2. Bond");
                            System.out.println("3. Fund");
                            System.out.print("Escolha uma opção: ");
                            int assetTypeChoice = scanner.nextInt();
                            scanner.nextLine();

                            switch (assetTypeChoice) {
                                case 1:
                                    Stock stock = null;
                                    System.out.print("Nome da ação: ");
                                    String stockName = scanner.nextLine();
                                    System.out.print("Preço da ação: ");
                                    double stockPrice = scanner.nextDouble();
                                    scanner.nextLine();
                                    System.out.println("Número de ações: ");
                                    int numShares = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.println("Dividendo por ação: ");
                                    double dividendPerShare = scanner.nextDouble();
                                    scanner.nextLine();
                                    System.out.println("Valor de mercado da ação: ");
                                    double marketCap = scanner.nextDouble();
                                    scanner.nextLine();
                                    System.out.println("Setor da indústria: ");
                                    String industrySector = scanner.nextLine();
                                    stock = new Stock(stockName, stockPrice, numShares, dividendPerShare, marketCap, industrySector);
                                    StockDAO stockDAO = new StockDAO();
                                    stockDAO.insertStock(stock);
                                    portfolioAssetDAO.addAssetToPortfolio(investor.getPortfolio(), stock);
                                    System.out.println("Ativo adicionado com sucesso!");
                                    break;
                                case 2:
                                    Bond bond = null;
                                    System.out.print("Nome do título: ");
                                    String bondName = scanner.nextLine();
                                    System.out.print("Preço do título: ");
                                    double bondPrice = scanner.nextDouble();
                                    scanner.nextLine();
                                    System.out.println("Taxa de rendimento: ");
                                    double interestRate = scanner.nextDouble();
                                    scanner.nextLine();
                                    System.out.println("Vencimento: ");
                                    int duration = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.println("Emissor do título: ");
                                    String issuer = scanner.nextLine();
                                    System.out.println("Análise de crédito: ");
                                    String creditRating = scanner.nextLine();
                                    bond = new Bond(bondName, bondPrice, interestRate, duration, issuer, creditRating);
                                    BondDAO bondDAO = new BondDAO();
                                    bondDAO.insertBond(bond);
                                    portfolioAssetDAO.addAssetToPortfolio(investor.getPortfolio(), bond);
                                    System.out.println("Ativo adicionado com sucesso!");
                                    break;
                                case 3:
                                    // Adicionar um fundo (Fund)
                                    Fund fund = null;
                                    System.out.print("Nome do fundo: ");
                                    String fundName = scanner.nextLine();
                                    System.out.print("Preço do fundo: ");
                                    double fundPrice = scanner.nextDouble();
                                    System.out.println("Tipo do fundo: ");
                                    String type = scanner.nextLine();
                                    System.out.println("Taxa de administração: ");
                                    double managementFee = scanner.nextDouble();
                                    scanner.nextLine();
                                    System.out.println("Administrador do fundo: ");
                                    String manager = scanner.nextLine();
                                    System.out.println("Performance histórica: ");
                                    double historicalPerformance = scanner.nextDouble();
                                    scanner.nextLine();
                                    fund = new Fund(fundName, fundPrice, type, managementFee, manager, historicalPerformance);
                                    FundDAO fundDAO = new FundDAO();
                                    fundDAO.insertFund(fund);
                                    portfolioAssetDAO.addAssetToPortfolio(investor.getPortfolio(), fund);
                                    System.out.println("Ativo adicionado com sucesso!");
                                    break;
                                default:
                                    System.out.println("Opção inválida. Tente novamente.");
                            }
                            break;
                        case 2:
                            portfolioAssetDAO.displayAllAssetsFromPortfolio(investor.getPortfolio());
                            break;
                        case 3:
                            System.out.println("Digite o id do ativo que você quer atualizar: ");
                            int assetIdToUpdate = scanner.nextInt();
                            scanner.nextLine();

                            AssetDAO updateAssetDAO = new AssetDAO();
                            Asset assetToUpdate = updateAssetDAO.getAssetById(assetIdToUpdate);

                            System.out.println("Digite o valor a ser atualizado: ");
                            double newPrice = scanner.nextDouble();
                            scanner.nextLine();
                            assetToUpdate.setPrice(newPrice);

                            updateAssetDAO.updateAsset(assetToUpdate);
                            //portfolioAssetDAO.updateAssetInPortfolio(investor.getPortfolio(), assetToUpdate);
                            System.out.println("Ativo atualizado!");
                            break;
                        case 4:
                            System.out.println("Digite o id do ativo que você quer remover: ");
                            int assetIdToRemove = scanner.nextInt();
                            scanner.nextLine();

                            AssetDAO removeAssetDAO = new AssetDAO();
                            Asset assetToRemove = removeAssetDAO.getAssetById(assetIdToRemove);

                            portfolioAssetDAO.removeAssetFromPortfolio(investor.getPortfolio(), assetToRemove);
                            break;
                        case 0:
                            System.out.println("Saindo do programa.");
                            System.exit(0);
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                    }
                }

            } else if(choice == 0){
                System.out.println("Saindo do programa.");
                scanner.close();
                System.exit(0);
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}


