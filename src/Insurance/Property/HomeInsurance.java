package Insurance.Property;

import Insurance.Helper.PaymentOption;
import Person.Customer;

import java.time.LocalDate;

/**
 * Created by steinar on 27.03.2015.
 */
public final class HomeInsurance extends PropertyInsurance {
    public HomeInsurance(LocalDate validFrom, int itemValue, String policy, Customer customer, PaymentOption paymentOption, int deductable) {
        super(validFrom, itemValue, policy, customer, paymentOption, deductable);
    }
}
