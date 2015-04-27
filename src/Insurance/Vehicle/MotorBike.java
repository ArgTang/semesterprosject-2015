package Insurance.Vehicle;

import java.time.Year;

/**
 * Created by steinar on 07.04.2015.
 */
public final class MotorBike extends Vehicle {

    private int kilometer, horsePower;
    byte bonus; //todo: maybe enum ( steps of 10)  ?

    public MotorBike(double insuranseValue, String insurancePolicy, String type, String model, Year productionYear, int kilometer, int horsePower)
    {
        super(insuranseValue, insurancePolicy, type, model, productionYear);
        this.kilometer = kilometer;
        this.horsePower = horsePower;
    }

    public int getKilometer() { return kilometer; }
    public int getHorsePower() { return horsePower; }
}
