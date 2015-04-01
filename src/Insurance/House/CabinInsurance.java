package Insurance.House;

import Insurance.Insurance;
import Person.Person;

import java.util.List;

/**
 * Created by steinar on 27.03.2015.
 */
public final class CabinInsurance extends HouseInsurance {

    //temp constructor (for compile)
    public CabinInsurance(double insuranceFee, double insuranseValue, String insurancePolicy, String adress, String postnumber, String city, short constructionYear, String type, String building, short size, String buildingMaterial, int houseValue, Person owner, List<Person> inhabitants) {
        super(insuranceFee, insuranseValue, insurancePolicy, adress, postnumber, city, constructionYear, type, building, size, buildingMaterial, houseValue, owner, inhabitants);
    }
}
