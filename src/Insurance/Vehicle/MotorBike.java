package Insurance.Vehicle;

import Insurance.Helper.PaymentOption;
import Person.Customer;

import java.time.LocalDate;

/**
 * Created by steinar on 07.04.2015.
 */
public final class MotorBike extends VehicleInsurance {

    private int kilometer, horsePower;
    byte bonus; //todo: maybe enum ( steps of 10)  ?
    private String registrationnumber;
    private String color;

    public MotorBike(LocalDate validfrom, int itemValue, String insurancePolicy, Customer customer, PaymentOption paymentOption, String type, String model,
                     int productionYear, int kilometer, int horsePower, String registrationnumber, String color, int deductable)
    {
        super(validfrom, itemValue, insurancePolicy, customer, paymentOption, type, model, productionYear, deductable);
        this.kilometer = kilometer;
        this.horsePower = horsePower;
        this.registrationnumber = registrationnumber;
        this.color = color;
    }

    public int getKilometer() { return kilometer; }
    public int getHorsePower() { return horsePower; }
}
