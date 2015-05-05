package Insurance.Property;

import Insurance.Helper.PaymentOption;
import Insurance.Insurance;
import Person.Customer;

import java.time.LocalDate;

/**
 * Created by steinar on 27.03.2015.
 * Base Property information
 */
public abstract class PropertyInsurance extends Insurance
{
    private String adress;
    private int citynumber;
    private String city;

    private int constructionYear;
    private String buildingMaterial;    //todo: ENUM? mur, tre, betong osv
    private int taxedvalue;
    private String type;             //todo: need this? ENUM? rekkehus, blokk, enebolig osv
    private int grossArea;
    private int primaryArea;


    public PropertyInsurance(LocalDate validFrom, int itemValue, String policy, Customer customer, PaymentOption paymentOption,
                             int deductable, String adress, int citynumber, String city, int constructionYear, String buildingMaterial,
                             int taxedvalue, String type, int grossArea, int primaryArea) {
        super(validFrom, itemValue, policy, customer, paymentOption, deductable);
        this.adress = adress;
        this.citynumber = citynumber;
        this.city = city;
        this.constructionYear = constructionYear;
        this.buildingMaterial = buildingMaterial;
        this.taxedvalue = taxedvalue;
        this.type = type;
        this.grossArea = grossArea;
        this.primaryArea = primaryArea;
    }

    public int getConstructionYear() { return constructionYear; }
    public String getBuildingMaterial() { return buildingMaterial; }
    public int getTaxedvalue() { return taxedvalue; }
    public String getType() { return type; }
    public int getGrossArea() { return grossArea; }
    public int getPrimaryArea() { return primaryArea; }
}