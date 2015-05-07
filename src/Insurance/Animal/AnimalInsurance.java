package Insurance.Animal;

import Insurance.Helper.PaymentOption;
import Insurance.Insurance;
import Person.Customer;

import java.time.LocalDate;

/**
 * Created by steinar on 07.04.2015.
 */
public class AnimalInsurance extends Insurance
{
    double purchasePrice;
    String usage; //TODO: Add enum for different usages //need this? horses and\or dogs only?
    String breed; //TODO: Add enum, (Full breed or mix)
    String dateOfBirth;
    String sex;
    boolean nutered; //TODO: for Cats only?
    AdditionalInfo additionalInfo;
    String countryOrigin;

    public AnimalInsurance(LocalDate validFrom, int purchasePrice, String policy, Customer customer, PaymentOption paymentOption, String usage, String breed,
                           String dateOfBirth, String sex, boolean nutered, AdditionalInfo additionalInfo, int deductable) {
        super(validFrom, purchasePrice, policy, customer, paymentOption, deductable);
        this.purchasePrice = purchasePrice;
        this.usage = usage;
        this.breed = breed;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.nutered = nutered;
        this.additionalInfo = additionalInfo;
    }

    public double getPurchasePrice() { return purchasePrice; }
    public String getUsage() { return usage; }
    public String getBreed() { return breed; }
    public String getDateOfBirth() { return dateOfBirth; }
    public String getSex() { return sex; }
    public boolean getNutered() { return nutered; }
    public String getColor() { return additionalInfo.color; }
    public String getName() { return additionalInfo.name; }
    public String getChipNumber() { return additionalInfo.chipNumber; }
}