package Insurance;

import Insurance.Helper.PaymentOption;
import Person.Person;

import java.time.LocalDateTime;

/**
 * Created by steinar on 27.03.2015.
 * Base information for the different insurance types
 */

public abstract class Insurance
{
    final LocalDateTime dateCreated; //TODO: Find a better name for "what date the insurance was created"
    final double itemValue;
    final String policy; // todo: informasjon om hva forsikringen dekker, forskjellig fra hver type?
    PaymentOption paymentOption;
    private int annualPremium;
    private static int monthlyPremium;
    private Person owner; //todo: if cosinging policy? or company owned?
    //todo: Decide on what we should store here annet?

    public Insurance(double itemValue, String policy)
    {
        this.dateCreated = LocalDateTime.now();
        this.itemValue = itemValue;
        this.policy = policy;
    }
}
