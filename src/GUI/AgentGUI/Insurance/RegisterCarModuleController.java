package GUI.AgentGUI.Insurance;

import GUI.GuiHelper.CommonGUIMethods;
import GUI.GuiHelper.RegEX;
import GUI.StartMain;
import Insurance.Insurance;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.time.LocalDate;

/**
 * Created by steinar on 17.04.2015.
 */
public final class RegisterCarModuleController implements CommonGUIMethods
{
    @FXML
    TextField registrationnumber;
    @FXML
    TextField km;
    @FXML
    TextField maker;
    @FXML
    TextField model;
    @FXML
    TextField motorsize;
    @FXML
    TextField modelYear;
    @FXML
    TextField color;
    @FXML
    TextField buyPrice;

    @FXML
    DatePicker fromDate;
    @FXML
    CheckBox ageRequirements;
    @FXML
    ComboBox kasko;
    @FXML
    ComboBox bonus;
    @FXML
    ComboBox yearlyKM;
    @FXML
    ComboBox deductible;
    @FXML
    ComboBox paymentOption;

    //combobox values:
    private ObservableList<String> kaskoValues = FXCollections.observableArrayList();
    private ObservableList<Integer> bonusValues = FXCollections.observableArrayList();
    private ObservableList<String> kmValues = FXCollections.observableArrayList();

    @FXML
    private void initialize()
    {
        //todo: some of these might be used for more insurances -> move into Vehicle Class
        kaskoValues.addAll("Ansvar", "Delkasko", "Fullkasko");
        kasko.setItems(kaskoValues);

        bonusValues.addAll(0, 10, 20, 30, 40, 50, 60, 70, 75);
        bonus.setItems(bonusValues);

        kmValues.addAll("8 000km", "12 000km", "16 000km", "ubegrenset km");
        yearlyKM.setItems(kmValues);

        deductible.setItems(AgentInsuranceController.deductablenumbers);

        paymentOption.setItems(AgentInsuranceController.paymentOptionNummber);

        addCSSValidation();
        setInsuranceChoiceListener();
        clearFields();
    }

    @Override
    public void clearFields()
    {
        resetTextFields(registrationnumber, km, maker, model, motorsize, color, buyPrice);
        fromDate.setValue(LocalDate.now());
        ageRequirements.setIndeterminate(false);

        //explanation -> https://thierrywasyl.wordpress.com/2014/02/09/update-your-scene-in-javafx/
        Runnable clear = () ->
        {
            kasko.setValue( kasko.getItems().get(1) );
            bonus.setValue( bonus.getItems().get(2) );
            yearlyKM.setValue( yearlyKM.getItems().get(0) );
            deductible.setValue( deductible.getItems().get(1) );
            paymentOption.setValue( paymentOption.getItems().get(0) );
        };

        if(Platform.isFxApplicationThread())
            clear.run();
        else
            Platform.runLater(clear);
    }

    @Override
    public void addCSSValidation() {
        RegEX.addCSSTextValidation(registrationnumber, RegEX.isAllChars()); //todo: regex for this?
        RegEX.addCSSTextValidation(model, RegEX.isAllChars()); //todo: is chars or is letters
        RegEX.addCSSTextValidation(modelYear, RegEX.isNumber(4));
        addCSSTextValidation(RegEX.isNumber(), km, motorsize, buyPrice);
        addCSSTextValidation(RegEX.isLetters(), maker, color);
    }

    private void setInsuranceChoiceListener()
    {

        StartMain.currentInsurance.getInsuranceProperty().addListener(
                observable -> {
                    ObjectProperty<Insurance> insurance = (ObjectProperty<Insurance>) observable;

                    if (insurance.isNotNull().get()) {
                        //todo: set insurance
                    } else
                        clearFields();
                }
        );

        AgentInsuranceController.emptyscreen.addListener(observable -> {
            SimpleBooleanProperty bool = (SimpleBooleanProperty) observable;
            if (bool.get())
                clearFields();
        });
    }
}