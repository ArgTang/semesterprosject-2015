package Insurance.VehicleInsurance;

import Insurance.Helper.PaymentOption;

import java.time.LocalDate;

/**
 * Created by steinar on 07.04.2015.
 */
public final class MotorBike extends VehicleInsurance
{
    public MotorBike(LocalDate validFrom, int itemValue, String policy, PaymentOption paymentOption) {
        super(validFrom, itemValue, policy, paymentOption);
    }
}
