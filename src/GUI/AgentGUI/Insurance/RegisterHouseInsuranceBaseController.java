package GUI.AgentGUI.Insurance;

import GUI.AgentGUI.CommonGUIMethods;
import GUI.GuiHelper.RegEX;
import GUI.WindowChangeListener;
import Insurance.Helper.PaymentOption;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;


/**
 * Created by steinar on 16.04.2015.
 */
public final class RegisterHouseInsuranceBaseController implements CommonGUIMethods
{
    @FXML
    Label customername;
    @FXML
    TextField adress;
    @FXML
    TextField citynumber;
    @FXML
    TextField city;
    @FXML
    TextField constructedIn;
    @FXML
    TextField constructionYear;
    @FXML
    TextField buildingType;
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
    private ObservableList<String> paymentOptionNummber = FXCollections.observableArrayList();

    @FXML
    private void initialize()
    {
        deductablenumbers.addAll(2000, 4000, 8000, 12000);
        deductible.setItems(deductablenumbers);
        deductible.setValue(4000);
        paymentOptionNummber.addAll(PaymentOption.MONTHLY.getName(), PaymentOption.QUARTERLY.getName(), PaymentOption.YEARLY.getName());
        paymentOption.setValue(PaymentOption.MONTHLY.getName());
        paymentOption.setItems(paymentOptionNummber);
        fromDate.setValue(LocalDate.now());

        setInsuranceChoiceListener();
        addCSSValidation();
    }

    @Override
    public void clearFields()
    {
        resetTextField(adress);
        resetTextField(citynumber);
        resetTextField(city);
        resetTextField(constructedIn);
        resetTextField(constructionYear);
        resetTextField(buildingType);
        resetTextField(grossArea);
        resetTextField(primaryArea);
        resetTextField(taxedvalue);
        fromDate.setValue(LocalDate.now());
        deductible.setValue(4000);
        paymentOption.setValue(PaymentOption.MONTHLY.getName());
    }

    @Override
    public void addCSSValidation()
    {
        RegEX.addCSSTextValidation(adress, RegEX.isAdress());
        RegEX.addCSSTextValidation(citynumber, RegEX.isNumber(4));
        RegEX.addCSSTextValidation(city, RegEX.isLetters());
        RegEX.addCSSTextValidation(constructedIn, RegEX.isLetters());
        RegEX.addCSSTextValidation(constructionYear, RegEX.isNumber()); //todo: make another regex for this
        RegEX.addCSSTextValidation(buildingType, RegEX.isLetters());
        RegEX.addCSSTextValidation(grossArea, RegEX.isNumber()); //todo:make regex for this
        RegEX.addCSSTextValidation(primaryArea, RegEX.isNumber()); //todo:make regex for this
    }
    private void setInsuranceChoiceListener()
    {
        AgentInsuranceController.insuranceChoiceListener.getStringProperty().addListener(
                observable -> {
                    StringProperty string = (StringProperty) observable;
                    if( string.getValue().equals("tøm skjerm") )
                        clearFields();
                });

    }
}
