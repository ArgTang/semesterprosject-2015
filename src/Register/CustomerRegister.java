package Register;

import Person.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by steinar on 08.04.2015.
 */
public final class CustomerRegister implements CommonRegisterMethods
{
    private final Map< String, Customer > register = new HashMap(); //todo: what key to use: socialsecuritynumber

    public void adder(Customer customer)
    {
        //return addToMap(customer.getSocialSecurityNumber(), customer, register);
        register.put(customer.getSocialSecurityNumber(), customer);
    }

    public Customer getCustomer(String key)
    {
        return (Customer)getWithKey(key, register);
    }

    //This Method is ONLY for testing purposes!
    public List<Customer> getRegister()
    {
        List list = new ArrayList<>(register.values());
        return list;
    }

}
