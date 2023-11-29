package entity.user;

public class Investor {
    //Attributes
    private static int id = 0;
    private int idInvestor;
    private String name;
    private String password;
    private String email;

    // Constructor
    public Investor(String name, String password, String email){
        this.idInvestor = id++;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    // Getters
    public int getIdInvestor() {
        return idInvestor;
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

    // Methods
}
