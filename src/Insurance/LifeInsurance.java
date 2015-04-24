package Insurance;

import Insurance.Helper.PaymentOption;
import Person.Person;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by steinar on 27.03.2015.
 */
public final class LifeInsurance extends Insurance
{

    //temp constructor (for compile)
    //TODO: this gets comlicated, lets see if we have time to do this
    List<Person> Doctor;

    public LifeInsurance(LocalDate validFrom, int itemValue, String policy, long owner, PaymentOption paymentOption) {
        super(validFrom, itemValue, policy, owner, paymentOption);
    }
}
