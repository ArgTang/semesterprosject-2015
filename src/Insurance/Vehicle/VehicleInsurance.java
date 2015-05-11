package Insurance.Vehicle;

import Insurance.Helper.PaymentOption;
import Insurance.Insurance;
import Person.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

/**
 * Created by steinar on 27.03.2015.
 */
public abstract class VehicleInsurance extends Insurance
{
    private String maker, model;
    private int productionYear;
    private String kasko;

    public static final ObservableList<String> kaskoValues = FXCollections.observableArrayList("Delkasko", "Fullkasko", "Pluss (tyveri)");

    public VehicleInsurance(LocalDate validFrom, int itemValue, String policy, Customer customer, PaymentOption paymentOption,
                            String maker, String model, int productionYear, int deductable, String kasko) {
        super(validFrom, itemValue, policy, customer, paymentOption, deductable);
        this.maker = maker;
        this.model = model;
        this.productionYear = productionYear;
        this.kasko = kasko;
    }

    public String getMaker() { return maker; }
    public String getModel() { return model; }
    public int getProductionYear() { return productionYear; }
    public String getKasko() {
        return kasko;
    }
}