package Person;

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
    public Customer(String firstName, String lastName, int socialSecurityNumber, ContactInfo contactInfo)
    {
        super(firstName, lastName, socialSecurityNumber, contactInfo);
        customerId = "cust" + idCount++;
    }

    public int getCustomerId() { return super.getSocialSecurityNumber(); }

    public void addInsurance(Insurance insurance)
    {
        //insuranceRegister.add(insurance.getKey()); //todo: decide what insurancekey is andd how to get it
    }
}
