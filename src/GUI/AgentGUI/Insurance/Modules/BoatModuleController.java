package GUI.AgentGUI.Insurance.Modules;

/**
 * Created by steinar on 27.04.2015.
 */

import GUI.GuiHelper.AlertWindow;
import GUI.GuiHelper.CommonInsuranceMethods;
import GUI.GuiHelper.RegEX;
import Insurance.Helper.PaymentOption;
import Insurance.Vehicle.BoatInsurance;
import Register.RegisterCustomer;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.stream.Collectors;

import static GUI.AgentGUI.Insurance.AgentInsuranceController.insuranceChoiceListener;
import static GUI.AgentGUI.Insurance.InsuranceConfirmModuleController.confirmOrderButton;
import static GUI.CurrentObjectListeners.CurrentInsurance.insuranceListener;
import static GUI.CurrentObjectListeners.CustomerListener.currentCustomer;
import static GUI.GuiHelper.RegEX.*;
import static Insurance.Insurance.deductablenumbers;
import static Insurance.Insurance.paymentOptions;
import static Insurance.Vehicle.BoatInsurance.minBoatValueforMandatoryRegistration;
import static Insurance.Vehicle.BoatInsurance.types;
import static Insurance.Vehicle.VehicleInsurance.kaskoValues;

public final class BoatModuleController extends CommonInsuranceMethods
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

        if (insuranceListener.get() instanceof BoatInsurance) {
            loadCurrentInsurance();
            showInsurance();
        } else {
            clearFields();
            makeInsurance();
        }
        setListeners();
        addCSSValidation();
    }

    @Override
    public void clearFields() {
        if ( !speed.editableProperty().get() )
            unfreezeInput();
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

        setEmptyScreenListener();
        setInsurancechoiceListeners("[Båt]");
        setCurrentInsuranceListener(BoatInsurance.class);

        confirmOrderButton.addListener(observable -> {
            BooleanProperty bool = (BooleanProperty) observable;
            if (bool.get() && insuranceChoiceListener.getString().equals("Båt")) {
                makeInsurance();
                saveInsurance(insurance);
            }
        });
    }

    @Override
    protected void loadCurrentInsurance() {
        BoatModuleController.insurance  = (BoatInsurance) insuranceListener.get();
    }
    @Override
    protected void showInsurance() {
        if ( insurance.getEndDate() != null )
            freezeInput();
        licenceNumber.setText(insurance.getLicenceNumber());
        setInt(size, insurance.getFeet());
        setInt(speed, insurance.getKnots());
        setInt(motorsize, insurance.getHorsepower());
        maker.setText(insurance.getMaker());
        harbor.setText(insurance.getharbor());
        model.setText(insurance.getModel());
        setInt(modelYear, insurance.getProductionYear());
        setInt(buyPrice, insurance.getItemValue());

        fromDate.setValue(insurance.getFromDate());
        type.setValue(insurance.getBoattype());
        kasko.setValue(insurance.getKasko());
        deductible.setValue(insurance.getDeductable());
        paymentOption.setValue(insurance.getPaymentOption().getName());
   }

    @Override
    protected void makeInsurance() {
        if (!checkValidation())
            return;

        PaymentOption selectedPayment = paymentOptions.get( paymentOption.getSelectionModel().getSelectedIndex() );
        try {
            insurance = new BoatInsurance(fromDate.getValue(), parseInt(buyPrice),"somePolicy", currentCustomer.get(),
                    selectedPayment, maker.getText(), model.getText(), parseInt(modelYear), parseInt(motorsize),
                    parseInt(speed), parseInt(size), type.getSelectionModel().getSelectedItem(), harbor.getText(),
                    kasko.getSelectionModel().getSelectedItem(), deductible.getSelectionModel().getSelectedItem() );
            insurance.setRegistrationNumber(licenceNumber.getText());
            showPremium(insurance);
        } catch (Exception expected) {
            BoatInsurance tempinsurance = new BoatInsurance(fromDate.getValue(), parseInt(buyPrice),"somePolicy",
                    RegisterCustomer.tempCustomer,  selectedPayment, maker.getText(), model.getText(), parseInt(modelYear),
                    parseInt(motorsize), parseInt(speed), parseInt(size), type.getSelectionModel().getSelectedItem(),
                    harbor.getText(), kasko.getSelectionModel().getSelectedItem(),  deductible.getSelectionModel().getSelectedItem() );
            tempinsurance.setRegistrationNumber(licenceNumber.getText());
            showPremium(tempinsurance);
        }
    }

    @Override
    protected void freezeInput() {
        freezeInputs(size, speed, motorsize, maker, harbor, model, modelYear, buyPrice);
        freezeInputs(type, kasko, deductible, paymentOption, fromDate);
    }

    @Override
    protected void unfreezeInput() {
        unFreezeInputs(size, speed, motorsize, maker, harbor, model, modelYear, buyPrice);
        unFreezeInputs(type, kasko, deductible, paymentOption, fromDate);
    }
}