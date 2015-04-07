package Insurance.VehicleInsurance;

import Insurance.Insurance;

/**
 * Created by steinar on 27.03.2015.
 */
public abstract class VehicleInsurance extends Insurance {

    int registeredKM;
    int horsePower;
    String make;
    String model;
    String color; //todo: maybe enum?
    String registrationNumber; //todo: are all boats registered, Or are registration prereq to get insurance?;
    byte bonus; //todo: maybe enum ( steps of 10)  ?


    //temp constructor (for compile))
    public VehicleInsurance(double insuranseValue, String insurancePolicy, int registeredKM) {
        super(insuranseValue, insurancePolicy);
        this.registeredKM = registeredKM;
    }
}
