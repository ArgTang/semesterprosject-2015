package Insurance;

import Insurance.Helper.PaymentOption;
import Person.Person;
import java.time.LocalDateTime;

/**
 * Created by steinar on 27.03.2015.
 * Base information for the different insurance types
 */
public abstract class Insurance {
    final LocalDateTime fromDate;
    final double insuranseValue;
    final String insurancePolicy; // todo: informasjon om hva forsikringen dekker, forskjellig fra hver type?
    PaymentOption paymentOption;
    private int annualPremium;
    private static int paymentFee;
    private Person owner; //todo: if cosinging policy? or company owned?
    //todo: Decide on what we should store here annet?

    public Insurance(double insuranseValue, String insurancePolicy) {
        this.fromDate = LocalDateTime.now();
        this.insuranseValue = insuranseValue;
        this.insurancePolicy = insurancePolicy;
    }
}
