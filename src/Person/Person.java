package Person;

import Incident.Incident;
import Insurance.Insurance;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

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
    List<Insurance> InsuranceHistory; //todo: change Insurance to Insuranceregister key
    List<Incident> IncidentHistory;  //todo: change Incident to Insidentregister key


    /**
     * Person constructor
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

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getAdress() { return contactInfo.getAddress(); }
    public String getCity() { return contactInfo.getCity(); }
    public int getCitynumbr() { return contactInfo.getCitynumber(); }
    public String getEmail() { return contactInfo.getEmail(); }
    public int getAPhonenumber() { return contactInfo.getAPhonenumber(); }
    public Set<Integer> getAllPhonenumbers() {return contactInfo.getPhones(); }
    public int getSocialSecurityNumber() { return socialSecurityNumber; }

    public void addPhonenumber(int phonenumber) { contactInfo.addPhonenumber(phonenumber); }
    public void addPhonenumber(List<Integer> list) { contactInfo.addPhonenumber(list); }
}
