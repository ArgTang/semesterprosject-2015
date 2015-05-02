package Insurance.Vehicle;

import Insurance.Helper.PaymentOption;
import Insurance.Insurance;
import Person.Customer;
import java.time.LocalDate;

/**
 * Created by steinar on 27.03.2015.
 */
public abstract class Vehicle extends Insurance
{
    private String maker, model; //todo: maker -> maker? if so rename
    private int productionYear;

    public Vehicle(LocalDate validFrom, int itemValue, String policy, Customer customer, PaymentOption paymentOption, String maker, String model, int productionYear, int deductable)
    {
        super(validFrom, itemValue, policy, customer, paymentOption, deductable);
        this.maker = maker;
        this.model = model;
        this.productionYear = productionYear;

    }

    public String getMaker() { return maker; }
    public String getModel() { return model; }
    public int getProductionYear() { return productionYear; }
}
