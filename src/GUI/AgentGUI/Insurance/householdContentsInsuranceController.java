package GUI.AgentGUI.Insurance;

import GUI.GuiHelper.CommonGUIMethods;
import GUI.GuiHelper.RegEX;
import GUI.StartMain;
import Insurance.Helper.PaymentOption;
import Insurance.Insurance;
import Insurance.Property.HouseholdContents;
import Person.Customer;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.awt.*;
import java.time.LocalDate;
import java.util.stream.IntStream;

public final class householdContentsInsuranceController implements CommonGUIMethods
{
    @FXML
    private TextField adress;
    @FXML
    private TextField citynumber;
    @FXML
    private TextField city;
    @FXML
    private ComboBox<Integer> roomnumbers;
    @FXML
    private ComboBox<Integer> personnumber;
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
    private static HouseholdContents insurance;

    @FXML
    public void initialize()
    {

        IntStream.range(1, 11).forEach(numbers::add);
        roomnumbers.setItems(numbers);
        personnumber.setItems(numbers);

        deductible.setItems(Insurance.deductablenumbers);
        paymentOption.setItems(Insurance.paymentOptionNummber);

        addCSSValidation();
        clearFields();
        setListeners();
        //todo: if insurance selected -> set info into page
        if (StartMain.currentCustomer.getPersonProperty().isNotNull().get())
        {
            //if chosen customer gues that he will sign insurance where he currently lives
            Customer customer = StartMain.currentCustomer.getPerson();
            adress.setText(customer.getAdress());
            citynumber.setText(String.valueOf(customer.getCitynumbr()));
            city.setText(customer.getCity());
            amount.setText("500000");
        }
    }

    @Override
    public void clearFields()
    {
        resetTextFields(adress, citynumber, city, amount);

        //explanation -> https://thierrywasyl.wordpress.com/2014/02/09/update-your-scene-in-javafx/
        Runnable clear = () ->
        {
            fromDate.setValue(LocalDate.now());
            deductible.setValue(deductible.getItems().get(1));
            paymentOption.setValue(paymentOption.getItems().get(0));
            roomnumbers.setValue(roomnumbers.getItems().get(1));
            personnumber.setValue(personnumber.getItems().get(0));
        };

        if(Platform.isFxApplicationThread())
            clear.run();
        else
            Platform.runLater(clear);
    }

    @Override
    public void addCSSValidation()
    {
        RegEX.addCSSTextValidation(adress, RegEX.isAdress());
        RegEX.addCSSTextValidation(citynumber, RegEX.isNumber(4));
        RegEX.addCSSTextValidation(city, RegEX.isLetters());
        RegEX.addCSSTextValidation(amount, RegEX.isNumber());
    }
    private boolean checkValidation()
    {
        if (adress.getLength() < 3 && adress.getPseudoClassStates().isEmpty() )
            return false;
        if ( !citynumber.getPseudoClassStates().isEmpty() )
            return false;
        if (city.getLength() < 2 && city.getPseudoClassStates().isEmpty())
            return false;
        if (amount.getLength() < 2 && amount.getPseudoClassStates().isEmpty())
            return false;
        return true;
    }

    private void setListeners()
    {
        AgentInsuranceController.emptyscreen.addListener(observable -> {
            SimpleBooleanProperty bool = (SimpleBooleanProperty) observable;
            if (bool.get())
                clearFields();
        });
        makeComboboxListener(roomnumbers, personnumber, deductible, paymentOption);
    }

    private void makeComboboxListener(ComboBox... comboBoxes)
    {
        for( ComboBox comboBox : comboBoxes ) {
            comboBox.valueProperty().addListener(listener -> makeInsurance());
        }
    }

    private void makeInsurance() {
        if (!checkValidation())
            return;
        ObservableList<PaymentOption> list = FXCollections.observableArrayList(PaymentOption.values());
        FXCollections.reverse(list);

        PaymentOption selectedPayment = list.get( paymentOption.getSelectionModel().getSelectedIndex() );

        insurance = new HouseholdContents( adress.getText(), parseInt(citynumber), city.getText(), roomnumbers.getValue(),
                personnumber.getValue(), fromDate.getValue(), parseInt(amount), "somePolicy",
                StartMain.currentCustomer.getPerson(), selectedPayment, deductible.getValue());
        showPremium();
    }

    private void showPremium() {
        int paymentTermins = insurance.getPaymentOption().getValue();
        InsuranceConfirmModuleController.yearlyPremiumLabel.setValue( insurance.getAnnualPremium() );
        InsuranceConfirmModuleController.totalFeeLabel.setValue( Insurance.paymentFee * paymentTermins );
        InsuranceConfirmModuleController.paymentEachTerminLabel.setValue( ( insurance.getAnnualPremium() + Insurance.paymentFee * paymentTermins) / paymentTermins );
    }

    private int parseInt(TextField textField) {
        return Integer.parseInt(textField.getText());
    }
}