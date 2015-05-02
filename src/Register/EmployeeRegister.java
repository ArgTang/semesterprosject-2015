package Register;

import Person.Employee;
import Person.Person;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by steinar on 09.04.2015.
 */
@Deprecated
public class EmployeeRegister  implements CommonRegisterMethods
{
    Map< String, Person > register = new HashMap();

    /**
     * Adds a new employee
     * @param employee
     * @return true if employee don't already exists
     */
    public boolean addEmployee(Employee employee)
    {
        return addToMap(employee.getEmployeeId(), employee, register);
    }

    /**
     * Finds an existing employee
     * @param employeeId
     * @return true if employee exists
     */
    public Object getEmployee(String employeeId)
    {
        return getWithKey(employeeId, register);
    }

    /**
     * Updates an existing employee
     * @param employee
     * @return true if employee exists
     */
    public boolean updateEmployee(Employee employee)
    {
        return update(employee.getEmployeeId(), employee, register);
    }
}
