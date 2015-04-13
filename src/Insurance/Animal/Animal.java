package Insurance.Animal;

import Insurance.Insurance;
import Person.Person;

/**
 * Created by steinar on 07.04.2015.
 */
public class Animal extends Insurance{

    String breed;
    String name;
    String countryOrigin;
    String Country;
    Person Owner;
    int purchasePrice;
    int Value;
    String chipNumber;
    String Sex;
    boolean vaccinated;

    public Animal(double insuranseValue, String insurancePolicy, String breed) {
        super(insuranseValue, insurancePolicy);
        this.breed = breed;
    }
}
