package Insurance.VehicleInsurance;

import Objects.Vehicle;

/**
 * Created by steinar on 27.03.2015.
 */
public final class BoatInsurance extends VehicleInsurance {

    //int feet; //todo: boat class?

    //temp constructor (for compile)

    public BoatInsurance(double insuranseValue, String insurancePolicy, Vehicle vehicle, int feet) {
        super(insuranseValue, insurancePolicy, vehicle);
       // this.feet = feet;
    }
}
