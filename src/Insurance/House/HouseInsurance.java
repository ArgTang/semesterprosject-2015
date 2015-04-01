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

    //temp constructor (for compile)
    public HouseInsurance(double insuranceFee, double insuranseValue, String insurancePolicy, String adress, String postnumber, String city, short constructionYear, String type, String building, short size, String buildingMaterial, int houseValue, Person owner, List<Person> inhabitants) {
        super(insuranceFee, insuranseValue, insurancePolicy);
        this.adress = adress;
        this.postnumber = postnumber;
        this.city = city;
        this.constructionYear = constructionYear;
        this.type = type;
        this.building = building;
        this.size = size;
        this.buildingMaterial = buildingMaterial;
        this.houseValue = houseValue;
        Owner = owner;
        this.inhabitants = inhabitants;
    }
}
