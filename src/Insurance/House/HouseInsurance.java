package Insurance.House;
import Insurance.Insurance;
import Person.Person;

import java.util.List;

/**
 * Created by steinar on 27.03.2015.
 * Base House information
 */
public abstract class HouseInsurance extends Insurance{

    String adress;
    String postnumber;
    String city;

    Person Owner;
    List<Person> inhabitants; //maybe??

}
