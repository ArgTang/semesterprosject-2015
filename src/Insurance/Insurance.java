package Insurance;

import Insurance.Helper.PaymentOption;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by steinar on 27.03.2015.
 * Base information for the different insurance types
 */

public abstract class Insurance
{
    private final LocalDateTime dateCreated; //TODO: Find a better name for "what date the insurance was created"
    private final LocalDate validFrom;
    private LocalDate endDate;
    private  final int itemValue;
    private  final String policy; // todo: informasjon om hva forsikringen dekker, forskjellig fra hver type?
    private  PaymentOption paymentOption;
    private int annualPremium;
    private static int monthlyPremium;
    private long owner; //todo: need this? if cosinging policy? or company owned?
    //todo: Decide on what we should store here annet?

    public Insurance( LocalDate validFrom, int itemValue, String policy, PaymentOption paymentOption )
    {
        this.dateCreated = LocalDateTime.now();
        this.validFrom = validFrom;
        this.itemValue = itemValue;
        this.policy = policy;
        this.paymentOption = paymentOption;
    }
}
