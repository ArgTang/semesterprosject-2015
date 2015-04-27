package GUI.AgentGUI.Insurance;

import GUI.GuiHelper.CommonGUIMethods;
import GUI.GuiHelper.RegEX;
import Insurance.Helper.PaymentOption;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;


/**
 * Created by steinar on 16.04.2015.
 */
public final class RegisterHouseInsuranceBaseController implements CommonGUIMethods
{
    @FXML
    TextField adress;
    @FXML
    TextField citynumber;
    @FXML
    TextField city;
    @FXML
    ComboBox<String> constructedIn;
    @FXML
    TextField constructionYear;
    @FXML
    ComboBox<String> buildingType;
    @FXML
    TextField grossArea;
    @FXML
    TextField primaryArea;
    @FXML
    TextField taxedvalue;

    @FXML
    DatePicker fromDate;
    @FXML
    ComboBox deductible;
    @FXML
    ComboBox paymentOption;

    private ObservableList<Integer> deductablenumbers = FXCollections.observableArrayList();
    private ObservableList<String> buildingMaterials = FXCollections.observableArrayList();
    private ObservableList<String> buildingTypes = FXCollections.observableArrayList();

    @FXML
    private void initialize()
    {
        deductablenumbers.addAll(2000, 4000, 8000, 12000);
        deductible.setItems(deductablenumbers);

        paymentOption.setItems(AgentInsuranceController.paymentOptionNummber);

        //todo: ENUM?
        buildingMaterials.addAll("Mur", "Tre", "strå");
        constructedIn.setItems(buildingMaterials);

        //todo: ENUM?
        buildingTypes.addAll("Rekkehus", "Enebolig", "Leilighet", "Tomannsbolig");
        buildingTypes.sorted();
        buildingType.setItems(buildingTypes);

        setListeners();
        addCSSValidation();
        clearFields();
    }

    @Override
    public void clearFields()
    {
        resetTextFields(adress, citynumber, city, constructionYear, grossArea, primaryArea, taxedvalue);
        constructedIn.setValue(buildingMaterials.get(0));
        buildingType.setValue(buildingTypes.get(1));
        fromDate.setValue(LocalDate.now());
        deductible.setValue(deductablenumbers.get(1));
        paymentOption.setValue(AgentInsuranceController.paymentOptionNummber.get(0));
    }

    @Override
    public void addCSSValidation()
    {
        RegEX.addCSSTextValidation(adress, RegEX.isAdress());
        RegEX.addCSSTextValidation(citynumber, RegEX.isNumber(4));
        RegEX.addCSSTextValidation(city, RegEX.isLetters());
        RegEX.addCSSTextValidation(constructionYear, RegEX.isNumber()); //todo: make another regex for this
        RegEX.addCSSTextValidation(grossArea, RegEX.isNumber()); //todo:make regex for this
        RegEX.addCSSTextValidation(primaryArea, RegEX.isNumber()); //todo:make regex for this
    }
    private void setListeners()
    {
        AgentInsuranceController.emptyscreen.addListener(observable -> {
            SimpleBooleanProperty bool = (SimpleBooleanProperty) observable;
            if (bool.get())
                clearFields();
        });
    }
}
