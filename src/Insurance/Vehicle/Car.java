package Insurance.Vehicle;


import Insurance.Helper.PaymentOption;
import java.time.LocalDate;
import java.time.Year;

/**
 * Created by steinar on 27.03.2015.
 */
public final class Car extends Vehicle {

    private int kilometer, horsePower;
    byte bonus; //todo: maybe arraylist -> observable list see Agentgui.insurance.CarModuleController
    private String licenceNumber;
    private String color;

    public Car(LocalDate validfrom, int itemValue, String insurancePolicy, PaymentOption paymentOption, String type, String model,
               Year productionYear, int kilometer, int horsePower, String licenceNumber, String color)
    {
        super(validfrom, itemValue, insurancePolicy, paymentOption, type, model, productionYear);
        this.kilometer = kilometer;
        this.horsePower = horsePower;
        this.color = color;
    }

    public int getKilometer() { return kilometer; }
    public int getHorsePower() { return horsePower; }
}
