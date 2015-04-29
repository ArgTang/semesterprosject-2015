package GUI.AgentGUI.Insurance.Modules;

import GUI.AgentGUI.Insurance.AgentInsuranceController;
import GUI.AgentGUI.Insurance.InsuranceConfirmModuleController;
import GUI.GuiHelper.CommonGUIMethods;
import GUI.GuiHelper.RegEX;
import GUI.StartMain;
import Insurance.Helper.PaymentOption;
import Insurance.Insurance;
import Insurance.Property.HomeInsurance;
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


/**
 * Created by steinar on 16.04.2015.
 */
public final class HouseModuleController implements CommonGUIMethods
{
    @FXML
    private TextField adress;
    @FXML
    private TextField citynumber;
    @FXML
    private TextField city;
    @FXML
    private ComboBox<String> constructedIn;
    @FXML
    private TextField constructionYear;
    @FXML
    private ComboBox<String> buildingType;
    @FXML
    private TextField grossArea;
    @FXML
    private TextField primaryArea;
    @FXML
    private TextField taxedvalue;
    @FXML
    private Checkbox extended;

    @FXML
    private DatePicker fromDate;
    @FXML
    private ComboBox<Integer> deductible;
    @FXML
    private ComboBox<String> paymentOption;

    private ObservableList<String> buildingMaterials = FXCollections.observableArrayList();
    private ObservableList<String> buildingTypes = FXCollections.observableArrayList();
    private static HomeInsurance insurance;

    @FXML
    private void initialize() {
        deductible.setItems(Insurance.deductablenumbers);
        paymentOption.setItems(Insurance.paymentOptionNames);

        //todo: ENUM?
        buildingMaterials.addAll("Mur", "Tre");
        constructedIn.setItems(buildingMaterials);

        //todo: ENUM?
        buildingTypes.addAll("Rekkehus", "Enebolig", "Leilighet", "Tomannsbolig");
        buildingTypes.sorted();
        buildingType.setItems(buildingTypes);

        setListeners();
        addCSSValidation();
        clearFields();

        if (StartMain.currentCustomer.getPersonProperty().isNotNull().get()) {
            Customer customer = StartMain.currentCustomer.getPerson();
            adress.setText(customer.getAdress());
            city.setText(customer.getCity());
            citynumber.setText(String.valueOf( customer.getCitynumbr()));
        }
    }

    @Override
    public void clearFields() {
        resetTextFields(adress, citynumber, city, constructionYear, grossArea, primaryArea, taxedvalue);
        fromDate.setValue(LocalDate.now());

        //explanation -> https://thierrywasyl.wordpress.com/2014/02/09/update-your-scene-in-javafx/
        Runnable clear = () ->
        {
            constructedIn.setValue( constructedIn.getItems().get(0) );
            buildingType.setValue( buildingType.getItems().get(1) );
            deductible.setValue( deductible.getItems().get(1) );
            paymentOption.setValue( paymentOption.getItems().get(0)  );
        };

        if(Platform.isFxApplicationThread())
            clear.run();
        else
            Platform.runLater(clear);
    }

    @Override
    public void addCSSValidation() {
        RegEX.addCSSTextValidation(adress, RegEX.isAdress());
        RegEX.addCSSTextValidation(citynumber, RegEX.isNumber(4));
        RegEX.addCSSTextValidation(city, RegEX.isLetters());
        RegEX.addCSSTextValidation(constructionYear, RegEX.isNumber(4)); //todo: make another regex for this
        RegEX.addCSSTextValidation(grossArea, RegEX.isNumber()); //todo:make regex for this
        RegEX.addCSSTextValidation(primaryArea, RegEX.isNumber()); //todo:make regex for this
    }

    private void setListeners() {
        AgentInsuranceController.emptyscreen.addListener(observable -> {
            SimpleBooleanProperty bool = (SimpleBooleanProperty) observable;
            if (bool.get())
                clearFields();
        });
        addComboboxListener(constructedIn, buildingType, deductible, paymentOption);
    }

    private boolean checkValidation() {
        if (adress.getLength() < 3 && adress.getPseudoClassStates().isEmpty() )
            return false;
        if (!citynumber.getPseudoClassStates().isEmpty())
            return false;
        if (city.getLength() < 2 && city.getPseudoClassStates().isEmpty())
            return false;
        if (!constructionYear.getPseudoClassStates().isEmpty())
            return false;
        if (grossArea.getLength() < 1 && grossArea.getPseudoClassStates().isEmpty())
            return false;
        if (primaryArea.getLength() < 1 && primaryArea.getPseudoClassStates().isEmpty())
            return false;
        return true;
    }

    private void addComboboxListener(ComboBox... comboBoxes) {
        for( ComboBox comboBox : comboBoxes )
            comboBox.valueProperty().addListener(observable -> makeInsurance() );
    }

    private void makeInsurance() {
        if (!checkValidation())
            return;
        ObservableList<PaymentOption> list = FXCollections.observableArrayList(PaymentOption.values());
        FXCollections.reverse(list);

        PaymentOption selectedPayment = list.get(paymentOption.getSelectionModel().getSelectedIndex());
        insurance = new HomeInsurance(fromDate.getValue(),0, "some policy", StartMain.currentCustomer.getPerson(), selectedPayment,
                deductible.getValue(), adress.getText(), parseInt(citynumber), city.getText(), parseInt(constructionYear), constructedIn.getValue(),
                parseInt(taxedvalue), buildingType.getValue(), parseInt(grossArea), parseInt(primaryArea), false);

        showPremium();
    }

    private void showPremium() {
        int paymentTermins = insurance.getPaymentOption().getValue();
        InsuranceConfirmModuleController.yearlyPremiumLabel.setValue(insurance.getAnnualPremium());
        InsuranceConfirmModuleController.totalFeeLabel.setValue( Insurance.paymentFee * paymentTermins );
        InsuranceConfirmModuleController.paymentEachTerminLabel.setValue((insurance.getAnnualPremium() + Insurance.paymentFee * paymentTermins) / paymentTermins);
    }

    private int parseInt(TextField textField) {
        return Integer.parseInt(textField.getText());
    }
}