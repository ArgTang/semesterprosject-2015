package Insurance.Animal;

import Insurance.Helper.PaymentOption;
import Insurance.Insurance;
import Person.Person;

import java.time.LocalDate;

/**
 * Created by steinar on 07.04.2015.
 */
public class Animal extends Insurance
{
    double purchasePrice;
    String usage; //TODO: Add enum for different usages
    String breed; //TODO: Add enum, (Full breed or mix)
    String dateOfBirth;
    String sex;
    boolean nutered;
    boolean imported;
    boolean earlierIllness;
    AdditionalInfo additionalInfo;

    String countryOrigin; //TODO: need this?
    boolean vaccinated; //TODO: need this?

    public Animal(LocalDate validFrom, int purchasePrice, String policy, PaymentOption paymentOption, String usage, String breed, String dateOfBirth, String sex, boolean nutered, boolean imported, boolean earlierIllness, AdditionalInfo additionalInfo)
    {
        super(validFrom, purchasePrice, policy, paymentOption);
        this.purchasePrice = purchasePrice;
        this.usage = usage;
        this.breed = breed;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.nutered = nutered;
        this.imported = imported;
        this.earlierIllness = earlierIllness;
        this.additionalInfo = additionalInfo;
    }

    public double getPurchasePrice() { return purchasePrice; }
    public String getUsage() { return usage; }
    public String getBreed() { return breed; }
    public String getDateOfBirth() { return dateOfBirth; }
    public String getSex() { return sex; }
    public boolean getNutered() { return nutered; }
    public boolean getImported() { return imported; }
    public boolean getEarlerIllness() { return earlierIllness; }
    public String getColor() { return additionalInfo.color; }
    public String getName() { return additionalInfo.name; }
    public String getChipNumber() { return additionalInfo.chipNumber; }
    public String getIllnessDescription() { return additionalInfo.illnessDescription; }

    //TODO: Add methods for changing usage and nutered


}
