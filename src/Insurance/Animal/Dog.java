package Insurance.Animal;

import Insurance.Helper.PaymentOption;

import java.time.LocalDate;

/**
 * Created by nugGet on 25.04.2015.
 */
public class Dog extends Animal
{
    private boolean clubMembership; //todo:need this?

    public Dog(LocalDate validFrom, int purchasePrice, String policy, PaymentOption paymentOption, String usage, String breed, String dateOfBirth,
               String sex, boolean nutered, boolean earlierIllness, AdditionalInfo additionalInfo)
    {
        super(validFrom, purchasePrice, policy, paymentOption, usage, breed, dateOfBirth, sex, nutered, earlierIllness, additionalInfo);
    }


    public boolean getClubMembership() { return clubMembership;}
    public void changeMembershipStatus(boolean newStatus) { clubMembership = newStatus;} //Midlertidig

    //TODO: Make club enum. Different types of clubs
}
