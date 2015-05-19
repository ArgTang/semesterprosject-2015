package GUI.GuiHelper;

import Insurance.Insurance;
import Person.Customer;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.NoSuchElementException;

import static GUI.AgentGUI.Insurance.AgentInsuranceController.emptyscreenButton;
import static GUI.AgentGUI.Insurance.InsuranceConfirmModuleController.*;
import static GUI.StartMain.*;
import static Insurance.Insurance.paymentFee;

/**
 *
 * Alot of GUI classes have similar functions and have need for some common utilityfunctions
 * we define these here
 * Created by steinar on 09.05.2015.
 */
public abstract class CommonInsuranceMethods extends CommonGUIMethods
{
    protected abstract void loadCurrentInsurance();
    protected abstract void showInsurance();
    protected abstract void makeInsurance();

    protected void saveInsurance(Insurance insurance) {
        Customer customer = currentCustomer.get();
        if (customer == null)
            throw new NoSuchElementException("Har du glemt å finne eller opprette kunden?");
        if ( insuranceRegister.add(insurance) ) {
            customer.addInsuranceNumber(insurance.getCasenumber());

            //todo: find a better way for this
            currentCustomer.reset();
            currentCustomer.set(customer);

            //currentCustomer.getPersonProperty().notifyAll(); //IllegalMonitorSateException????
        } else
            throw new IllegalArgumentException("denne finnes allerede i registeret");
    }

    protected void showPremium(Insurance insurance) {
        int paymentTermins = insurance.getPaymentOption().getValue();
        yearlyPremiumLabel.setValue( insurance.getAnnualPremium() );
        totalFeeLabel.setValue( paymentFee * paymentTermins );
        paymentEachTerminLabel.setValue( (insurance.getAnnualPremium() + paymentFee * paymentTermins) / paymentTermins );
    }

    protected <neededClass extends Insurance> void setCurrentInsuranceListener(final Class<neededClass> someClass) {
        currentInsurance.getProperty().addListener(listener -> {
            Boolean isNotNull = currentInsurance.getProperty().isNotNull().get();
            if (isNotNull && someClass.equals(currentInsurance.get().getClass())) {
                loadCurrentInsurance();
                showInsurance();
            }
        });
    }

    protected void setInsurancechoiceListeners(String typeofclassString) {
        changeWindowListener.getProperty().addListener( observable -> {
            SimpleStringProperty property = (SimpleStringProperty) observable;
            if (property.get().equals(typeofclassString)) {
                makeInsurance();
            }
        });
    }

    protected void setEmptyScreenListener() {
        emptyscreenButton.addListener(observable -> {
            SimpleBooleanProperty bool = (SimpleBooleanProperty) observable;
            if (bool.get())
                clearFields();
        });
    }

    protected final void addComboboxListener(ComboBox... comboBoxes) {
        for( ComboBox comboBox : comboBoxes )
            comboBox.getSelectionModel().selectedItemProperty().addListener(observable -> makeInsurance());
    }

    // setOnAction -> enter key. focusedProperty -> if lostfocus -> try to show price for insurance
    protected final void addTextfieldListener(TextField... textFields) {
        for (TextField textField: textFields) {
            //textField.setOnAction(event -> makeInsurance());
            textField.focusedProperty().addListener( observable -> {
                if (!textField.focusedProperty().getValue())
                    makeInsurance();
            });
        }
    }

    public static void setBoldFont(Node... nodes) {
        for(Node node: nodes)
            node.setStyle("-fx-font-weight: bold;");
    }

    protected void hideNode(Node... nodes) {
        for (Node node: nodes)
            node.setVisible(false);
    }
    protected void showNode(Node... nodes) {
        for (Node node: nodes)
            node.setVisible(true);
    }

    protected void freezeTextfields(TextField... textFields) {
        for (TextField textField: textFields)
            textField.setEditable(false);
    }
    protected void unfreezeTextfields(TextField... textFields) {
        for (TextField textField: textFields)
            textField.setEditable(true);
    }
}