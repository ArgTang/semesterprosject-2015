package GUI.AgentGUI.Insurance.Modules;

import GUI.GuiHelper.CommonGUIMethods;
import GUI.GuiHelper.RegEX;
import Insurance.Helper.PaymentOption;
import Insurance.Insurance;
import Insurance.Vehicle.CarInsurance;
import Register.RegisterCustomer;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.stream.Collectors;

import static GUI.AgentGUI.Insurance.AgentInsuranceController.emptyscreenButton;
import static GUI.AgentGUI.Insurance.AgentInsuranceController.insuranceChoiceListener;
import static GUI.AgentGUI.Insurance.InsuranceConfirmModuleController.*;
import static GUI.GuiHelper.RegEX.*;
import static GUI.StartMain.currentCustomer;
import static GUI.StartMain.currentInsurance;
import static Insurance.Insurance.*;
import static Insurance.Vehicle.CarInsurance.bonusValues;
import static Insurance.Vehicle.CarInsurance.kaskoValues;

/**
 * Created by steinar on 17.04.2015.
 */
public final class CarModuleController extends CommonGUIMethods
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

    CarInsurance insurance;

    @FXML
    @Override
    protected void initialize() {
        //todo: some of these might be used for more insurances -> move into Vehicle Class
        kasko.setItems(kaskoValues);
        bonus.setItems(bonusValues);
        yearlyKM.setItems(CarInsurance.kmValues);
        maker.setItems(CarInsurance.makers);

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
        resetTextFields(licenceNumber, km, model, horsePower, modelYear, color, buyPrice);
        fromDate.setValue(LocalDate.now());
//        ageRequirements.setIndeterminate(false);

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
        RegEX.addCSSTextValidation(modelYear, isNumberWithLength(4));
        RegEX.addCSSTextValidation(color, isLetters());
        addCSSTextValidation(isNumber(), km, horsePower, buyPrice);
    }

    @Override
    protected void setCustomer() {

    }

    @Override
    protected boolean checkValidation() {
        if (validationIsOk(4).test(licenceNumber))
            return false;

        if (validationIsOk(2).test(model))
            return false;

        if (pseudoOK.test(modelYear))
            return false;

        if (validationIsOk(1).test(color))
            return false;

        if (validationIsOk(1).test(km))
            return false;

        if (validationIsOk(2).test(horsePower))
            return false;

        if (validationIsOk(3).test(buyPrice))
            return false;

        return true;
    }

    @Override
    protected void setListeners() {

        addComboboxListener(maker, kasko, bonus, yearlyKM, deductible, paymentOption);
        addTextfieldListener(licenceNumber, model, modelYear, color, buyPrice);

        currentInsurance.getInsuranceProperty().addListener(
                observable -> {
                    ObjectProperty<Insurance> insurance = (ObjectProperty<Insurance>) observable;
                    if (insurance.isNotNull().get()) {
                        //todo: set insurance
                    } else
                        clearFields();
                }
        );

        emptyscreenButton.addListener(observable -> {
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

        PaymentOption selectedPayment = paymentOptions.get(paymentOption.getSelectionModel().getSelectedIndex());
        int bonus = bonusValues.get(this.bonus.getSelectionModel().getSelectedIndex());
        String kasko = kaskoValues.get(this.kasko.getSelectionModel().getSelectedIndex());
        String yearlyKM = CarInsurance.kmValues.get(this.yearlyKM.getSelectionModel().getSelectedIndex());

        try {
            insurance = new CarInsurance(fromDate.getValue(), parseInt(buyPrice), "comePolicy", currentCustomer.getPerson(), selectedPayment,
                    maker.getValue().toString(), model.getText(), parseInt(modelYear), parseInt(km), parseInt(horsePower), licenceNumber.getText(),
                    color.getText(), Integer.parseInt(deductible.getValue().toString()), bonus, kasko, yearlyKM);
            showPremium(insurance);
        } catch (Exception expected) {
            Insurance tempinsurance = new CarInsurance(fromDate.getValue(), parseInt(buyPrice), "comePolicy",
                    RegisterCustomer.tempCustomer, selectedPayment, maker.getValue().toString(), model.getText(),
                    parseInt(modelYear), parseInt(km), parseInt(horsePower), licenceNumber.getText(),
                    color.getText(), Integer.parseInt(deductible.getValue().toString()), bonus, kasko, yearlyKM);
            showPremium(tempinsurance);
        }
    }

    @Override
    protected void showPremium(Insurance insurance) {
        CarInsurance carInsurance = (CarInsurance) insurance;
        int paymentTermins = insurance.getPaymentOption().getValue();
        double bonus = 1-(carInsurance.getCurrentBonus()/100.0);

        yearlyPremiumLabel.setValue( insurance.getAnnualPremium() * bonus  );
        totalFeeLabel.setValue( paymentFee * paymentTermins );
        paymentEachTerminLabel.setValue(  ( insurance.getAnnualPremium() * bonus + paymentFee * paymentTermins) / paymentTermins );
        bonusValueLabel.setValue(String.valueOf( 100 - (bonus * 100) ) );
    }
}