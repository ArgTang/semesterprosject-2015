package GUI.AgentGUI.Insurance.Modules;

/**
 * Created by steinar on 27.04.2015.
 */

import GUI.GuiHelper.AlertWindow;
import GUI.GuiHelper.CommonGUIMethods;
import GUI.GuiHelper.RegEX;
import Insurance.Helper.PaymentOption;
import Insurance.Vehicle.BoatInsurance;
import Insurance.Vehicle.VehicleInsurance;
import Register.RegisterCustomer;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
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
import static GUI.StartMain.currentCustomer;
import static Insurance.Insurance.deductablenumbers;
import static Insurance.Insurance.paymentOptions;
import static Insurance.Vehicle.BoatInsurance.*;
import static Insurance.Vehicle.BoatInsurance.minBoatValueforMandatoryRegistration;
import static Insurance.Vehicle.VehicleInsurance.kaskoValues;

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

    private static BoatInsurance insurance;

    @FXML
    @Override
    protected void initialize() {

        kasko.setItems(kaskoValues);
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
        resetTextFields(speed, size, motorsize, model, maker, harbor, licenceNumber, modelYear, buyPrice);

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
        addCSSTextValidation(isLetters(), maker, harbor); //todo: allow numbers for harbor? i.e. peer 16
        addCSSTextValidation(isAllChars(), licenceNumber, model);
        RegEX.addCSSTextValidation(modelYear, isNumberWithLength(4));
    }

    @Override
    protected boolean checkValidation() {

        //todo: what if we want to give feedbavk to user for what and witch Textfield are wrong ??
        if ( !validationIsOk(0, size, speed, motorsize))
            return false;

        if ( !validationIsOk(2, maker, harbor, model, modelYear, buyPrice) )
            return false;


        if (buyPrice.getLength() > 2 && parseInt(buyPrice) > minBoatValueforMandatoryRegistration)
            if ( !validationIsOk(2).test(licenceNumber) ) {
                AlertWindow.messageDialog("Registreringsnummer er obligatorisk for båter med verdi over " +
                        minBoatValueforMandatoryRegistration, "Registreringsnummer påkrevd");
                return false;
            }
        return true;
    }

    @Override
    protected void setListeners() {

        addComboboxListener(type, kasko, deductible, paymentOption);
        addTextfieldListener(speed, size, motorsize, modelYear);

        emptyscreenButton.addListener(observable -> {
            SimpleBooleanProperty bool = (SimpleBooleanProperty) observable;
            if (bool.get())
                clearFields();
        });

        confirmOrderButton.addListener(observable -> {
            BooleanProperty bool = (BooleanProperty) observable;
            if (insuranceChoiceListener.getPropertyString().equals("[Båt]") && bool.get()) {
                makeInsurance();
                //saveInsurance(insurance);
            }
        });

        insuranceChoiceListener.getStringProperty().addListener(observable -> {
            SimpleStringProperty property = (SimpleStringProperty) observable;
            if (property.get().equals("[Båt]")) {
                makeInsurance();
            }
        });
    }

    @Override
    protected void makeInsurance() {
        if (!checkValidation())
            return;

        PaymentOption selectedPayment = paymentOptions.get( paymentOption.getSelectionModel().getSelectedIndex() );

        try {
            insurance = new BoatInsurance(fromDate.getValue(), parseInt(buyPrice),"somePolicy", currentCustomer.getPerson(),
                    selectedPayment, maker.getText(), model.getText(), parseInt(modelYear), parseInt(motorsize),
                    parseInt(speed), parseInt(size), type.getSelectionModel().getSelectedItem(), deductible.getSelectionModel().getSelectedItem() );
            showPremium(insurance);
        } catch (Exception expected) {
            BoatInsurance tempinsurance = new BoatInsurance(fromDate.getValue(), parseInt(buyPrice),"somePolicy",
                    RegisterCustomer.tempCustomer,  selectedPayment, maker.getText(), model.getText(), parseInt(modelYear),
                    parseInt(motorsize), parseInt(speed), parseInt(size), type.getSelectionModel().getSelectedItem(),
                    deductible.getSelectionModel().getSelectedItem() );
            showPremium(tempinsurance);
        }

        System.out.print("");
    }
}