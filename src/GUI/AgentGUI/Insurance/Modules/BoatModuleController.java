package GUI.AgentGUI.Insurance.Modules;

/**
 * Created by steinar on 27.04.2015.
 */

import GUI.GuiHelper.CommonGUIMethods;
import GUI.GuiHelper.RegEX;
import Insurance.Helper.PaymentOption;
import Insurance.Vehicle.BoatInsurance;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.stream.Collectors;

import static GUI.AgentGUI.Insurance.AgentInsuranceController.emptyscreenButton;
import static GUI.AgentGUI.Insurance.AgentInsuranceController.insuranceChoiceListener;
import static GUI.AgentGUI.Insurance.InsuranceConfirmModuleController.confirmOrderButton;
import static GUI.GuiHelper.RegEX.*;
import static Insurance.Insurance.deductablenumbers;
import static Insurance.Insurance.paymentOptions;

public final class BoatModuleController extends CommonGUIMethods
{
    @FXML
    private TextField buyPrice;
    @FXML
    private TextField motorsize;
    @FXML
    private TextField model;
    @FXML
    private TextField maker;
    @FXML
    private TextField modelYear;

    @FXML
    private ComboBox<String> type;
    @FXML
    private TextField licenceNumber;
    @FXML
    private TextField speed;
    @FXML
    private TextField size;
    @FXML
    private TextField harbor;

    @FXML
    private DatePicker fromDate;
    @FXML
    private ComboBox<String> kasko;
    @FXML
    private ComboBox<Integer> deductible;
    @FXML
    private ComboBox<String> paymentOption;

    private ObservableList<String> kaskoValues = FXCollections.observableArrayList();
    private ObservableList<String> types = FXCollections.observableArrayList();

    private static BoatInsurance insurance;

    @FXML
    @Override
    protected void initialize() {
        kaskoValues.addAll("Delkasko", "Fullkasko", "Pluss (tyveri)");
        kasko.setItems(kaskoValues);

        types.addAll("Innenbordsmotor", "Utenbordsmotor", "Seilbåt");
        type.setItems(types);

        deductible.setItems(deductablenumbers);
        paymentOption.setItems(paymentOptions.stream()
                                             .map(PaymentOption::getName)
                                             .collect(Collectors.toCollection(FXCollections::observableArrayList)));

        addCSSValidation();
        clearFields();
        setListeners();
    }

    @Override
    public void clearFields() {
        fromDate.setValue(LocalDate.now());
        resetTextFields(speed, size, motorsize, buyPrice, model, maker, harbor, licenceNumber, modelYear);

        //explanation -> https://thierrywasyl.wordpress.com/2014/02/09/update-your-scene-in-javafx/
        Runnable clear = () -> {
            type.setValue( type.getItems().get(1) );
            kasko.setValue(kasko.getItems().get(2));
            deductible.setValue( deductible.getItems().get(1) );
            paymentOption.setValue( paymentOption.getItems().get(1) );
        };

        if(Platform.isFxApplicationThread())
            clear.run();
        else
            Platform.runLater(clear);
    }

    @Override
    public void addCSSValidation() {
        addCSSTextValidation(isNumber(), speed, size, motorsize);
        RegEX.addCSSTextValidation(buyPrice, isNumber());
        addCSSTextValidation(isLetters(), maker, harbor); //todo: allow numbers for harbor? i.e. peer 16
        addCSSTextValidation(isAllChars(), licenceNumber, model);
        RegEX.addCSSTextValidation(modelYear, isNumberWithLength(4));
    }

    @Override
    protected void setCustomer() {

    }

    @Override
    protected boolean checkValidation() {

        //todo: what if we want to give feedbavk to user for what and witch Textfield are wrong ??
        if ( !validationIsOk(1, size, speed, motorsize) )
            return false;

        if ( !validationIsOk(3, maker, harbor, licenceNumber, model, modelYear, buyPrice ) )
            return false;

        return true;
    }

    @Override
    protected void setListeners() {
        emptyscreenButton.addListener(observable -> {
            SimpleBooleanProperty bool = (SimpleBooleanProperty) observable;
            if (bool.get())
                clearFields();
        });

        confirmOrderButton.addListener(observable -> {
            BooleanProperty bool = (BooleanProperty) observable;
            if (insuranceChoiceListener.getPropertyString().equals("[Båt]") && bool.get()) {
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

    }
}