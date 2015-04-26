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

    public boolean addInsurance(Customer customer, Insurance insurance)
    {
        insuranceID = idCount++;
        customer.addInsuranceNumber(insuranceID);
        return addToMap(insuranceID, insurance, register);
    }

    public Object getInsurance(int insuranceID)
    {
        return getWithKey(insuranceID, register);
    }
}
