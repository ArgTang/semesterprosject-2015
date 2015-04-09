package Insurance;

import Insurance.Helper.PaymentOption;

/**
 * Created by steinar on 27.03.2015.
 */
public final class TravelInsurance extends Insurance {

    PaymentOption insuranceDuration;
    PaymentOption yearlyPremium;

    //temp constructor (for compile)
    public TravelInsurance(double insuranseValue, String insurancePolicy) {
        super(insuranseValue, insurancePolicy);
    }
}
