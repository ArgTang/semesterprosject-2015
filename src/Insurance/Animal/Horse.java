package Insurance.Animal;

/**
 * Created by nugGet on 25.04.2015.
 */
public class Horse extends Animal
{
    private boolean clubMembership;

    public Horse(double value, String policy, String usage, String breed, String dateOfBirth, String sex, boolean nutered, boolean imported, boolean earlierIllness, AdditionalInfo additionalInfo, boolean clubMembership)
    {
        super(value, policy, usage, breed, dateOfBirth, sex, nutered, imported, earlierIllness, additionalInfo);
        this.clubMembership = clubMembership;
    }

    public boolean getClubMembership() { return clubMembership; }
    public void changeMembershipStatus( boolean newStatus ) { clubMembership = newStatus; } //Midlertidig

    //TODO: Make club enum. Different types of clubs
}
