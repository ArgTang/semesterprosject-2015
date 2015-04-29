package Insurance.Property;


import Insurance.Helper.PaymentOption;
import Insurance.Insurance;
import Objects.Property;
import Person.Customer;

import java.time.LocalDate;


/**
 * Created by steinar on 27.03.2015.
 * Base Property information
 */
public abstract class PropertyInsurance extends Insurance
{
    Property property;

    public PropertyInsurance(LocalDate validFrom, int itemValue, String policy, Customer customer, PaymentOption paymentOption, int deductable)
    {
        super(validFrom, itemValue, policy, customer, paymentOption, deductable);
    }
}
