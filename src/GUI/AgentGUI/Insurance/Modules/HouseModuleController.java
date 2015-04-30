package GUI.AgentGUI.Insurance.Modules;

import GUI.GuiHelper.CommonGUIMethods;
import GUI.GuiHelper.RegEX;
import Insurance.Helper.PaymentOption;
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

import static GUI.AgentGUI.Insurance.AgentInsuranceController.emptyscreen;
import static GUI.AgentGUI.Insurance.InsuranceConfirmModuleController.*;
import static GUI.GuiHelper.RegEX.*;
import static GUI.StartMain.currentCustomer;
import static Insurance.Insurance.*;


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
        deductible.setItems(deductablenumbers);
        paymentOption.setItems(paymentOptionNames);

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

        if (currentCustomer.getPersonProperty().isNotNull().get()) {
            Customer customer = currentCustomer.getPerson();
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
        Runnable clear = () -> {
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
        RegEX.addCSSTextValidation(adress, isAdress());
        RegEX.addCSSTextValidation(citynumber, isNumber(4));
        RegEX.addCSSTextValidation(city, isLetters());
        RegEX.addCSSTextValidation(constructionYear, isNumber(4)); //todo: make another regex for this
        RegEX.addCSSTextValidation(grossArea, isNumber()); //todo:make regex for this
        RegEX.addCSSTextValidation(primaryArea, isNumber()); //todo:make regex for this
    }

    private void setListeners() {
        emptyscreen.addListener(observable -> {
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
        insurance = new HomeInsurance(fromDate.getValue(),0, "some policy", currentCustomer.getPerson(), selectedPayment,
                deductible.getValue(), adress.getText(), parseInt(citynumber), city.getText(), parseInt(constructionYear), constructedIn.getValue(),
                parseInt(taxedvalue), buildingType.getValue(), parseInt(grossArea), parseInt(primaryArea), false);

        showPremium();
    }

    private void showPremium() {
        int paymentTermins = insurance.getPaymentOption().getValue();
        yearlyPremiumLabel.setValue(insurance.getAnnualPremium());
        totalFeeLabel.setValue( paymentFee * paymentTermins );
        paymentEachTerminLabel.setValue((insurance.getAnnualPremium() + paymentFee * paymentTermins) / paymentTermins);
    }

    private int parseInt(TextField textField) {
        return Integer.parseInt(textField.getText());
    }
}