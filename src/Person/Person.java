package Person;

import Incident.Incident;
import Insurance.Insurance;

import java.util.List;
import java.util.Set;

/**
 * Created by steinar on 27.03.2015.
 */
public abstract class Person {

    private String firstName;
    private String lastName;
    private String socialSecurityNumber;
    private ContactInfo contactInfo;
    private String password;
    List<Insurance> InsuranceHistory;
    List<Incident> IncidentHistory;


    /**
     * Person constructor
     * @param firstName
     * @param lastName
     * @param socialSecurityNumber
     * @param contactInfo
     */
    public Person(String firstName, String lastName, String socialSecurityNumber, ContactInfo contactInfo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
        this.contactInfo = contactInfo;
    }

}
