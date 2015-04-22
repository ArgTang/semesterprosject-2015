package Person;

import Incident.Incident;
import Insurance.Insurance;
import Register.IncidentRegister;
import Register.InsuranceRegister;

import java.util.List;

/**
 * Created by steinar on 27.03.2015.
 */
public final class Customer extends Person
{
    private static int idCount = 00001;
    private String customerId;
    private InsuranceRegister insuranceRegister;
    private IncidentRegister incidentRegister;

    /**
     *
     * @param firstName
     * @param lastName
     * @param socialSecurityNumber
     * @param contactInfo
     */
    public Customer( String firstName, String lastName, int socialSecurityNumber, ContactInfo contactInfo )
    {
        super(firstName, lastName, socialSecurityNumber, contactInfo);
        customerId = "cust" + idCount++;
    }

    public String getCustomerId() { return customerId; }
    public InsuranceRegister getInsuranceRegister() { return insuranceRegister; }
    public IncidentRegister getIncidentRegister() { return incidentRegister; }

    /**
     *
     * @param insurance
     * @return true if added successfully
     */
    public boolean addInsurance( Insurance insurance )
    {
        //TODO: Check for null? Exceptions?
        //TODO: insuranceRegister.addInsurance(insurance);
        return false;
    }


    /**
     *
     * @param incident
     * @return true if added successfully
     */
    public boolean addIncident( Incident incident )
    {
        //TODO: Check for null? Exceptions?
        //TODO: incidentRegister.addIncident(incident);
        return false;
    }

    public String toString()
    {
        String text = super.toString();
        text += "\nCustomer ID: " + customerId;
        return text;
    }
}
