package Insurance.Vehicle;


import Insurance.Helper.PaymentOption;
import Person.Customer;

import java.time.LocalDate;
import java.time.Year;

/**
 * Created by steinar on 27.03.2015.
 */
public final class CarInsurance extends Vehicle {

    private int kilometer, horsePower;
    byte bonus; //todo: maybe arraylist -> observable list see Agentgui.insurance.CarModuleController
    private String licenceNumber;
    private String color;

    public CarInsurance(LocalDate validfrom, int itemValue, String insurancePolicy, Customer customer, PaymentOption paymentOption, String maker, String model,
                        int productionYear, int kilometer, int horsePower, String licenceNumber, String color, int deductable)
    {
        super(validfrom, itemValue, insurancePolicy, customer, paymentOption, maker, model, productionYear, deductable);
        this.kilometer = kilometer;
        this.horsePower = horsePower;
        this.color = color;
    }

    public int getKilometer() { return kilometer; }
    public int getHorsePower() { return horsePower; }
}
