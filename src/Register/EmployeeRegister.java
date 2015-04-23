package Register;

import Person.Person;
import Person.Employee;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by steinar on 09.04.2015.
 */
public class EmployeeRegister  implements CommonRegisterMethods
{

    Map< String, Person > register = new HashMap();

    public boolean add(Employee emp)
    {
        return add(emp.getEmployeeId(), emp, register);
    }
}
