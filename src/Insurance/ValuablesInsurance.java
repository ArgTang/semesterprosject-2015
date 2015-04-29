package Insurance;

import Insurance.Helper.PaymentOption;
import Person.Customer;

import java.time.LocalDate;

/**
 * Created by steinar on 27.03.2015.
 */
public final class ValuablesInsurance extends Insurance //TODO: Change class name to something more appropriate, this class is an insurance for a specific high itemValue item. For instance a 100.000,- guitar
{
    public ValuablesInsurance(LocalDate validFrom, int itemValue, String policy, Customer customer, PaymentOption paymentOption) {
        super(validFrom, itemValue, policy, customer, paymentOption, 0);
    }


    //temp constructor (for compile)
    //TODO: low priority: only if we have time to implement this
}