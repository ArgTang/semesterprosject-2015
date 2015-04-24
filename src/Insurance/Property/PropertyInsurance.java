package Insurance.Property;

import Insurance.Helper.PaymentOption;
import Insurance.Insurance;
import Objects.Property;

import java.time.LocalDate;

/**
 * Created by steinar on 27.03.2015.
 * Base Property information
 */
public abstract class PropertyInsurance extends Insurance{

    Property property;

    public PropertyInsurance(LocalDate validFrom, int itemValue, String policy, long owner, PaymentOption paymentOption) {
        super(validFrom, itemValue, policy, owner, paymentOption);
    }
}
