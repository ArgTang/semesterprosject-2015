package Register;

import Person.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by steinar on 08.04.2015.
 */
public class CustomerRegister implements CommonRegisterMethods
{
    private final Map< String, Customer > register = new HashMap(); //todo: what key to use: socialsecuritynumber

    /**
     * Adds a new customer
     * @param customer
     * @return true if customer don't already exists
     */
    public boolean addCustomer(Customer customer)
    {
        return addToMap(customer.getSocialSecurityNumber(), customer, register);
    }

    /**
     * Finds an existing customer
     * @param socialSecurityNumber
     * @return true if customer exists
     */
    public Customer getCustomer(String socialSecurityNumber)
    {
        return (Customer)getWithKey(socialSecurityNumber, register);
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

    //This Method is ONLY for testing purposes!
    public List<Customer> getRegister()
    {
        List list = new ArrayList<>(register.values());
        return list;
    }
}
