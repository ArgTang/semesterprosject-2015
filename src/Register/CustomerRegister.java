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
    Map< Integer, Customer > register = new HashMap(); //todo: what key to use: socialsecuritynumber

     public boolean add(Customer cust)
    {
        return add(cust.getSocialSecurityNumber(), cust, register);
    }
}
