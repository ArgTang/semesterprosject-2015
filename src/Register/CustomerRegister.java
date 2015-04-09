package Register;

import Person.Customer;
import Person.Employee;
import Person.Person;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by steinar on 08.04.2015.
 */
public class CustomerRegister
{

    Map<String, Person> CustomerRegister = new HashMap();
    //todo: decide if hashmap is correct)
    //TODO: make an interface or superclass to all the registers so that all common methods can be in one place

    public void addCustomer(Customer customer)
    {
        CustomerRegister.put( customer.getCustomerId(), customer );
    }
    

}
