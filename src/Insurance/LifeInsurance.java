package Insurance;

import Insurance.Helper.PaymentOption;
import Person.Customer;

import java.time.LocalDate;

/**
 * Created by steinar on 27.03.2015.
 */
public final class LifeInsurance extends Insurance
{

    //temp constructor (for compile)
    //TODO: this gets comlicated, lets see if we have time to do this
    //List<Person> Doctor;


    public LifeInsurance(LocalDate validFrom, int itemValue, String policy, Customer customer, PaymentOption paymentOption) {
        super(validFrom, itemValue, policy, customer, paymentOption, 0);
    }
}
