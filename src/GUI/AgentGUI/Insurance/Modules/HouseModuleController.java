package GUI.AgentGUI.Insurance.Modules;

import GUI.GuiHelper.CommonGUIMethods;
import GUI.GuiHelper.RegEX;
import Insurance.Helper.PaymentOption;
import Insurance.Property.HomeInsurance;
import Person.Customer;
import Register.RegisterCustomer;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.awt.*;
import java.time.LocalDate;
import java.util.stream.Collectors;

import static GUI.AgentGUI.Insurance.AgentInsuranceController.emptyscreenButton;
import static GUI.AgentGUI.Insurance.AgentInsuranceController.insuranceChoiceListener;
import static GUI.AgentGUI.Insurance.InsuranceConfirmModuleController.confirmOrderButton;
import static GUI.GuiHelper.RegEX.*;
import static GUI.StartMain.currentCustomer;
import static Insurance.Insurance.deductablenumbers;
import static Insurance.Insurance.paymentOptions;


/**
 * Created by steinar on 16.04.2015.
 */
public final class HouseModuleController extends CommonGUIMethods
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
    @Override
    protected void initialize() {
        deductible.setItems(deductablenumbers);
        paymentOption.setItems(paymentOptions.stream()
                                             .map(PaymentOption::getName)
                                             .collect(Collectors.toCollection(FXCollections::observableArrayList)));

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
        setCustomer();
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
        RegEX.addCSSTextValidation(citynumber, isNumberWithLength(4));
        RegEX.addCSSTextValidation(city, isLetters());
        RegEX.addCSSTextValidation(constructionYear, isNumberWithLength(4)); //todo: make another regex for this
        RegEX.addCSSTextValidation(grossArea, isNumber()); //todo:make regex for this
        RegEX.addCSSTextValidation(primaryArea, isNumber()); //todo:make regex for this
        RegEX.addCSSTextValidation(taxedvalue, isNumber());
    }

    @Override
    protected void setListeners() {
        emptyscreenButton.addListener(observable -> {
            SimpleBooleanProperty bool = (SimpleBooleanProperty) observable;
            if (bool.get())
                clearFields();
        });

        addComboboxListener(constructedIn, buildingType, deductible, paymentOption);
        addTextfieldListener(constructionYear, grossArea, primaryArea, taxedvalue);

        confirmOrderButton.addListener(observable -> {
            BooleanProperty bool = (BooleanProperty) observable;
            if ( bool.get() && insuranceChoiceListener.getPropertyString().equals("Hus") ) {
                System.out.println("saveinsurance");
                makeInsurance();
                saveInsurance(insurance);
            }
        });

        insuranceChoiceListener.getStringProperty().addListener( observable -> {
            SimpleStringProperty property = (SimpleStringProperty) observable;
            if (property.get().equals("[Hus]")) {
                setCustomer();
                makeInsurance();
            }
        });
    }

    @Override
    protected boolean checkValidation() {
        //todo: implement varang?
        if (!validationIsOk(3).test(adress) )
            return false;
        if (pseudoOK.test(citynumber))
            return false;
        if (!validationIsOk(2).test(city))
            return false;
        if (pseudoOK.test(constructionYear))
            return false;
        if (!validationIsOk(2).test(grossArea))
            return false;
        if (!validationIsOk(2).test(primaryArea))
            return false;
        if (!validationIsOk(3).test(taxedvalue))
            return false;
        return true;
    }

    @Override
    protected void makeInsurance() {
        if (!checkValidation())
            return;

        PaymentOption selectedPayment = paymentOptions.get(paymentOption.getSelectionModel().getSelectedIndex());

        try {
            insurance = new HomeInsurance(fromDate.getValue(), 0, "some policy", currentCustomer.getPerson(), selectedPayment,
                    deductible.getValue(), adress.getText(), parseInt(citynumber), city.getText(), parseInt(constructionYear), constructedIn.getValue(),
                    parseInt(taxedvalue), buildingType.getValue(), parseInt(grossArea), parseInt(primaryArea), false);
            showPremium(insurance);
        } catch (Exception expected) {
            //if currentcustomer == null getPremium with tempcustomer
            HomeInsurance testinsurance = new HomeInsurance(fromDate.getValue(), 0, "some policy", RegisterCustomer.tempCustomer, selectedPayment,
                    deductible.getValue(), adress.getText(), parseInt(citynumber), city.getText(), parseInt(constructionYear), constructedIn.getValue(),
                    parseInt(taxedvalue), buildingType.getValue(), parseInt(grossArea), parseInt(primaryArea), false);
            showPremium(testinsurance);
        }
    }

    protected void setCustomer() {
        Customer customer = getCustomerOrDummyCustomer();

        adress.setText(customer.getAdress());
        city.setText(customer.getCity());
        citynumber.setText(String.valueOf( customer.getCitynumber()));
    }
}