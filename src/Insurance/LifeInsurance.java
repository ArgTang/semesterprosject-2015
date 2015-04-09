package Insurance;

import Person.Person;

import java.util.List;

/**
 * Created by steinar on 27.03.2015.
 */
public final class LifeInsurance extends Insurance{

    //temp constructor (for compile)
    //TODO: this gets comlicated, lets see if we have time to do this
    List<Person> Doctor;

    public LifeInsurance(double insuranseValue, String insurancePolicy) {
        super(insuranseValue, insurancePolicy);
    }
}
