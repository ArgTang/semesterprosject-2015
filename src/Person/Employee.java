package Person;


/**
 * Created by steinar on 27.03.2015.
 */
public final class Employee extends Person
{

    private String jobTitle;
    private int idCount = 00001;
    private String employeeId;

    /**
     *
     * @param firstName
     * @param lastName
     * @param socialSecurityNumber
     * @param contactInfo
     * @param jobTitle
     */
    public Employee(String firstName, String lastName, String socialSecurityNumber, ContactInfo contactInfo, String jobTitle)
    {
        super(firstName, lastName, socialSecurityNumber, contactInfo);
        this.jobTitle = jobTitle;
        employeeId = "emp" + idCount++;
    }
}
