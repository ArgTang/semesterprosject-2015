package Insurance.Animal;

import Insurance.Helper.PaymentOption;

import java.time.LocalDate;

/**
 * Created by nugGet on 25.04.2015.
 */
public class Horse extends Animal
{
    private boolean clubMembership;

    public Horse(LocalDate validFrom, int purchasePrice, String policy, PaymentOption paymentOption, String usage, String breed, String dateOfBirth,
               String sex, boolean nutered, boolean imported, boolean earlierIllness, AdditionalInfo additionalInfo, boolean clubMembership)
    {
        super(validFrom, purchasePrice, policy, paymentOption, usage, breed, dateOfBirth, sex, nutered, imported, earlierIllness, additionalInfo);
    }

    public boolean getClubMembership() { return clubMembership; }
    public void changeMembershipStatus( boolean newStatus ) { clubMembership = newStatus; } //Midlertidig

    //TODO: Make club enum. Different types of clubs
}
