package Insurance.VehicleInsurance;
import Insurance.Helper.PaymentOption;
import Objects.Vehicle;

import java.time.LocalDate;

/**
 * Created by steinar on 27.03.2015.
 */
public final class CarInsurance extends VehicleInsurance {

    Vehicle vehicle;

    public CarInsurance(LocalDate validFrom, int itemValue, String policy, PaymentOption paymentOption) {
        super(validFrom, itemValue, policy, paymentOption);
    }
}