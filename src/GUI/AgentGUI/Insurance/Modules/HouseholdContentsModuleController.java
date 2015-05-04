package GUI.AgentGUI.Insurance.Modules;

import GUI.GuiHelper.CommonGUIMethods;
import GUI.GuiHelper.RegEX;
import Insurance.Helper.PaymentOption;
import Insurance.Property.HouseholdContentsInsurance;
import Person.Customer;
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

import java.awt.*;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static GUI.AgentGUI.Insurance.AgentInsuranceController.emptyscreenButton;
import static GUI.AgentGUI.Insurance.AgentInsuranceController.insuranceChoiceListener;
import static GUI.AgentGUI.Insurance.InsuranceConfirmModuleController.confirmOrderButton;
import static GUI.GuiHelper.RegEX.*;
import static GUI.StartMain.currentCustomer;
import static Insurance.Insurance.deductablenumbers;
import static Insurance.Insurance.paymentOptions;

public final class HouseholdContentsModuleController extends CommonGUIMethods
{
    @FXML
    private TextField adress;
    @FXML
    private TextField citynumber;
    @FXML
    private TextField city;
    @FXML
    private ComboBox<Integer> numberOfrooms;
    @FXML
    private ComboBox<Integer> numberOfPersons;
    @FXML
    private Checkbox plussForsikring;

    @FXML
    private DatePicker fromDate;
    @FXML
    private TextField amount;

    @FXML
    private ComboBox<Integer> deductible;
    @FXML
    private ComboBox<String> paymentOption;

    public static final ObservableList<Integer> numbers = FXCollections.observableArrayList();
    private static HouseholdContentsInsurance insurance;

    @FXML
    @Override
    protected void initialize() {
        IntStream.range(1, 11).forEach(numbers::add);
        numberOfrooms.setItems(numbers);
        numberOfPersons.setItems(numbers);

        deductible.setItems(deductablenumbers);
        paymentOption.setItems(paymentOptions.stream()
                                             .map(PaymentOption::getName)
                                             .collect(Collectors.toCollection(FXCollections::observableArrayList)));

        addCSSValidation();
        clearFields();
        setListeners();
        setCustomer();

        //todo: if insurance selected -> set info into page
    }

    @Override
    public void clearFields() {
        resetTextFields(adress, citynumber, city, amount);

        //explanation -> https://thierrywasyl.wordpress.com/2014/02/09/update-your-scene-in-javafx/
        Runnable clear = () -> {
            fromDate.setValue(LocalDate.now());
            deductible.setValue(deductible.getItems().get(1));
            paymentOption.setValue(paymentOption.getItems().get(0));
            numberOfrooms.setValue(numberOfrooms.getItems().get(1));
            numberOfPersons.setValue(numberOfPersons.getItems().get(0));
        };

        if(Platform.isFxApplicationThread())
            clear.run();
        else
            Platform.runLater(clear);
    }

    @Override
    public void addCSSValidation() {
        RegEX.addCSSTextValidation(adress, isAdress());
        RegEX.addCSSTextValidation(citynumber, isNumberWithLength(4));
        RegEX.addCSSTextValidation(city, isLetters());
        RegEX.addCSSTextValidation(amount, isNumber());
    }

    @Override
    protected void setCustomer() {
        Customer customer = getCustomerOrDummyCustomer();

        adress.setText(customer.getAdress());
        citynumber.setText(String.valueOf(customer.getCitynumber()));
        city.setText(customer.getCity());
        amount.setText("500000");
    }

    @Override
    protected boolean checkValidation() {
        if (validationIsOk(3).test(adress))
            return false;
        if (pseudoOK.test(citynumber))
            return false;
        if (validationIsOk(2).test(city))
            return false;
        if (validationIsOk(4).test(amount))
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

        addComboboxListener(numberOfrooms, numberOfPersons, deductible, paymentOption);

        confirmOrderButton.addListener(observable -> {
            BooleanProperty bool = (BooleanProperty) observable;
            if (insuranceChoiceListener.getPropertyString().equals("[Innbo]") && bool.get()) {
                System.out.println("saveinsurance");
                makeInsurance();
                saveInsurance(insurance);
            }
        });

        insuranceChoiceListener.getStringProperty().addListener(observable -> {
            SimpleStringProperty property = (SimpleStringProperty) observable;
            if (property.get().equals("[Innbo]"))
                makeInsurance();
        });
    }

    @Override
    protected void makeInsurance() {
        if (!checkValidation())
            return;

        PaymentOption selectedPayment = paymentOptions.get( paymentOption.getSelectionModel().getSelectedIndex() );
        try {
            insurance = new HouseholdContentsInsurance(adress.getText(), parseInt(citynumber), city.getText(), numberOfrooms.getValue(),
                    numberOfPersons.getValue(), fromDate.getValue(), parseInt(amount), "somePolicy",
                    currentCustomer.getPerson(), selectedPayment, deductible.getValue());
            showPremium(insurance);
        } catch (Exception e) {
            HouseholdContentsInsurance tempInsurance = new HouseholdContentsInsurance(adress.getText(), parseInt(citynumber), city.getText(), numberOfrooms.getValue(),
                    numberOfPersons.getValue(), fromDate.getValue(), parseInt(amount), "somePolicy",
                    RegisterCustomer.tempCustomer, selectedPayment, deductible.getValue());
            showPremium(tempInsurance);
        }
    }
}