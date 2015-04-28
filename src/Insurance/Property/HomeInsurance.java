package Insurance.Property;

import Insurance.Helper.PaymentOption;

import java.time.LocalDate;

/**
 * Created by steinar on 27.03.2015.
 */
public final class HomeInsurance extends PropertyInsurance {
    public HomeInsurance(LocalDate validFrom, int itemValue, String policy, PaymentOption paymentOption) {
        super(validFrom, itemValue, policy, paymentOption);
    }
}
