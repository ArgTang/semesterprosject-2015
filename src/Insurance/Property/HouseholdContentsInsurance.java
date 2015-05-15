package Insurance.Property;

import Insurance.Helper.PaymentOption;
import Insurance.Insurance;
import Person.Customer;

import java.time.LocalDate;

/**
 * Created by steinar on 29.04.2015.
 */
public class HouseholdContentsInsurance extends Insurance
{
    private String address;
    private String citynumber;
    private String city;
    private int roomCount;
    private int roomMates;
    private boolean pluss;

    public HouseholdContentsInsurance(String address, String citynumber, String city, int roomCount, int roomMates, LocalDate validFrom,
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
        final int basePremium = 500;
        final double roomCountAdjust = 0.4;
        final double roomMatesAdjust = 0.6;
        final double valueAdjust = 0.002;

        double premium;
        double adjust = (roomCount*roomCountAdjust)+(roomMates*roomMatesAdjust);
        double valueadjust = ( 12000.0-getDeductable() ) / 10000.0;
        valueadjust += 0.3*(pluss ? 1:0);
        premium = (valueadjust + adjust)* basePremium + getItemValue()*valueAdjust;
        super.setAnnualPremium( (int) premium );
    }

    public String getAddress() {
        return address;
    }
    public String getCitynumber() {
        return citynumber;
    }
    public String getCity() {
        return city;
    }
    public int getRoomCount() {
        return roomCount;
    }
    public int getRoomMates() {
        return roomMates;
    }
    public boolean isPluss() {
        return pluss;
    }
}