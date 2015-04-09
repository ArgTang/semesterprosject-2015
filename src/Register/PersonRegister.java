package Register;

import Person.Customer;
import Person.Employee;
import Person.Person;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by steinar on 08.04.2015.
 */
public class PersonRegister
{

    Map<String, Person> personRegister = new HashMap();
    //todo: decide if hashmap is correct)
    //TODO: make an interface or superclass to all the registers so that all common methods can be in one place

    public void addCustomer(Customer customer)
    {
        personRegister.put( customer.getCustomerId(), customer );
    }

    public void addEmployee(Employee employee) //todo merge add methods?
    {
        personRegister.put( employee.getEmployeeId(), employee );
    }

}
