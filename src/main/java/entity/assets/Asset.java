package entity.assets;

import DAO.AssetDAO;

public class Asset {
    // Attributes
    private static int id = 1;
    private int assetId;
    private String name;
    private double price;

    // Constructor
    public Asset(String name, double price){
        this.name = name;
        this.price = price;
        this.assetId = id++;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAssetId() {
        return assetId;
    }

    public void setPrice(double price){
        this.price = price;
    }

    // Methods
}
