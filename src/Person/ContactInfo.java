package Person;

import javafx.collections.ObservableList;

import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by nugGet on 09.04.2015.
 */
public class ContactInfo
{
    private String address;
    private String city;
    private int citynumber;
    private String email;
    private Set<Integer> phones = new HashSet(); //TODO: is this needed?

    /**
     *
     * @param address
     * @param email
     * @param city
     * @param phone
     */
    public ContactInfo(String address, String email, String city, int citynumber, int phone)
    {
        this.address = address;
        this.email = email;
        this.city = city;
        this.citynumber = citynumber;
        phones.add(phone);
    }

    public String getAddress() { return address; }
    public String getCity() { return city; }
    public String getEmail() { return email; }
    public Set<Integer> getPhones() { return phones; }
    public int getCitynumber() { return citynumber; }

    public int getAPhonenumber()
    {
        if (phones.isEmpty())
            throw  new EmptyStackException();
        return phones.stream()
                     .findAny().get();
    }

    public void addPhonenumber(int i) { phones.add(i); }
    public void addPhonenumber(List<Integer> list)
    {
        phones.clear();
        phones.addAll(list);
    }
}
