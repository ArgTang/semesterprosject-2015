package GUI.GuiHelper;

import Insurance.Insurance;
import Person.Customer;
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
public abstract class CommonPrivateGUIMethods {

    protected abstract void setListeners();
    protected abstract void makeInsurance();
    protected abstract void initialize();
    protected abstract void addCSSValidation();
    protected abstract boolean checkValidation();

    protected void saveInsurance(Insurance insurance) {
        Customer customer =  currentCustomer.getPerson();
        if (customer == null)
            throw new NoSuchElementException("Har du glemt å finne eller opprette kunden?");
        if ( insuranceRegister.add(insurance) )
            customer.addInsuranceNumber(insurance.getCasenumber());
        else
            throw new IllegalArgumentException("denne finnes allerede i registeret");
    }

    protected void showPremium(Insurance insurance) {
        int paymentTermins = insurance.getPaymentOption().getValue();
        yearlyPremiumLabel.setValue( insurance.getAnnualPremium() );
        totalFeeLabel.setValue( paymentFee * paymentTermins );
        paymentEachTerminLabel.setValue( ( insurance.getAnnualPremium() + paymentFee * paymentTermins) / paymentTermins );
    }

    protected void addComboboxListener(ComboBox... comboBoxes) {
        for( ComboBox comboBox : comboBoxes )
            comboBox.getSelectionModel().selectedItemProperty().addListener(observable -> makeInsurance() );
            //comboBox.valueProperty().addListener(observable -> makeInsurance());
    }

    protected int parseInt(TextField textField) {
        return Integer.parseInt(textField.getText());
    }
}