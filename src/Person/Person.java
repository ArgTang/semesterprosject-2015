package Person;

import java.util.Set;

/**
 * Created by steinar on 27.03.2015.
 */
public abstract class Person {

    private String firstName;
    private String lastName;
    private String socialSecurityNumber;
    private ContactInfo contactInfo;


    /**
     * Person constructor
     * @param firstName
     * @param lastName
     * @param socialSecurityNumber
     * @param personId
     * @param contactInfo
     */
    public Person(String firstName, String lastName, String socialSecurityNumber, ContactInfo contactInfo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
        this.contactInfo = contactInfo;
    }

}
