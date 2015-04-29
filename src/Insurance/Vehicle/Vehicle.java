package Insurance.Vehicle;

import Insurance.Helper.PaymentOption;
import Insurance.Insurance;
import Person.Customer;

import java.time.LocalDate;
import java.time.Year;

/**
 * Created by steinar on 27.03.2015.
 */
public abstract class Vehicle extends Insurance
{
    //todo: missing
    private String type, model; //todo: type -> maker? if so rename
    private Year productionYear;

    public Vehicle(LocalDate validFrom, int itemValue, String policy, Customer customer, PaymentOption paymentOption, String type, String model, Year productionYear, int deductable)
    {
        super(validFrom, itemValue, policy, customer, paymentOption, deductable);
        this.type = type;
        this.model = model;
        this.productionYear = productionYear;

    }

    public String getType() { return type; }
    public String getModel() { return model; }
    public Year getProductionYear() { return productionYear; }
}
