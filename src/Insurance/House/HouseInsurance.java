package Insurance.House;
import Insurance.Insurance;
import Person.Person;

import java.util.List;

/**
 * Created by steinar on 27.03.2015.
 * Base House information
 */
public abstract class HouseInsurance extends Insurance{

    private String adress;
    private String postnumber;
    private String city;
    private short constructionYear;
    private String type; //ENUM
    private String building;
    private short size;
    private String buildingMaterial;    //ENUM?
    private int houseValue;
    private Person Owner;
    private List<Person> inhabitants; //maybe??
}
