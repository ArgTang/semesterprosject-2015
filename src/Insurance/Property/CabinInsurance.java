package Insurance.Property;

import Insurance.Helper.PaymentOption;

import java.time.LocalDate;

/**
 * Created by steinar on 27.03.2015.
 */
public final class CabinInsurance extends PropertyInsurance {
    public CabinInsurance(LocalDate validFrom, int itemValue, String policy, long owner, PaymentOption paymentOption) {
        super(validFrom, itemValue, policy, owner, paymentOption);
    }

    //temp constructor (for compile)

}
