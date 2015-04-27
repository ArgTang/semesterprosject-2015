package Insurance.Vehicle;

import Insurance.Insurance;

import java.time.Year;

/**
 * Created by steinar on 27.03.2015.
 */
public abstract class Vehicle extends Insurance
{

    private String type, model;
    private Year productionYear;

    public Vehicle(double value, String policy, String type, String model, Year productionYear)
    {
        super(value, policy);
        this.type = type;
        this.model = model;
        this.productionYear = productionYear;
    }

    public String getType() { return type; }
    public String getModel() { return model; }
    public Year getProductionYear() { return productionYear; }
}
