package GUI.AgentGUI.Insurance.Modules;

/**
 * Created by steinar on 27.04.2015.
 */

import GUI.AgentGUI.Insurance.AgentInsuranceController;
import GUI.GuiHelper.CommonGUIMethods;
import Insurance.Helper.PaymentOption;
import Insurance.TravelInsurance;
import Person.Customer;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

import static GUI.AgentGUI.Insurance.AgentInsuranceController.emptyscreen;
import static GUI.AgentGUI.Insurance.InsuranceConfirmModuleController.*;
import static GUI.StartMain.*;
import static Insurance.Insurance.paymentFee;

public class TravelModuleController implements CommonGUIMethods
{
    @FXML
    private ComboBox<String> type;
    @FXML
    private DatePicker fromDate;
    @FXML
    private ComboBox<String> paymentOption;

    private static final ObservableList<String> types = FXCollections.observableArrayList();
    private static TravelInsurance insurance;

    @FXML
    public void initialize() {
        paymentOption.setItems(AgentInsuranceController.paymentOptionNummber);
        types.setAll("Reise", "Reise pluss (Familie)");
        type.setItems(types);

        clearFields();
        setListeners();
        if ( currentCustomer.getPersonProperty().isNotNull().get() ) {
            makeInsurance();
        }
    }

    @Override
    public void clearFields() {
        fromDate.setValue(LocalDate.now());

        //explanation -> https://thierrywasyl.wordpress.com/2014/02/09/update-your-scene-in-javafx/
        Runnable clear = () -> {
            paymentOption.setValue( paymentOption.getItems().get(0) );
            type.setValue( type.getItems().get(0) );};

        if(Platform.isFxApplicationThread())
            clear.run();
        else
            Platform.runLater(clear);
    }

    @Override
    public void addCSSValidation() {
        //no textfields -> no action
    }

    public void saveInsurance() {
        Customer customer =  currentCustomer.getPerson();
        if ( insuranceRegister.add(insurance) )
            customer.addInsuranceNumber(insurance.getCasenumber());
    }

    private void setListeners() {
        emptyscreen.addListener(observable -> {
            SimpleBooleanProperty bool = (SimpleBooleanProperty) observable;
            if (bool.get())
                clearFields();
        });

        confirmOrderButton.addListener(observable -> {
            BooleanProperty bool = (BooleanProperty) observable;
            if ( bool.get() ) {
                makeInsurance();
                saveInsurance();
            }
        });

        addComboboxListener(type);
        addComboboxListener(paymentOption);
    }

    private void addComboboxListener(ComboBox... comboBoxes) {
        for( ComboBox comboBox : comboBoxes )
            comboBox.getSelectionModel().selectedItemProperty().addListener(observable -> makeInsurance() );
    }

    private void makeInsurance() {
        if (currentCustomer.getPersonProperty().isNull().get())
            return;
        ObservableList<PaymentOption> list = FXCollections.observableArrayList(PaymentOption.values());
        FXCollections.reverse(list);

        PaymentOption selectedPayment = list.get(paymentOption.getSelectionModel().getSelectedIndex());
        boolean pluss = type.getSelectionModel().getSelectedIndex() == 1;
        insurance = new TravelInsurance(fromDate.getValue(), "something", currentCustomer.getPerson(), selectedPayment, pluss);
        showPremium();
    }

    private void showPremium() {
        int paymentTermins = insurance.getPaymentOption().getValue();
        yearlyPremiumLabel.setValue( insurance.getAnnualPremium() );
        totalFeeLabel.setValue( paymentFee * paymentTermins );
        paymentEachTerminLabel.setValue( ( insurance.getAnnualPremium() + paymentFee * paymentTermins) / paymentTermins );
    }
}