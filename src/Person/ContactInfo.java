package Person;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by nugGet on 09.04.2015.
 */
public class ContactInfo
{
    private String address;
    private String city; //TODO: Add a class containing every postal codes in Norway
    private String email;
    Set<Integer> phoneNumbers = new HashSet<Integer>(); //TODO: is this needed?

    /**
     *
     * @param address
     * @param email
     * @param city
     * @param phone
     */
    public ContactInfo( String address, String email, String city, int phone )
    {
        this.address = address;
        this.email = email;
        this.city = city;
        phoneNumbers.add(phone);
    }

    public String getAddress() { return address; }
    public String getCity() { return city; }
    public String getEmail() { return email; }
    public Set<Integer> getPhoneNumbers() { return phoneNumbers; }

    public String toString()
    {
        String text = address + ", " + city;
        text += "\n" + email;
        text += "\nPhone number(s): " + phoneNumbers;
        return text;
    }

}
