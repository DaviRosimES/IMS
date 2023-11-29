package entity.user;

public class Investor {
    //Attributes
    private String name;
    private String cpf;
    private String password;
    private String email;

    // Constructor
    public Investor(String name, String cpf, String password, String email){
        this.cpf = cpf;
        this.name = name;
        this.password = password;
        this.email = email;
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

    // Methods
}
