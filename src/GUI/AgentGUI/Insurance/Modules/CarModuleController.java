package GUI.AgentGUI.Insurance.Modules;

import GUI.GuiHelper.CommonInsuranceMethods;
import GUI.GuiHelper.RegEX;
import Insurance.Helper.PaymentOption;
import Insurance.Insurance;
import Insurance.Vehicle.CarInsurance;
import Register.RegisterCustomer;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.stream.Collectors;

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
public final class CarModuleController extends CommonInsuranceMethods
{
    @FXML
    private TextField licenceNumber;
    @FXML
    private TextField km;
    @FXML
    private ComboBox<String> maker;
    @FXML
    private TextField showmaker;
    @FXML
    private TextField model;
    @FXML
    private TextField horsePower;
    @FXML
    private TextField modelYear;
    @FXML
    private TextField color;
    @FXML
    private TextField buyPrice;

    @FXML
    private DatePicker fromDate;
    @FXML
    private TextField showfromDate;
    @FXML
    private CheckBox ageRequirements;
    @FXML
    private ComboBox<String> kasko;
    @FXML
    private TextField showkasko;
    @FXML
    private ComboBox<Integer> bonus;
    @FXML
    private TextField showbonus;
    @FXML
    private ComboBox<String> yearlyKM;
    @FXML
    private TextField showyearlyKM;
    @FXML
    private ComboBox<Integer> deductible;
    @FXML
    private TextField showdeductible;
    @FXML
    private ComboBox<String> paymentOption;
    @FXML
    private TextField showpaymentOption;


    private static CarInsurance insurance;

    @FXML
    @Override
    protected void initialize() {
        freezeTextfields(showfromDate, showbonus, showdeductible, showkasko, showmaker, showpaymentOption, showyearlyKM);
        //todo: some of these might be used for more insurances -> move into VehicleInsurance Class
        kasko.setItems(kaskoValues);
        bonus.setItems(bonusValues);
        yearlyKM.setItems(CarInsurance.kmValues);
        maker.setItems(CarInsurance.makers);

        deductible.setItems(deductablenumbers);
        paymentOption.setItems(paymentOptions.stream()
                                             .map(PaymentOption::getName)
                                             .collect(Collectors.toCollection(FXCollections::observableArrayList)));

        if (currentInsurance.get() instanceof CarInsurance) {
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
        hideNode(showfromDate, showbonus, showdeductible, showkasko, showmaker, showpaymentOption, showyearlyKM);
        showNode(fromDate, bonus, deductible, kasko, maker, paymentOption, yearlyKM);
        unfreezeTextfields(licenceNumber, km, model, horsePower, modelYear, color, buyPrice);
        resetTextFields(licenceNumber, km, model, horsePower, modelYear, color, buyPrice);
        fromDate.setValue(LocalDate.now());

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
    protected boolean checkValidation() {
        if (!validationIsOk(4).test(licenceNumber))
            return false;

        if (!validationIsOk(2).test(model))
            return false;

        if (pseudoOK.test(modelYear))
            return false;

        if (!validationIsOk(1).test(color))
            return false;

        if (!validationIsOk(1).test(km))
            return false;

        if (!validationIsOk(2).test(horsePower))
            return false;

        if (!validationIsOk(3).test(buyPrice))
            return false;

        return true;
    }

    @Override
    protected void setListeners() {
        addComboboxListener(kasko, bonus, yearlyKM, deductible, paymentOption);
        addTextfieldListener(modelYear, buyPrice);
        setEmptyScreenListener();
        setCurrentInsuranceListener(CarInsurance.class);

        confirmOrderButton.addListener(observable -> {
            BooleanProperty bool = (BooleanProperty) observable;
            if (bool.get() && insuranceChoiceListener.getString().equals("Bil")) {
                makeInsurance();
                saveInsurance(insurance);
            }
        });

        insuranceChoiceListener.getProperty().addListener(observable -> {
            SimpleStringProperty property = (SimpleStringProperty) observable;
            if (property.get().equals("Bil"))
                makeInsurance();
        });
    }

    @Override
    protected void loadCurrentInsurance() {
        CarModuleController.insurance  = (CarInsurance) currentInsurance.get();
    }

    @Override
    protected void showInsurance() {
        showNode(showfromDate, showbonus, showdeductible, showkasko, showmaker, showpaymentOption, showyearlyKM);
        hideNode(fromDate, bonus, deductible, kasko, maker, paymentOption, yearlyKM);
        freezeTextfields(licenceNumber, model, modelYear, color, km, horsePower, buyPrice);

        licenceNumber.setText(insurance.getLicenceNumber());
        model.setText(insurance.getModel());
        setInt(modelYear, insurance.getProductionYear());
        color.setText(insurance.getColor());
        setInt(km, insurance.getTotalKilometer());
        setInt(horsePower, insurance.getHorsePower());
        setInt(buyPrice, insurance.getItemValue());

        showmaker.setText(insurance.getMaker());
        showkasko.setText(insurance.getKasko());
        setInt(showdeductible, insurance.getDeductable());
        showpaymentOption.setText(insurance.getPaymentOption().getName());
        showfromDate.setText(insurance.getFromDate().toString());
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
            insurance = new CarInsurance(fromDate.getValue(), parseInt(buyPrice), "somePolicy", currentCustomer.get(), selectedPayment,
                    maker.getValue(), model.getText(), parseInt(modelYear), parseInt(km), parseInt(horsePower), licenceNumber.getText(),
                    color.getText(), Integer.parseInt(deductible.getValue().toString()), bonus, kasko, yearlyKM);
            showPremium(insurance);
        } catch (Exception expected) {
            Insurance tempinsurance = new CarInsurance(fromDate.getValue(), parseInt(buyPrice), "somePolicy",
                    RegisterCustomer.tempCustomer, selectedPayment, maker.getValue(), model.getText(),
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