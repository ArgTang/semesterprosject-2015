package Person;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**Class for storing base info for each Person
 * Created by nugGet on 09.04.2015.
 */
public class ContactInfo implements Serializable
{
    private static final long serialVersionUID = 6526322295622776147L;
    private String address;
    private int citynumber;
    private String city; //TODO: Add a class containing every postal codes in Norway
    private String email;
    Set<Integer> phoneNumbers = new HashSet<Integer>(); //TODO: is this needed?

    private ContactInfo(String address, int citynumber, String city, String email) {
        this.address = address;
        this.email = email;
        this.city = city;
        this.citynumber = citynumber;
    }

    public ContactInfo(String address, int citynumber, String city, String email, int phone) {
        this(address, citynumber, city, email);
        phoneNumbers.add(phone);
    }

    public ContactInfo(String address, int citynumber, String city, String email, List<Integer> phoneNumbers) {
        this(address, citynumber, city, email);
        this.phoneNumbers.addAll(phoneNumbers);
    }

    public String getAddress() { return address; }
    public String getCity() { return city; }
    public String getEmail() { return email; }
    public int getCitynumber() { return citynumber; }
    public Set<Integer> getPhoneNumbers() { return phoneNumbers; }

    public void addPhonenumber(int i) { phoneNumbers.add(i); }
    public void setPhonenumbers(List<Integer> list) {
        phoneNumbers.clear();
        phoneNumbers.addAll(list);
    }
}