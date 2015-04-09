package Objects;

import Person.Person;

import java.io.File;
import java.util.List;

/**
 * Created by steinar on 09.04.2015.
 */
public final class Property {
    private String adress;
    private String postnumber;
    private String city;

    private short constructionYear;
    private String buildingMaterial;    //todo: ENUM?
    private int buildingValue;
    private String type;             //todo: need this? ENUM?
    private short floors;
    private int totalSqm;

    private Person Owner;           //todo: what if a company owns this?
    private List<Person> contactPerson; //todo: List< keyfromCustomerRegister > instead -> no doublestorage.
    List<File> pictures;

}
