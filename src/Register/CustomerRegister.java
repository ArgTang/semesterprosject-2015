package Register;

import Person.Customer;
import Person.Person;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by steinar on 08.04.2015.
 */
public class CustomerRegister implements CommonRegisterMethods
{
    Map< Integer, Person > register = new HashMap(); //todo: what key to use: socialsecuritynumber

    /**
     * Adds a new customer
     * @param customer
     * @return true if customer don't already exists
     */
    public boolean addCustomer(Customer customer)
    {
        return add(customer.getSocialSecurityNumber(), customer, register);
    }

    /**
     * Finds an existing customer
     * @param socialSecurityNumber
     * @return true if customer exists
     */
    public Object getCustomer(int socialSecurityNumber)
    {
        return getWithKey(socialSecurityNumber, register);
    }

    /**
     * Updates an existing customer
     * @param customer
     * @return true if customer exists
     */
    public boolean updateCustomer(Customer customer)
    {
        return update(customer.getSocialSecurityNumber(), customer, register);
    }
}
