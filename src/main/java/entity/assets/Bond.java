package entity.assets;

public class Bond extends Asset{
    // Attributes
    private double interestRate;
    private int duration;
    private String issuer;
    private String creditRating;
    private int assetId;

    // Constructor
    public Bond(String name, double price, double interestRate, int duration, String issuer, String creditRating) {
        super(name, price);
        this.interestRate = interestRate;
        this.duration = duration;
        this.issuer = issuer;
        this.creditRating = creditRating;
    }

    // Getters
    public double getInterestRate() {
        return interestRate;
    }

    public int getDuration() {
        return duration;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getCreditRating() {
        return creditRating;
    }

    // Methods
}
