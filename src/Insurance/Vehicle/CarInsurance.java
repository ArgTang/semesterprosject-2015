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
public final class CarInsurance extends Vehicle {

    private int totalKilometer, horsePower;
    byte bonus; //todo: maybe arraylist -> observable list see Agentgui.insurance.CarModuleController
    private String licenceNumber;
    private String color;
    private String kasko;
    private String yearlyKm;

    public static final ObservableList<String> kaskoValues = FXCollections.observableArrayList("Ansvar", "Delkasko", "Fullkasko");
    public static final ObservableList<Integer> bonusValues = FXCollections.observableArrayList(0, 10, 20, 30, 40, 50, 60, 70, 75);
    public static final ObservableList<String> kmValues = FXCollections.observableArrayList("8 000km", "12 000km", "16 000km", "ubegrenset km");
    public static final ObservableList<String> type = FXCollections.observableArrayList(
            "Stasjonsvogn","SUV","Flerbruksbil","Coupe","Cabriolet","Veteran","Sedan","Kasse","Kombi","Picup","Elbil");
    public static final ObservableList<String> makers = FXCollections.observableArrayList(
            "Alfa Romeo","Aston Martin","Audi","Austin","Bentley","BMW","Buddy","Buick","Cadillac",
            "Chevrolet","Chrysler","Citroen","Dacia","Daewoo","Daihatsu","Datsun","Dodge","Ferrari",
            "Fiat","Fisker","Ford","GMC","Honda","Hummer","Hyundai","Infiniti","Isuzu","Iveco","Jaguar",
            "Jeep","Jensen","Kewet","Kia","Lada","Lamborghini","Lancia","Land Rover","Lexus","Lincon","Lotus",
            "Maserati","Matra","Mazda","McLaren","Mercedes-Benz","Mercury","MG","Mia Electric","MINI","Mitsubishi",
            "Morgan","Morris","Nissan","Oldsmobile","Opel","Peugeot","Plymouth","Pontiac","Porche","Renault","Reva",
            "Rolls Royce","Rover","Saab","Seat","Skoda","Smart","Ssangyong","Subaru","Suzuki","Tesla","Think","Toyota",
            "Triumph","Volkswagen","Volvo","Andre");

    public CarInsurance(LocalDate validfrom, int itemValue, String insurancePolicy, Customer customer, PaymentOption paymentOption, String maker, String model,
                        int productionYear, int totalKilometer, int horsePower, String licenceNumber, String color, int deductable, int bonus, String kasko, String yearlyKm)
    {
        super(validfrom, itemValue, insurancePolicy, customer, paymentOption, maker, model, productionYear, deductable);
        this.totalKilometer = totalKilometer;
        this.horsePower = horsePower;
        this.color = color;
        this.bonus = (byte) bonus;
        this.kasko = kasko;
        this.yearlyKm = yearlyKm;
        calculateAndSetAnnualPremium();
    }

    public int getTotalKilometer() { return totalKilometer; }
    public int getHorsePower() { return horsePower; }


    public int getCurrentBonus() {
        int years = LocalDate.now().getYear() - getFromYear();
        int totalbonus = bonus + (years*10);

        if ( getIncidentIDs() != null && !getIncidentIDs().isEmpty() ) {
            //TODO: only substract incident when owner is responible and not paying himself
            totalbonus = totalbonus - (getIncidentIDs().size()*10);
        }

        if (totalbonus < 0)
            return 0;
        if (totalbonus > 75)
            return 75;

        return totalbonus;
    }

    private void calculateAndSetAnnualPremium() {
        int km = getTotalKilometer();
        int horsepower = getHorsePower();
        int age = LocalDate.now().getYear() - getProductionYear();

        double ageAdjust = pow((log(km) + 1 * log(age) + 1), 0.7);
        double horseAdjust = pow(horsepower, 4) / 100000000;
        double buyPriceAdjust = pow(getItemValue(), 0.4)/100;
        int basePrice = 20000;

        basePrice += getDeductable();
        double kmprYearAdjust = 1;

        if ( yearlyKm.equals(kmValues.get(0)) )
            kmprYearAdjust = 1;
        else if (yearlyKm.equals(kaskoValues.get(1)))
            kmprYearAdjust = 1.2;
        else if (yearlyKm.equals(kmValues.get(2)))
            kmprYearAdjust = 1.4;
        else if (yearlyKm.equals(kmValues.get(3)))
            kmprYearAdjust = 1.7;

        double price = (basePrice*(buyPriceAdjust+(horseAdjust/2)))/ageAdjust;

        if (kasko.equals(kaskoValues.get(0)))
            price = price * kmprYearAdjust * 1;
        else if (kasko.equals(kaskoValues.get(1)))
            price = price * kmprYearAdjust *  1.2;
        else if (kasko.equals(kaskoValues.get(2)))
            price = price * kmprYearAdjust * 1.6;

        super.setAnnualPremium((int) price);
    }
}
