package Insurance.Property;

import Insurance.Insurance;
import Objects.Property;

/**
 * Created by steinar on 27.03.2015.
 * Base Property information
 */
public abstract class PropertyInsurance extends Insurance{

    Property property;

    //temp constructor (for compile)

    public PropertyInsurance(double insuranseValue, String insurancePolicy) {
        super(insuranseValue, insurancePolicy);
    }
}
