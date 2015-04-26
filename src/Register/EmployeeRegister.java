package Register;

import Person.Employee;
import Person.Person;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by steinar on 09.04.2015.
 */
public class EmployeeRegister  implements CommonRegisterMethods
{
    Map< String, Person > register = new HashMap();

    public boolean addEmployee(Employee employee)
    {
        return addToMap(employee.getEmployeeId(), employee, register);
    }

    public Object getEmployee(String employeeId)
    {
        return getWithKey(employeeId, register);
    }
}
