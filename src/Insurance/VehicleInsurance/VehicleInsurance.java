package Insurance.VehicleInsurance;

import Insurance.Insurance;
import Objects.Vehicle;

/**
 * Created by steinar on 27.03.2015.
 */
public abstract class VehicleInsurance extends Insurance {

    Vehicle vehicle;
    byte bonus; //todo: maybe enum ( steps of 10)  ?


    //temp constructor (for compile))

    public VehicleInsurance(double insuranseValue, String insurancePolicy, Vehicle vehicle) {
        super(insuranseValue, insurancePolicy);
        this.vehicle = vehicle;
    }
}
