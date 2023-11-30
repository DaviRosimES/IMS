package entity.user;

import DAO.InvestorDAO;

public class Investor {
    //Attributes
    private String name;
    private String cpf;
    private String password;
    private String email;
    private Portfolio portfolio;

    // Constructor
    public Investor(String name, String cpf, String password, String email){
        this.cpf = cpf;
        this.name = name;
        this.password = password;
        this.email = email;

        portfolio = new Portfolio();
    }

    // Getters
    public String getCpf() {
        return cpf;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    // Methods
}
