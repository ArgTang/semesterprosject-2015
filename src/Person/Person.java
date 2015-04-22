package Person;

import java.util.List;
import java.util.Set;


/**
 * Created by steinar on 27.03.2015.
 */
public abstract class Person {

    //todo: Merge Person and Object package together.
    private String firstName;
    private String lastName;
    private int socialSecurityNumber;
    private ContactInfo contactInfo;
    private String password;


    /**
     * Person constructor
     *
     * @param firstName
     * @param lastName
     * @param socialSecurityNumber
     * @param contactInfo
     */
    public Person(String firstName, String lastName, int socialSecurityNumber, ContactInfo contactInfo) {
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

    public int getCitynumbr() {
        return contactInfo.getCitynumber();
    }

    public String getEmail() {
        return contactInfo.getEmail();
    }

    public int getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public Set<Integer> getPhoneNumbers() {
        return contactInfo.getPhoneNumbers();
    }

    public void addPhonenumber(int phonenumber) {
        contactInfo.addPhonenumber(phonenumber);
    }

    public void addPhonenumber(List<Integer> list) {
        contactInfo.addPhonenumber(list);
    }

    @Override
    public String toString() {
        String text = firstName + " " + lastName;
        text += "\n" + contactInfo.toString();
        return text;
    }
}


