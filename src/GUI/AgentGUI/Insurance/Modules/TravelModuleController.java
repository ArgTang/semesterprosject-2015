package GUI.AgentGUI.Insurance.Modules;

/**
 * Created by steinar on 27.04.2015.
 */

import GUI.GuiHelper.CommonInsuranceMethods;
import Insurance.Helper.PaymentOption;
import Insurance.TravelInsurance;
import Register.RegisterCustomer;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.stream.Collectors;

import static GUI.AgentGUI.Insurance.AgentInsuranceController.insuranceChoiceListener;
import static GUI.AgentGUI.Insurance.InsuranceConfirmModuleController.confirmOrderButton;
import static GUI.CurrentObjectListeners.CurrentInsurance.insuranceListener;
import static GUI.CurrentObjectListeners.CustomerListener.currentCustomer;
import static Insurance.Insurance.paymentOptions;

public final class TravelModuleController extends CommonInsuranceMethods
{
    @FXML
    private ComboBox<String> type;
    @FXML
    private DatePicker fromDate;
    @FXML
    private ComboBox<String> paymentOption;
    @FXML
    private TextField showtype;
    @FXML
    private TextField showfromDate;
    @FXML
    private TextField showpaymentOption;



    private static final ObservableList<String> types = FXCollections.observableArrayList();
    private static TravelInsurance insurance;

    @FXML
    @Override
    protected void initialize() {
        freezeTextfields(showfromDate, showpaymentOption, showtype);
        paymentOption.setItems(paymentOptions.stream()
                                             .map(PaymentOption::getName)
                                             .collect(Collectors.toCollection(FXCollections::observableArrayList)));

        types.setAll("Reise", "Reise pluss (Familie)");
        type.setItems(types);

        if (insuranceListener.get() instanceof TravelInsurance) {
            loadCurrentInsurance();
            showInsurance();
        } else {
            clearFields();
            makeInsurance();
        }
        setListeners();
    }

    @Override
    public void clearFields() {
        hideNode(showfromDate, showpaymentOption, showtype);
        showNode(type, paymentOption, fromDate);
        //explanation -> https://thierrywasyl.wordpress.com/2014/02/09/update-your-scene-in-javafx/
        Runnable clear = () -> {
            fromDate.setValue(LocalDate.now());
            paymentOption.setValue( paymentOption.getItems().get(0) );
            type.setValue( type.getItems().get(0) );
        };

        if(Platform.isFxApplicationThread())
            clear.run();
        else
            Platform.runLater(clear);
    }

    @Override
    protected void setListeners() {
        addComboboxListener(type, paymentOption);
        setEmptyScreenListener();
        setInsurancechoiceListeners("Reise");
        setCurrentInsuranceListener(TravelInsurance.class);

        confirmOrderButton.addListener(observable -> {
            BooleanProperty bool = (BooleanProperty) observable;
            if ( bool.get() && insuranceChoiceListener.getString().equals("Reise") ) {
                System.out.println("saveinsurance");
                makeInsurance();
                saveInsurance(insurance);
            }
        });
    }

    @Override
    protected void loadCurrentInsurance() {
        TravelModuleController.insurance  = (TravelInsurance) insuranceListener.get();
    }
    @Override
    protected void showInsurance() {
        showNode(showfromDate, showpaymentOption, showtype);
        hideNode(type, paymentOption, fromDate);

        showfromDate.setText(insurance.getFromDate().toString());
        int types = insurance.isTravelPluss() ?  1:0;
        showtype.setText(type.getItems().get(types));
        showpaymentOption.setText(insurance.getPaymentOption().getName());
    }

    @Override
    protected void makeInsurance() {

        PaymentOption selectedPayment = paymentOptions.get(paymentOption.getSelectionModel().getSelectedIndex());
        boolean pluss = type.getSelectionModel().getSelectedIndex() == 1;
        try {
            insurance = new TravelInsurance(fromDate.getValue(), "something", currentCustomer.get(), selectedPayment, pluss);
            showPremium(insurance);
        } catch (Exception expected) {
            //if currentcustomer == null getPreium with tempcustomer
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