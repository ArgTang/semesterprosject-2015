package Insurance.VehicleInsurance;

import Objects.Vehicle;
import Objects.VehicleBoat;

/**
 * Created by steinar on 27.03.2015.
 */
public final class BoatInsurance extends VehicleInsurance {


    VehicleBoat Boat;

    //temp constructor (for compile)

    public BoatInsurance(double insuranseValue, String insurancePolicy, Vehicle vehicle, int feet) {
        super(insuranseValue, insurancePolicy, vehicle);
    }
}
