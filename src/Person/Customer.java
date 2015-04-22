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
        insuranceNumbers = new ArrayList<Integer>();
        incidentNumbers = new ArrayList<Integer>();
    }

    /**
     *
     * @return String element
     */
    public String getCustomerId() { return customerId; }
    /**
     * Add number to list
     * @param nr
     */
    public void addInsuranceNumber( int nr ) { insuranceNumbers.add( nr ); }
    /**
     * Add number to list
     * @param nr
     */
    public void addIncidentNumber( int nr ) { incidentNumbers.add( nr ); }
    /**
     *
     * @return a List<Integer> object - ArrayList
     */
    public List<Integer> getInsuranceNumbers() { return insuranceNumbers; }
    /**
     *
     * @return a List<Integer> object - ArrayList
     */
    public List<Integer> getIncidentNumbers() { return incidentNumbers; }

    /**
     * Info about customer
     * @return a String
     */
    public String toString()
    {
        String text = super.toString();
        text += "\nCustomer ID: " + customerId;
        return text;
    }
}
