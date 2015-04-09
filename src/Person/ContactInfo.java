package Person;

import java.util.Set;

/**
 * Created by nugGet on 09.04.2015.
 */
public class ContactInfo
{
    private String address;
    private String email;
    private String city;
    Set<Integer> phone; //TODO: Make hashset to store work phnoe and private phone

    /**
     *
     * @param address
     * @param email
     * @param city
     * @param phone
     */
    public ContactInfo(String address, String email, String city, Set<Integer> phone)
    {
        this.address = address;
        this.email = email;
        this.city = city;
        this.phone = phone;
    }
}
