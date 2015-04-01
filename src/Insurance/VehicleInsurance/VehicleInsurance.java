package Insurance.VehicleInsurance;

import Insurance.Insurance;

/**
 * Created by steinar on 27.03.2015.
 */
public abstract class VehicleInsurance extends Insurance {

    //temp constructor (for compile))
    public VehicleInsurance(double insuranceFee, double insuranseValue, String insurancePolicy) {
        super(insuranceFee, insuranseValue, insurancePolicy);
    }
}
