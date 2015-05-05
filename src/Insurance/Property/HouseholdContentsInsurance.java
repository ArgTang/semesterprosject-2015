package Insurance.Property;

import Insurance.Helper.PaymentOption;
import Insurance.Insurance;
import Person.Customer;

import java.time.LocalDate;

/**
 * Created by steinar on 29.04.2015.
 */
public class HouseholdContentsInsurance extends Insurance{

    private String address;
    private int citynumber;
    private String city;
    private int roomCount;
    private int roomMates;
    private boolean pluss;

    private int basePremium = 500;
    private double roomCountAdjust = 0.4;
    private double roomMatesAdjust = 0.6;
    private double valueAdjust = 0.002;

    public HouseholdContentsInsurance(String address, int citynumber, String city, int roomCount, int roomMates, LocalDate validFrom,
                                      int itemValue, String policy, Customer customer, PaymentOption paymentOption, int deductable) {
        super(validFrom, itemValue, policy, customer, paymentOption, deductable);
        this.address = address;
        this.citynumber = citynumber;
        this.city = city;
        this.roomCount = roomCount;
        this.roomMates = roomMates;

        premiumCalculation();
    }

    private void premiumCalculation() {
        double premium;
        double adjust = (roomCount*roomCountAdjust)+(roomMates*roomMatesAdjust);
        double valueadjust = ( 12000.0-getDeductable() ) / 10000.0;
        valueadjust += 0.3*(pluss ? 1:0);
        premium = (valueadjust + adjust)* basePremium + getItemValue()*this.valueAdjust;
        super.setAnnualPremium( (int) premium );
    }
}