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
    private final LocalDateTime dateCreated;
    private final LocalDate validFrom;
    private LocalDate endDate;
    private  final int itemValue;
    private  final String policy; // todo: informasjon om hva forsikringen dekker, forskjellig fra hver type?
    private  PaymentOption paymentOption;
    private int annualPremium;
    private int monthlyPremium;
    private String owner; //personnummer todo: need this? if cosinging policy? or company owned?
    private int deductible; //bonus

    public Insurance( LocalDate validFrom, int itemValue, String policy, PaymentOption paymentOption )
    {
        this.dateCreated = LocalDateTime.now();
        this.validFrom = validFrom;
        this.itemValue = itemValue;
        this.policy = policy;
        this.paymentOption = paymentOption;
    }
}