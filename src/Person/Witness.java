package Person;

/**Class for storing witness contactinformation to Incidents
 *  --------  NOT IMPLEMENTET YET --------------
 * Created by steinar on 10.04.2015.
 */
public class Witness
{
    String firstName;
    String lastName;
    ContactInfo contactInfo;

    //temp Constructor for compiling
    public Witness( String firstName, String lastName, ContactInfo contactInfo ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactInfo = contactInfo;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public ContactInfo getContactInfo() { return contactInfo; }
}
