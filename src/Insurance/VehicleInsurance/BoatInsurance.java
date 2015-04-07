package Insurance.VehicleInsurance;

import Insurance.Insurance;

/**
 * Created by steinar on 27.03.2015.
 */
public final class BoatInsurance extends VehicleInsurance {

    int feet;

    //temp constructor (for compile)
    public BoatInsurance(double insuranseValue, String insurancePolicy, int registeredKM) {
        super(insuranseValue, insurancePolicy, registeredKM);
    }
}
