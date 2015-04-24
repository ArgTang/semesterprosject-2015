package Insurance;

import Insurance.Helper.PaymentOption;

import java.time.LocalDate;

/**
 * Created by steinar on 27.03.2015.
 */
public final class ValuablesInsurance extends Insurance //TODO: Change class name to something more appropriate, this class is an insurance for a specific high itemValue item. For instance a 100.000,- guitar
{
    public ValuablesInsurance(LocalDate validFrom, int itemValue, String policy, long owner, PaymentOption paymentOption) {
        super(validFrom, itemValue, policy, owner, paymentOption);
    }

    //temp constructor (for compile)
    //TODO: low priority: only if we have time to implement this
}
