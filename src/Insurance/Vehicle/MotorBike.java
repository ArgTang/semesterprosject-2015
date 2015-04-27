package Insurance.Vehicle;

import Insurance.Helper.PaymentOption;

import java.time.LocalDate;
import java.time.Year;

/**
 * Created by steinar on 07.04.2015.
 */
public final class MotorBike extends Vehicle {

    private int kilometer, horsePower;
    byte bonus; //todo: maybe enum ( steps of 10)  ?
    private String registrationnumber;
    private String color;

    public MotorBike(LocalDate validfrom, int itemValue, String insurancePolicy, PaymentOption paymentOption, String type, String model,
                     Year productionYear, int kilometer, int horsePower, String registrationnumber, String color)
    {
        super(validfrom, itemValue, insurancePolicy, paymentOption, type, model, productionYear);
        this.kilometer = kilometer;
        this.horsePower = horsePower;
        this.registrationnumber = registrationnumber;
        this.color = color;
    }

    public int getKilometer() { return kilometer; }
    public int getHorsePower() { return horsePower; }
}
