package Insurance.Animal;

import Insurance.Helper.PaymentOption;
import Insurance.Insurance;
import Person.Person;

import java.time.LocalDate;

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


    public Animal(LocalDate validFrom, int itemValue, String policy, long owner, PaymentOption paymentOption) {
        super(validFrom, itemValue, policy, owner, paymentOption);
    }
}
