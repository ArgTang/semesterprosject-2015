package GUI.AgentGUI.Insurance.Modules;

import GUI.AgentGUI.Insurance.AgentInsuranceController;
import GUI.GuiHelper.CommonGUIMethods;
import GUI.GuiHelper.RegEX;
import GUI.StartMain;
import Insurance.Insurance;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.time.LocalDate;

/**
 * Created by steinar on 17.04.2015.
 */
public final class CarModuleController implements CommonGUIMethods
{
    @FXML
    TextField licenceNumber;
    @FXML
    TextField km;
    @FXML
    ComboBox maker;
    @FXML
    TextField model;
    @FXML
    TextField motorsize;
    @FXML
    TextField modelYear;
    @FXML
    TextField color;
    @FXML
    TextField buyPrice;

    @FXML
    DatePicker fromDate;
    @FXML
    CheckBox ageRequirements;
    @FXML
    ComboBox kasko;
    @FXML
    ComboBox bonus;
    @FXML
    ComboBox yearlyKM;
    @FXML
    ComboBox deductible;
    @FXML
    ComboBox paymentOption;

    //combobox values:
    private ObservableList<String> kaskoValues = FXCollections.observableArrayList();
    private ObservableList<Integer> bonusValues = FXCollections.observableArrayList();
    private ObservableList<String> kmValues = FXCollections.observableArrayList();
    private ObservableList<String> type = FXCollections.observableArrayList(
            "Stasjonsvogn","SUV","Flerbruksbil","Coupe","Cabriolet","Veteran","Sedan","Kasse","Kombi","Picup","Elbil");
    private ObservableList<String> makers = FXCollections.observableArrayList(
            "Alfa Romeo","Aston Martin","Audi","Austin","Bentley","BMW","Buddy","Buick","Cadillac",
            "Chevrolet","Chrysler","Citroen","Dacia","Daewoo","Daihatsu","Datsun","Dodge","Ferrari",
            "Fiat","Fisker","Ford","GMC","Honda","Hummer","Hyundai","Infiniti","Isuzu","Iveco","Jaguar",
            "Jeep","Jensen","Kewet","Kia","Lada","Lamborghini","Lancia","Land Rover","Lexus","Lincon","Lotus",
            "Maserati","Matra","Mazda","McLaren","Mercedes-Benz","Mercury","MG","Mia Electric","MINI","Mitsubishi",
            "Morgan","Morris","Nissan","Oldsmobile","Opel","Peugeot","Plymouth","Pontiac","Porche","Renault","Reva",
            "Rolls Royce","Rover","Saab","Seat","Skoda","Smart","Ssangyong","Subaru","Suzuki","Tesla","Think","Toyota",
            "Triumph","Volkswagen","Volvo","Andre");

    @FXML
    private void initialize() {
        //todo: some of these might be used for more insurances -> move into Vehicle Class
        kaskoValues.addAll("Ansvar", "Delkasko", "Fullkasko");
        kasko.setItems(kaskoValues);

        bonusValues.addAll(0, 10, 20, 30, 40, 50, 60, 70, 75);
        bonus.setItems(bonusValues);

        kmValues.addAll("8 000km", "12 000km", "16 000km", "ubegrenset km");
        yearlyKM.setItems(kmValues);

        maker.setItems(makers);

        deductible.setItems(Insurance.deductablenumbers);
        paymentOption.setItems(Insurance.paymentOptionNames);

        addCSSValidation();
        setInsuranceChoiceListener();
        clearFields();
    }

    @Override
    public void clearFields() {
        resetTextFields(licenceNumber, km, model, motorsize, color, buyPrice);
        fromDate.setValue(LocalDate.now());
        ageRequirements.setIndeterminate(false);

        //explanation -> https://thierrywasyl.wordpress.com/2014/02/09/update-your-scene-in-javafx/
        Runnable clear = () -> {
            maker.setValue( maker.getItems().get(2) );
            kasko.setValue( kasko.getItems().get(1) );
            bonus.setValue( bonus.getItems().get(2) );
            yearlyKM.setValue( yearlyKM.getItems().get(0) );
            deductible.setValue( deductible.getItems().get(1) );
            paymentOption.setValue( paymentOption.getItems().get(0) );
        };

        if(Platform.isFxApplicationThread())
            clear.run();
        else
            Platform.runLater(clear);
    }

    @Override
    public void addCSSValidation() {
        RegEX.addCSSTextValidation(licenceNumber, RegEX.isAllChars()); //todo: regex for this?
        RegEX.addCSSTextValidation(model, RegEX.isAllChars()); //todo: is chars or is letters
        RegEX.addCSSTextValidation(modelYear, RegEX.isNumber(4));
        RegEX.addCSSTextValidation(color, RegEX.isLetters());
        addCSSTextValidation(RegEX.isNumber(), km, motorsize, buyPrice);
    }

    private void setInsuranceChoiceListener() {

        StartMain.currentInsurance.getInsuranceProperty().addListener(
                observable -> {
                    ObjectProperty<Insurance> insurance = (ObjectProperty<Insurance>) observable;
                    if (insurance.isNotNull().get()) {
                        //todo: set insurance
                    } else
                        clearFields();
                }
        );

        AgentInsuranceController.emptyscreen.addListener(observable -> {
            SimpleBooleanProperty bool = (SimpleBooleanProperty) observable;
            if (bool.get())
                clearFields();
        });
    }
}