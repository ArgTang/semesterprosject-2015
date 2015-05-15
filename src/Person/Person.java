package Person;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

/**Superclass for PersonObjects
 * Created by steinar on 27.03.2015.
 */
public abstract class Person implements Serializable {
    private static final long serialVersionUID = 6526472295622776147L;
    private String firstName;
    private String lastName;
    private String socialSecurityNumber; //If we make this a number: 0304052345 will become 304052345
    private LocalDate deathDate = null;
    private ContactInfo contactInfo;
    private String password;

    public Person(String firstName, String lastName, String socialSecurityNumber, ContactInfo contactInfo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
        this.contactInfo = contactInfo;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAdress() {
        return contactInfo.getAddress();
    }

    public String getCity() {
        return contactInfo.getCity();
    }

    public String getCitynumber() {
        return contactInfo.getCitynumber();
    }

    public String getEmail() {
        return contactInfo.getEmail();
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public Set<Integer> getPhoneNumbers() {
        return contactInfo.getPhoneNumbers();
    }

    public void addPhonenumber(int phonenumber) {
        contactInfo.addPhonenumber(phonenumber);
    }

    public void addPhonenumber(List<Integer> list) {
        contactInfo.setPhonenumbers(list);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setDeathDate(LocalDate deathDate) {
        this.deathDate = deathDate;
    }
    public LocalDate getDeathDate() {
        return deathDate;
    }
}