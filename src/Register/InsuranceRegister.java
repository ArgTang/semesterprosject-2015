package Register;

import Insurance.Insurance;
import Person.Customer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by steinar on 08.04.2015.
 */
public class InsuranceRegister  implements CommonRegisterMethods
{
    private static int idCount = 1;
    private int insuranceID;

    Map< Integer, Insurance > register = new HashMap();

    /**
     * Adds an Insurance to a customer
     * @param customer
     * @param insurance
     * @return true if insurance don't already exists
     */
    public boolean addInsurance(Customer customer, Insurance insurance)
    {
        insuranceID = idCount++;
        customer.addInsuranceNumber(insuranceID);
        return add(insuranceID, insurance, register);
    }

    /**
     * Finds an existing insurance
     * @param insuranceID
     * @return true if insurance exists
     */
    public Object getInsurance(int insuranceID)
    {
        return getWithKey(insuranceID, register);
    }

    /**
     * Updates an existing insurance
     * @param insuranceID
     * @param insurance
     * @return true if insurance exists
     */
    public boolean updateInsurance(int insuranceID, Insurance insurance)
    {
        return update(insuranceID, insurance, register);
    }
}
