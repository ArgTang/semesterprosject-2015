package Person;

import java.util.Set;

/**
 * Created by steinar on 27.03.2015.
 */
public class Person {

    private String name;
    private String address;
    private String email;
    private String city;
    Set<Integer> phone;
    long personID; //todo: if we dont count here delete
    private long personIdCounter; //todo: where to count here or in customer\Agent

    public Person(String name, String lastname, String address, String city, String email, int phone) {
        this.name = name + " " + lastname;
        this.address = address;
        this.city = city;
        this.email = email;
        this.phone.add( phone );
    }
}
