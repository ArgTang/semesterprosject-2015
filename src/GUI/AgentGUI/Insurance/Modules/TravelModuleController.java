package GUI.AgentGUI.Insurance.Modules;

/**
 * Created by steinar on 27.04.2015.
 */

import GUI.GuiHelper.CommonPrivateGUIMethods;
import GUI.GuiHelper.CommonPublicGUIMethods;
import Insurance.Helper.PaymentOption;
import Insurance.TravelInsurance;
import Register.RegisterCustomer;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.util.stream.Collectors;

import static GUI.AgentGUI.Insurance.AgentInsuranceController.emptyscreen;
import static GUI.AgentGUI.Insurance.InsuranceConfirmModuleController.confirmOrderButton;
import static GUI.StartMain.currentCustomer;
import static Insurance.Insurance.paymentOptions;

public final class TravelModuleController extends CommonPrivateGUIMethods implements CommonPublicGUIMethods
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
    @Override
    protected void initialize() {
        paymentOption.setItems(paymentOptions.stream()
                                             .map(PaymentOption::getName)
                                             .collect(Collectors.toCollection(FXCollections::observableArrayList)));

        types.setAll("Reise", "Reise pluss (Familie)");
        type.setItems(types);

        clearFields();
        setListeners();
        makeInsurance();
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
    protected void setListeners() {
        emptyscreen.addListener(observable -> {
            SimpleBooleanProperty bool = (SimpleBooleanProperty) observable;
            if (bool.get())
                clearFields();
        });

        confirmOrderButton.addListener(observable -> {
            BooleanProperty bool = (BooleanProperty) observable;
            if (bool.get()) {
                makeInsurance();
                saveInsurance(insurance);
            }
        });

        addComboboxListener(type, paymentOption);
    }

    @Override
    protected void makeInsurance() {

        PaymentOption selectedPayment = paymentOptions.get(paymentOption.getSelectionModel().getSelectedIndex());
        boolean pluss = type.getSelectionModel().getSelectedIndex() == 1;
        try {
            insurance = new TravelInsurance(fromDate.getValue(), "something", currentCustomer.getPerson(), selectedPayment, pluss);
            showPremium(insurance);
        } catch (Exception e) {
            //we dont care that the error gets ignored
            TravelInsurance testinsurance = new TravelInsurance(fromDate.getValue(), "something", RegisterCustomer.tempCustomer, selectedPayment, pluss);
            showPremium(testinsurance);
        }
    }

    //no textfields -> no action
    @Override
    public void addCSSValidation() { throw  new NoSuchMethodError("TravelInsirance have no textfields"); }
    @Override
    protected boolean checkValidation() { throw  new NoSuchMethodError("TravelInsurance have no textfields"); }
}