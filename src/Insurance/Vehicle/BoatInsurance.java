package Insurance.Vehicle;

import Insurance.Helper.PaymentOption;
import Person.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

import static java.lang.Math.log;
import static java.lang.Math.pow;

/**
 * Created by steinar on 27.03.2015.
 */
public final class BoatInsurance extends VehicleInsurance
{
    private int knots, feet, horsepower;
    String boattype; //seilbåt, innenbors eller utenborsmotor
    private String licenceNumber = ""; //req if boat value > 50000
    private String harbor;

    public static final int minBoatValueforMandatoryRegistration = 50000;
    public static final ObservableList<String> types = FXCollections.observableArrayList("Innenbordsmotor", "Utenbordsmotor", "Seilbåt");

    public BoatInsurance(LocalDate validfrom, int itemValue, String insurancePolicy, Customer customer, PaymentOption paymentOption, String maker,
                         String model, int productionYear, int horsePower, int knots, int feet, String boattype, String harbor, String kasko, int deductable) {
        super(validfrom, itemValue, insurancePolicy, customer, paymentOption, maker, model, productionYear, deductable, kasko);
        //todo: if value > 50 000 registrationnumber required. here or in gui?
        this.horsepower = horsePower;
        this.knots = knots;
        this.feet = feet;
        this.boattype = boattype;
        this.harbor = harbor;
        calculatePremium();
    }

    public int getKnots() { return knots; }
    public int getFeet() { return feet; }

    public int getHorsepower() {
        return horsepower;
    }

    public String getBoattype() {
        return boattype;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setRegistrationNumber(String registrationnumber) { this.licenceNumber = registrationnumber; }

    private void calculatePremium() {
        double base = (getItemValue()+2000) * 0.005;
        double age = LocalDate.now().getYear() - getProductionYear();
        double poweradjust = log(horsepower) + log(pow(knots, 2));

        int typeAdjust = 18;
        if (boattype.equals(types.get(0)))
            typeAdjust = 14;
        if (boattype.equals(types.get(1)))
            typeAdjust = 10;

        double sizeadjust = feet/ typeAdjust;
        double helperadjuster = (12000 - getDeductable())/500 + pow(poweradjust, age/10);

        double total = ((base*helperadjuster)/8)*sizeadjust;

        setAnnualPremium((int)total);
    }

    public String getharbor() {
        return harbor;
    }
}