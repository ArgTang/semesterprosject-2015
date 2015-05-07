//Customer class for use when customers registers or for info
package Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by steinar on 27.03.2015.
 */
public final class Customer extends Person
{
    private static int idCount = 00001;
    private String customerId;
    private List<Integer> insuranceNumbers;
    private List<Integer> incidentNumbers;

    public Customer( String firstName, String lastName, String socialSecurityNumber, ContactInfo contactInfo ) {
        super(firstName, lastName, socialSecurityNumber, contactInfo);
        customerId = "cust" + idCount++;
        insuranceNumbers = new ArrayList<Integer>();
        incidentNumbers = new ArrayList<Integer>();
    }

    public String getCustomerId() { return customerId; }
    public void addInsuranceNumber( int nr ) { insuranceNumbers.add( nr ); }
    public void addIncidentNumber( int nr ) { incidentNumbers.add( nr ); }
    public List<Integer> getInsuranceNumbers() { return insuranceNumbers; }
    public List<Integer> getIncidentNumbers() { return incidentNumbers; }
}