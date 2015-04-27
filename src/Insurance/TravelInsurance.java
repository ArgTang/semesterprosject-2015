package Insurance;

import Insurance.Helper.PaymentOption;

import java.time.LocalDate;

/**
 * Created by steinar on 27.03.2015.
 */
public final class TravelInsurance extends Insurance {

    PaymentOption insuranceDuration;
    PaymentOption yearlyPremium;


    public TravelInsurance(LocalDate validFrom, String policy, PaymentOption paymentOption)
    {
        super(validFrom, 0, policy, paymentOption);
    }
}
