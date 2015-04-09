package Objects;

import Person.Person;

import java.util.List;

/**
 * Created by steinar on 09.04.2015.
 */
public class Property {
    private String adress;
    private String postnumber;
    private String city;
    private short constructionYear;
    private String type;             //todo: ENUM?
    private String building;
    private short size;
    private String buildingMaterial;    //todo: ENUM?
    private int houseValue;
    private Person Owner;           //todo: what if company owns this?
    private List<Person> contactPerson; //todo: maybe??

}
