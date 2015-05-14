package Insurance;

import Insurance.Helper.PaymentOption;
import Person.Customer;

import java.time.LocalDate;

/** Class for Travelinsurance
 * Created by steinar on 27.03.2015.
 */
public final class TravelInsurance extends Insurance
{
    boolean travelPluss;
    String owner;

    public TravelInsurance(LocalDate validFrom, String policy, Customer customer, PaymentOption paymentOption, boolean travelPluss) {
        super(validFrom, 0, policy, customer, paymentOption, 0);
        this.travelPluss = travelPluss;
        owner = customer.getCustomerId();
        premiumCalculation();
    }

    public boolean isTravelPluss() {
        return travelPluss;
    }

    private void premiumCalculation() {
        int basePremium = 1200;
        int plusPremuim = 500;
        super.setAnnualPremium( basePremium + plusPremuim *(travelPluss ? 1:0) );
    }
}