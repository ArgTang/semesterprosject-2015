package Insurance.Vehicle;


import java.time.Year;

/**
 * Created by steinar on 27.03.2015.
 */
public final class Car extends Vehicle {

    private int kilometer, horsePower;
    byte bonus; //todo: maybe enum ( steps of 10)  ?

    public Car(double value, String policy, String type, String model, Year productionYear, int kilometer, int horsePower)
    {
        super(value, policy, type, model, productionYear);
        this.kilometer = kilometer;
        this.horsePower = horsePower;
    }

    public int getKilometer() { return kilometer; }
    public int getHorsePower() { return horsePower; }
}
