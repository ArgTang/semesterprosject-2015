package Insurance.Property;

import Insurance.Insurance;

/**
 * Created by steinar on 27.03.2015.
 * Base Property information
 */
public abstract class PropertyInsurance extends Insurance{


    public PropertyInsurance(double insuranseValue, String insurancePolicy) {
        super(insuranseValue, insurancePolicy);
    }
}
