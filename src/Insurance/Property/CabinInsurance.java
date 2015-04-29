package Insurance.Property;

import Insurance.Helper.PaymentOption;
import Person.Customer;

import java.time.LocalDate;

/**
 * Created by steinar on 27.03.2015.
 */
public final class CabinInsurance extends PropertyInsurance {

    public CabinInsurance(LocalDate validFrom, int itemValue, String policy, Customer customer, PaymentOption paymentOption,
                          int deductable, String adress, int citynumber, String city, int constructionYear,
                          String buildingMaterial, int taxedvalue, String type, int grossArea, int primaryArea) {
        super(validFrom, itemValue, policy, customer, paymentOption, deductable, adress, citynumber, city, constructionYear, buildingMaterial, taxedvalue, type, grossArea, primaryArea);
    }
}
