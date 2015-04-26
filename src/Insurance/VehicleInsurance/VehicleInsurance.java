package Insurance.VehicleInsurance;

import Insurance.Helper.PaymentOption;
import Insurance.Insurance;
import Objects.Vehicle;

import java.time.LocalDate;

/**
 * Created by steinar on 27.03.2015.
 */
public abstract class VehicleInsurance extends Insurance {

    Vehicle vehicle;
    byte bonus; //todo: maybe enum ( steps of 10)  ?


    public VehicleInsurance(LocalDate validFrom, int itemValue, String policy, PaymentOption paymentOption)
    {
        super(validFrom, itemValue, policy, paymentOption);
    }
}
