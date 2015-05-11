package GUI.AgentGUI.Insurance.Modules;

import GUI.GuiHelper.CommonInsuranceMethods;
import GUI.GuiHelper.RegEX;
import Insurance.Helper.PaymentOption;
import Insurance.Property.HouseholdContentsInsurance;
import Person.Customer;
import Register.RegisterCustomer;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
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

import static GUI.AgentGUI.Insurance.AgentInsuranceController.insuranceChoiceListener;
import static GUI.AgentGUI.Insurance.InsuranceConfirmModuleController.confirmOrderButton;
import static GUI.CurrentObjectListeners.CurrentInsurance.insuranceListener;
import static GUI.CurrentObjectListeners.CustomerListener.currentCustomer;
import static GUI.GuiHelper.RegEX.*;
import static Insurance.Insurance.deductablenumbers;
import static Insurance.Insurance.paymentOptions;

public final class HouseholdContentsModuleController extends CommonInsuranceMethods
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

        if (insuranceListener.get() instanceof HouseholdContentsInsurance) {
            loadCurrentInsurance();
            showInsurance();
        } else {
            clearFields();
            setCustomer();
        }
        setListeners();
        addCSSValidation();
    }

    @Override
    public void clearFields() {
        if (adress.disabledProperty().get())
            unfreezeInput();
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

    protected void setCustomer() {
        Customer customer = getCustomerOrDummyCustomer();

        adress.setText(customer.getAdress());
        citynumber.setText(String.valueOf(customer.getCitynumber()));
        city.setText(customer.getCity());
        amount.setText("500000");
    }

    @Override
    protected boolean checkValidation() {
        if (!validationIsOk(3).test(adress))
            return false;
        if (pseudoOK.test(citynumber))
            return false;
        if (!validationIsOk(2).test(city))
            return false;
        if (!validationIsOk(4).test(amount))
            return false;
        return true;
    }

    @Override
    protected void setListeners() {
        addComboboxListener(numberOfrooms, numberOfPersons, deductible, paymentOption);
        addTextfieldListener(amount);
        setEmptyScreenListener();
        setCurrentInsuranceListener(HouseholdContentsInsurance.class);

        confirmOrderButton.addListener(observable -> {
            BooleanProperty bool = (BooleanProperty) observable;
            if (bool.get() && insuranceChoiceListener.getString().equals("Innbo")) {
                System.out.println("saveinsurance");
                makeInsurance();
                saveInsurance(insurance);
            }
        });

        insuranceChoiceListener.getProperty().addListener(observable -> {
            SimpleStringProperty property = (SimpleStringProperty) observable;
            if (property.get().equals("Innbo")) {
                setCustomer();
                makeInsurance();
            }
        });
    }

    @Override
    protected void loadCurrentInsurance() {
        HouseholdContentsModuleController.insurance  = (HouseholdContentsInsurance) insuranceListener.get();
    }
    @Override
    protected void showInsurance() {
        if ( insurance.getEndDate() != null )
            freezeInput();

        adress.setText(insurance.getAddress());
        city.setText(insurance.getCity());
        setInt(citynumber, insurance.getCitynumber());
        setInt(amount, insurance.getItemValue());
        numberOfPersons.setValue(insurance.getRoomMates());
        numberOfrooms.setValue(insurance.getRoomCount());
        deductible.setValue(insurance.getDeductable());
        paymentOption.setValue(insurance.getPaymentOption().getName());
        fromDate.setValue(insurance.getFromDate());
    }

    @Override
    protected void makeInsurance() {
        if (!checkValidation())
            return;

        PaymentOption selectedPayment = paymentOptions.get( paymentOption.getSelectionModel().getSelectedIndex() );
        try {
            insurance = new HouseholdContentsInsurance(adress.getText(), parseInt(citynumber), city.getText(), numberOfrooms.getValue(),
                    numberOfPersons.getValue(), fromDate.getValue(), parseInt(amount), "somePolicy",
                    currentCustomer.get(), selectedPayment, deductible.getValue());
            showPremium(insurance);
        } catch (Exception e) {
            HouseholdContentsInsurance tempInsurance = new HouseholdContentsInsurance(adress.getText(), parseInt(citynumber), city.getText(), numberOfrooms.getValue(),
                    numberOfPersons.getValue(), fromDate.getValue(), parseInt(amount), "somePolicy",
                    RegisterCustomer.tempCustomer, selectedPayment, deductible.getValue());
            showPremium(tempInsurance);
        }
    }

    @Override
    protected void freezeInput() {
        freezeInputs(adress, city, citynumber, amount);
        freezeInputs(numberOfPersons, numberOfrooms, deductible, paymentOption, fromDate);

    }

    @Override
    protected void unfreezeInput() {
        unFreezeInputs(adress, city, citynumber, amount);
        unFreezeInputs(numberOfPersons, numberOfrooms, deductible, paymentOption, fromDate);
    }
}