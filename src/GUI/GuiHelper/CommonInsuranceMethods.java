package GUI.GuiHelper;

import Insurance.Insurance;
import Person.Customer;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.NoSuchElementException;

import static GUI.AgentGUI.Insurance.InsuranceConfirmModuleController.paymentEachTerminLabel;
import static GUI.AgentGUI.Insurance.InsuranceConfirmModuleController.totalFeeLabel;
import static GUI.AgentGUI.Insurance.InsuranceConfirmModuleController.yearlyPremiumLabel;
import static GUI.StartMain.currentCustomer;
import static GUI.StartMain.insuranceRegister;
import static Insurance.Insurance.paymentFee;

/**
 * Created by steinar on 09.05.2015.
 */
public abstract class CommonInsuranceMethods extends CommonGUIMethods {

    //todo: is there a eay to make this function so that i can spesify what type of Insurance when i @Override
    protected abstract void setInsurance();
    protected abstract void makeInsurance();
    protected abstract void freezeInput();
    protected abstract void unfreezeInput();

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


    /**
     * This function disables inputfields.
     * Both TextFields and ComboboxesWork :)
     */
    protected void freezeInputs(Node... UserEditableNodes) {
        freezer(UserEditableNodes, false);
    }
    /**
     * This function enables inputfields.
     * Both TextFields and ComboboxesWork :)
     */
    protected void unFreezeInputs(Node... UserEditableNodes) {
        freezer(UserEditableNodes, true);
    }

    private void freezer(Node[] UserEditableNodes, boolean value){
        for (Node UserEditableNode: UserEditableNodes)
            UserEditableNode.setDisable(value);
    }
}
