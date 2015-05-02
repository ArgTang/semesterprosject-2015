package GUI.AgentGUI.Insurance.Modules;

import GUI.GuiHelper.CommonPrivateGUIMethods;
import GUI.GuiHelper.CommonPublicGUIMethods;
import GUI.GuiHelper.RegEX;
import Insurance.Helper.PaymentOption;
import Insurance.Insurance;
import Insurance.Vehicle.CarInsurance;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
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
import java.util.stream.Collectors;

import static GUI.AgentGUI.Insurance.AgentInsuranceController.emptyscreen;
import static GUI.AgentGUI.Insurance.AgentInsuranceController.insuranceChoiceListener;
import static GUI.AgentGUI.Insurance.InsuranceConfirmModuleController.confirmOrderButton;
import static GUI.GuiHelper.RegEX.*;
import static GUI.StartMain.currentCustomer;
import static GUI.StartMain.currentInsurance;
import static Insurance.Insurance.deductablenumbers;
import static Insurance.Insurance.paymentOptions;

/**
 * Created by steinar on 17.04.2015.
 */
public final class CarModuleController extends CommonPrivateGUIMethods implements CommonPublicGUIMethods
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
    TextField horsePower;
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

    CarInsurance insurance;

    @FXML
    @Override
    protected void initialize() {
        //todo: some of these might be used for more insurances -> move into Vehicle Class
        kaskoValues.addAll("Ansvar", "Delkasko", "Fullkasko");
        kasko.setItems(kaskoValues);

        bonusValues.addAll(0, 10, 20, 30, 40, 50, 60, 70, 75);
        bonus.setItems(bonusValues);

        kmValues.addAll("8 000km", "12 000km", "16 000km", "ubegrenset km");
        yearlyKM.setItems(kmValues);

        maker.setItems(makers);

        deductible.setItems(deductablenumbers);
        paymentOption.setItems(paymentOptions.stream()
                                             .map(PaymentOption::getName)
                                             .collect(Collectors.toCollection(FXCollections::observableArrayList)));

        addCSSValidation();
        setListeners();
        clearFields();
    }

    @Override
    public void clearFields() {
        resetTextFields(licenceNumber, km, model, horsePower, color, buyPrice);
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
        RegEX.addCSSTextValidation(licenceNumber, isAllChars()); //todo: regex for this?
        RegEX.addCSSTextValidation(model, isAllChars()); //todo: is chars or is letters
        RegEX.addCSSTextValidation(modelYear, isNumber(4));
        RegEX.addCSSTextValidation(color, isLetters());
        addCSSTextValidation(isNumber(), km, horsePower, buyPrice);
    }

    @Override
    protected boolean checkValidation() {
        if (validationIsOk(6).negate().test(licenceNumber))
            return false;

        if (validationIsOk(2).negate().test(model))
            return false;

        if (!modelYear.getPseudoClassStates().isEmpty())
            return false;

        if (validationIsOk(1).negate().test(color))
            return false;

        if (validationIsOk(1).negate().test(km))
            return false;

        if (validationIsOk(2).negate().test(horsePower))
            return false;

        if (validationIsOk(3).negate().test(buyPrice))
            return false;

        return true;
    }

    @Override
    protected void setListeners() {

        currentInsurance.getInsuranceProperty().addListener(
                observable -> {
                    ObjectProperty<Insurance> insurance = (ObjectProperty<Insurance>) observable;
                    if (insurance.isNotNull().get()) {
                        //todo: set insurance
                    } else
                        clearFields();
                }
        );

        emptyscreen.addListener(observable -> {
            SimpleBooleanProperty bool = (SimpleBooleanProperty) observable;
            if (bool.get())
                clearFields();
        });

        confirmOrderButton.addListener(observable -> {
            BooleanProperty bool = (BooleanProperty) observable;
            if (insuranceChoiceListener.getPropertyString().equals("[Bil]") && bool.get()) {
                makeInsurance();
                saveInsurance(insurance);
            }
        });
    }

    @Override
    protected void makeInsurance() {
        if (!checkValidation())
            return;

        PaymentOption selectedPayment = paymentOptions.get( paymentOption.getSelectionModel().getSelectedIndex() );
        insurance = new CarInsurance( fromDate.getValue(), parseInt(buyPrice),"comePolicy", currentCustomer.getPerson(), selectedPayment,
        maker.getValue().toString(), model.getText(), parseInt(modelYear), parseInt(km), parseInt(horsePower), licenceNumber.getText(),
        color.getText(), Integer.parseInt( deductible.getValue().toString() ) );

        System.out.print("");
    }
}