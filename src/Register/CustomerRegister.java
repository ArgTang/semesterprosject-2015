package Register;

import Person.Customer;
import Person.Person;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by steinar on 08.04.2015.
 */
public final class CustomerRegister implements CommonRegisterMethods
{
    Map< Integer, Person > register = new HashMap(); //todo: what key to use: socialsecuritynumber

     public boolean addCustomer(Customer customer)
    {
        return add(customer.getSocialSecurityNumber(), customer, register);
    }
}
