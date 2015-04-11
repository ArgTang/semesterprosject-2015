package Person;

import Incident.Incident;
import Insurance.Insurance;

import java.util.List;


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

    protected int getSocialSecurityNumber() { return socialSecurityNumber; }
}
