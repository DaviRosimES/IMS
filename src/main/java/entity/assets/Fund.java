package entity.assets;

public class Fund extends Asset{
    // Attributes
    private String type;
    private double managementFee;
    private String manager;
    private double historicalPerformance;

    // Constructor
    public Fund(String name, double price, String type, double managementFee, String manager, double historicalPerformance){
        super(name, price);
        this.type = type;
        this.managementFee = managementFee;
        this.manager = manager;
        this.historicalPerformance = historicalPerformance;
    }

    // Getters
    public String getType() {
        return type;
    }

    public double getManagementFee() {
        return managementFee;
    }

    public String getManager() {
        return manager;
    }

    public double getHistoricalPerformance() {
        return historicalPerformance;
    }


    // Methods
}
