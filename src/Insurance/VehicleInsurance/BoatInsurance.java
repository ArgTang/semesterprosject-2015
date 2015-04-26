package Insurance.VehicleInsurance;

import Insurance.Helper.PaymentOption;
import Objects.VehicleBoat;

import java.time.LocalDate;

/**
 * Created by steinar on 27.03.2015.
 */
public final class BoatInsurance extends VehicleInsurance {


    VehicleBoat Boat;


    public BoatInsurance(LocalDate validFrom, int itemValue, String policy, PaymentOption paymentOption) {
        super(validFrom, itemValue, policy, paymentOption);
    }
}
