package GUI.GuiHelper;

import Insurance.Insurance;
import Person.Customer;
import Register.RegisterCustomer;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.NoSuchElementException;

import static GUI.AgentGUI.Insurance.InsuranceConfirmModuleController.*;
import static GUI.StartMain.currentCustomer;
import static GUI.StartMain.insuranceRegister;
import static Insurance.Insurance.paymentFee;

/**
 * Created by steinar on 01.05.2015.
 */
public abstract class CommonGUIMethods {

    public abstract void clearFields();

    protected abstract void setListeners();
    protected abstract void makeInsurance();
    protected abstract void initialize();
    protected abstract void addCSSValidation();
    //protected abstract void setCustomer();
    protected abstract boolean checkValidation();

    protected void saveInsurance(Insurance insurance) {
        Customer customer =  currentCustomer.getPerson();
        if (customer == null)
            throw new NoSuchElementException("Har du glemt å finne eller opprette kunden?");
        if ( insuranceRegister.add(insurance) ) {
            customer.addInsuranceNumber(insurance.getCasenumber());

            //todo: find a better way for this
            currentCustomer.reset();
            currentCustomer.setProperty(customer);

            //currentCustomer.getPersonProperty().notifyAll(); //IllegalMonitorSateException????
        } else
            throw new IllegalArgumentException("denne finnes allerede i registeret");
    }

    protected void showPremium(Insurance insurance) {
        int paymentTermins = insurance.getPaymentOption().getValue();
        yearlyPremiumLabel.setValue( insurance.getAnnualPremium() );
        totalFeeLabel.setValue( paymentFee * paymentTermins );
        paymentEachTerminLabel.setValue( ( insurance.getAnnualPremium() + paymentFee * paymentTermins) / paymentTermins );
    }


    protected final void addComboboxListener(ComboBox... comboBoxes) {
        for( ComboBox comboBox : comboBoxes )
            comboBox.getSelectionModel().selectedItemProperty().addListener(observable -> makeInsurance() );
/*            comboBox.valueProperty().addListener(observable -> {
                if (!comboBox.focusedProperty().getValue())
                    makeInsurance();
            });*/
    }


    // setOnAction -> enter key. focusedProperty -> if lostfocus -> try to show price for insurance
    protected final void addTextfieldListener(TextField... textFields) {
        for (TextField textField: textFields) {
            //textField.setOnAction(event -> makeInsurance());
            textField.focusedProperty().addListener(observable -> {
                if (!textField.focusedProperty().getValue())
                    makeInsurance();
            });
        }
    }

    protected int parseInt(TextField textField) {
        return Integer.parseInt(textField.getText());
    }

    protected Customer getCustomerOrDummyCustomer() {
        Customer customer = currentCustomer.getPerson();
        if (customer == null)
            customer = RegisterCustomer.tempCustomer;
        return customer;
    }
}