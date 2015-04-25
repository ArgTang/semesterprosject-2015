package Insurance.Animal;

import Insurance.Insurance;
import Person.Person;

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

    public Animal(double value, String policy, String usage, String breed, String dateOfBirth, String sex, boolean nutered, boolean imported, boolean earlierIllness, AdditionalInfo additionalInfo)
    {
        super(value, policy);
        purchasePrice = value;
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