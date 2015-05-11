package Insurance.Property;

import Insurance.Helper.PaymentOption;
import Person.Customer;

import java.time.LocalDate;

/**
 * Created by steinar on 27.03.2015.
 */
public final class HomeInsurance extends PropertyInsurance
{
    private boolean extended;

    public HomeInsurance(LocalDate validFrom, int itemValue, String policy, Customer customer, PaymentOption paymentOption,
                         int deductable, String adress, int citynumber, String city, int constructionYear, String buildingMaterial,
                         int taxedvalue, String type, int grossArea, int primaryArea, boolean extended) {

        super(validFrom, itemValue, policy, customer, paymentOption, deductable, adress, citynumber, city, constructionYear,
                buildingMaterial, taxedvalue, type, grossArea, primaryArea);
        this.extended = extended;
        premiumCalculation();
    }

    private void premiumCalculation() {
        double materialAdjust = getBuildingMaterial().equalsIgnoreCase("tre") ? 3.5:3.8;
        String type = getType();
        double typeadjust = type.matches("(?i:.*rekkehus.*)") || type.matches("(?i:.*tomannsbolig.*)") ? 0.5:0;
        typeadjust += type.matches("(?i:.*enebolig.*)") ? 1:0.8;
        double yearAdjust = (double)(LocalDate.now().getYear() - getConstructionYear())/100.0;
        double deductableAdjust = ( 12000.0-getDeductable() ) / 10000.0;
        double totalAdjust = (yearAdjust+typeadjust+deductableAdjust)/materialAdjust;

        double basePremium = (getGrossArea() + getPrimaryArea())*8 + getTaxedvalue()*0.005;

        setAnnualPremium( (int)(basePremium*(totalAdjust)));
    }
}