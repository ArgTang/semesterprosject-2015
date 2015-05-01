package Insurance;

import Insurance.Helper.PaymentOption;
import Person.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

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
    private String owner;
    private int casenumber = -1;
    private int deductable; //egenandel

    public static int paymentFee = 35;
    public static final ObservableList<Integer> deductablenumbers = FXCollections.observableArrayList(2000, 4000, 8000, 12000);
    public static final ObservableList<PaymentOption> paymentOptions = FXCollections.observableArrayList(PaymentOption.values());

    public Insurance( LocalDate validFrom, int itemValue, String policy, Customer customer, PaymentOption paymentOption, int deductable) {
        this.dateCreated = LocalDateTime.now();
        this.validFrom = validFrom;
        this.itemValue = itemValue;
        this.policy = policy;
        this.paymentOption = paymentOption;
        this.deductable = deductable;
        try {
            owner = customer.getSocialSecurityNumber();
        } catch (NullPointerException e) {
            throw new NoSuchElementException("Tried assigning owner null as insurance owner");
        }
    }

    public int setCasenumber(int casenumber) {
        //deny new casenumber if alreaddy given
        if (this.casenumber < 1)
            this.casenumber = casenumber;

        return casenumber;
    }

    public void setAnnualPremium(int annualPremium) { this.annualPremium = annualPremium; }
    public int getCasenumber() { return casenumber; }
    public int getFromYear() { return validFrom.getYear(); }
    public Class getInsuranceClass() { return this.getClass(); }
    public PaymentOption getPaymentOption() { return paymentOption; }
    public int getAnnualPremium() { return annualPremium; }
    public int getItemValue() { return itemValue; }
    public int getDeductable() { return deductable; }
}