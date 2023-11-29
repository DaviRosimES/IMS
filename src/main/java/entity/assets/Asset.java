package entity.assets;

public class Asset {
    // Attributes
    private String name;
    private double price;

    // Constructor
    public Asset(String name, double price){
        this.name = name;
        this.price = price;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }


    // Methods
}
