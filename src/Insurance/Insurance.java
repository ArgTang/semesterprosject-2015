package Insurance;

import Incident.Incident;
import Insurance.Helper.PaymentOption;
import Person.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Set;

import static GUI.CurrentObjectListeners.CurrentInsurance.getNameOfInsurance;

/** Superclass for all our insurances.
 * Created by steinar on 27.03.2015.
 * Base information for the different insurance types
 */

public abstract class Insurance implements Serializable
{
    private static final long serialVersionUID = 7526472295622776147L;
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
    private Set<Integer> incidentIDs;

    public static final int paymentFee = 35;
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

    public void setCasenumber(int casenumber) {
        //deny new casenumber if alreaddy given
        if (this.casenumber < 1)
            this.casenumber = casenumber;
        else
            throw new IllegalArgumentException("This insurance already have a caseNumber. You can only assign a casenumber once");
    }

    public void addIncident(Incident incident) {
        incidentIDs.add(incident.getIncidentID());
    }
    public Set<Integer> getIncidentIDs() {
        return incidentIDs;
    }

    public void setAnnualPremium(int annualPremium) {
        if (endDate != null)
            throw new IllegalArgumentException("This insurance is ended, change of data is not allowed");
        this.annualPremium = annualPremium; }
    public int getCasenumber() { return casenumber; }
    public int getFromYear() { return validFrom.getYear(); }
    public PaymentOption getPaymentOption() { return paymentOption; }
    public int getAnnualPremium() { return annualPremium; }
    public int getItemValue() { return itemValue; }
    public int getDeductable() { return deductable; }

    public String getInsuranceName() { return getNameOfInsurance(this); }
    public void endInsuranse(LocalDate endDate) {
        this.endDate = endDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public LocalDate getFromDate() { return validFrom; }
}