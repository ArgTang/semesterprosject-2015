package GUI.GuiHelper;

import GUI.CurrentObjectListeners.CustomerListener;
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
import static GUI.CurrentObjectListeners.CurrentInsurance.insuranceListener;
import static GUI.CurrentObjectListeners.CustomerListener.currentCustomer;
import static GUI.StartMain.changeWindowListener;
import static GUI.StartMain.insuranceRegister;
import static Insurance.Insurance.paymentFee;

/**
 * Created by steinar on 09.05.2015.
 */
public abstract class CommonInsuranceMethods extends CommonGUIMethods
{
    //todo: is there a eay to make this function so that i can specify what type of Insurance when i @Override
    //protected abstract  <T extends Insurance> void setInsurance(T insurance);
    protected abstract void loadCurrentInsurance();
    protected abstract void showInsurance();
    protected abstract void makeInsurance();
    protected abstract void freezeInput();
    protected abstract void unfreezeInput();

    protected void saveInsurance(Insurance insurance) {
        Customer customer = currentCustomer.get();
        if (customer == null)
            throw new NoSuchElementException("Har du glemt å finne eller opprette kunden?");
        if ( insuranceRegister.add(insurance) ) {
            customer.addInsuranceNumber(insurance.getCasenumber());

            //todo: find a better way for this
            CustomerListener.reset();
            currentCustomer.set(customer);

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

    protected void setEmptyScreenListener() {
        emptyscreenButton.addListener(observable -> {
            SimpleBooleanProperty bool = (SimpleBooleanProperty) observable;
            if (bool.get())
                clearFields();
        });
    }

    protected <neededClass extends Insurance> void setCurrentInsuranceListener(final Class<neededClass> someClass) {
        insuranceListener.addListener(
            listener -> {
                Boolean isNotNull = insuranceListener.isNotNull().get();
                if (isNotNull && someClass.equals(insuranceListener.get().getClass())) {
                    loadCurrentInsurance();
                    showInsurance();
                }
        });
    }

    protected void setInsurancechoiceListeners(String typeofclassString) {
        changeWindowListener.getProperty().addListener(observable -> {
            SimpleStringProperty property = (SimpleStringProperty) observable;
            if (property.get().equals(typeofclassString)) {
                makeInsurance();
            }
        });
    }

    protected final void addComboboxListener(ComboBox... comboBoxes) {
        for( ComboBox comboBox : comboBoxes )
            comboBox.getSelectionModel().selectedItemProperty().addListener(observable -> makeInsurance() );
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

    /**
     * This function disables inputfields.
     * Both TextFields and ComboboxesWork :)
     */
    protected void freezeInputs(Node... UserEditableNodes) {
        freezer(UserEditableNodes, true);
    }
    /**
     * This function enables inputfields.
     * Both TextFields and ComboboxesWork :)
     */
    protected void unFreezeInputs(Node... UserEditableNodes) {
        freezer(UserEditableNodes, false);
    }

    private void freezer(Node[] UserEditableNodes, boolean value){
        for (Node UserEditableNode: UserEditableNodes)
            UserEditableNode.setDisable(value);
    }

    public static void setBoldFont(Node... nodes){
        for(Node node: nodes)
            node.setStyle("-fx-font-weight: bold;");
    }
}
