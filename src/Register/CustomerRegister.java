package Register;

import Person.Customer;
import Person.Employee;
import Person.Person;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by steinar on 08.04.2015.
 */
public final class CustomerRegister implements registerMethods
{

    Map< String, Person > register = new HashMap(); //todo: what key to use: userId or socialsecuritynumber
    //TODO: make an interface or superclass to the registers so that all common methods can be in one place, unsure how to do this.

    public void add(Customer customer)
    {
        register.put( customer.getCustomerId(), customer );

    }

}
