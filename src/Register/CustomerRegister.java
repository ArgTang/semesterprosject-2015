package Register;

import Person.Customer;
import Person.Employee;
import Person.Person;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by steinar on 08.04.2015.
 */
public final class CustomerRegister
{

    Map< String, Person > register = new HashMap(); //todo: decide if hashmap is correct, AND on what key to use

    //TODO: make an interface or superclass to all the registers so that all common methods can be in one place

    public void addCustomer(Customer customer)
    {
        register.put( customer.getCustomerId(), customer );
    }


}
