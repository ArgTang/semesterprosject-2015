package Insurance.Animal;

import Insurance.Helper.PaymentOption;
import Person.Customer;

import java.time.LocalDate;

/**
 * Created by nugGet on 25.04.2015.
 */
public class Dog extends Animal
{
    private boolean clubMembership; //todo:need this?

    public Dog(LocalDate validFrom, int purchasePrice, String policy, Customer customer, PaymentOption paymentOption, String usage, String breed, String dateOfBirth,
               String sex, boolean nutered, boolean earlierIllness, AdditionalInfo additionalInfo, int deductable)
    {
        super(validFrom, purchasePrice, policy, customer, paymentOption, usage, breed, dateOfBirth, sex, nutered, earlierIllness, additionalInfo, deductable);
    }

    public boolean getClubMembership() { return clubMembership;}
    public void changeMembershipStatus(boolean newStatus) { clubMembership = newStatus;} //Midlertidig

    //TODO: Make club enum. Different types of clubs
}