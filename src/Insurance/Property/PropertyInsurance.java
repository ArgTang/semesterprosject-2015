package Insurance.Property;

import Person.Person;
import Insurance.Insurance;
import java.util.List;

/**
 * Created by steinar on 27.03.2015.
 * Base Property information
 */
public abstract class PropertyInsurance extends Insurance{

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

    //temp constructor (for compile)

    public PropertyInsurance(double insuranseValue, String insurancePolicy, String adress) {
        super(insuranseValue, insurancePolicy);
        this.adress = adress;
    }
}
