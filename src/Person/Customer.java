package Person;

import Register.InsuranceRegister;
import Register.IncidentRegister;
import Insurance.Insurance;

import java.util.List;

/**
 * Created by steinar on 27.03.2015.
 */
public final class Customer extends Person
{
    private static int idCount = 00001;
    private String customerId;
    private List<String> insuranceRegister; //todo: bruke registeret i Personklassen? kjekt med historikk for ansatte også.
    private List<String> incidentRegister;  //todo: se over.

    /**
     *
     * @param firstName
     * @param lastName
     * @param socialSecurityNumber
     * @param contactInfo
     */
    public Customer(String firstName, String lastName, String socialSecurityNumber, ContactInfo contactInfo)
    {
        super(firstName, lastName, socialSecurityNumber, contactInfo);
        customerId = "cust" + idCount++;
    }

    public String getCustomerId() { return customerId; }

    public void addInsurance(Insurance insurance)
    {
        //insuranceRegister.addInsurance(insurance);
    }
}
