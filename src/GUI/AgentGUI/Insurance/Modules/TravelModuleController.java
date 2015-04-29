package GUI.AgentGUI.Insurance.Modules;

/**
 * Created by steinar on 27.04.2015.
 */

import GUI.AgentGUI.Insurance.AgentInsuranceController;
import GUI.AgentGUI.Insurance.InsuranceConfirmModuleController;
import GUI.GuiHelper.CommonGUIMethods;
import GUI.StartMain;
import Insurance.Helper.PaymentOption;
import Insurance.Insurance;
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
        if ( StartMain.currentCustomer.getPersonProperty().isNotNull().get() ) {
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
        Customer customer =  StartMain.currentCustomer.getPerson();
        if ( StartMain.insuranceRegister.add(insurance) )
            customer.addInsuranceNumber(insurance.getCasenumber());
    }

    private void setListeners() {
        AgentInsuranceController.emptyscreen.addListener(observable -> {
            SimpleBooleanProperty bool = (SimpleBooleanProperty) observable;
            if (bool.get())
                clearFields();
        });

        InsuranceConfirmModuleController.confirmOrderButton.addListener(observable -> {
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
        ObservableList<PaymentOption> list = FXCollections.observableArrayList(PaymentOption.values());
        FXCollections.reverse(list);

        PaymentOption selectedPayment = list.get(paymentOption.getSelectionModel().getSelectedIndex());
        boolean pluss = type.getSelectionModel().getSelectedIndex() == 1;
        insurance = new TravelInsurance(fromDate.getValue(), "something", StartMain.currentCustomer.getPerson(), selectedPayment, pluss);
        showPremium();
    }

    private void showPremium() {
        int paymentTermins = insurance.getPaymentOption().getValue();
        InsuranceConfirmModuleController.yearlyPremiumLabel.setValue( insurance.getAnnualPremium() );
        InsuranceConfirmModuleController.totalFeeLabel.setValue( Insurance.paymentFee * paymentTermins );
        InsuranceConfirmModuleController.paymentEachTerminLabel.setValue( ( insurance.getAnnualPremium() + Insurance.paymentFee * paymentTermins) / paymentTermins );
    }
}