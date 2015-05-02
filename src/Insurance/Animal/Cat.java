package Insurance.Animal;

import Insurance.Helper.PaymentOption;
import Person.Customer;

import java.time.LocalDate;

/**
 * Created by nugGet on 25.04.2015.
 */
public class Cat extends AnimalInsurance
{

    public Cat(LocalDate validFrom, int purchasePrice, String policy, Customer customer, PaymentOption paymentOption, String usage, String breed, String dateOfBirth,
               String sex, boolean nutered, boolean earlierIllness, AdditionalInfo additionalInfo, int deductable) {
        super(validFrom, purchasePrice, policy, customer, paymentOption, usage, breed, dateOfBirth, sex, nutered, earlierIllness, additionalInfo, deductable);
    }
}
