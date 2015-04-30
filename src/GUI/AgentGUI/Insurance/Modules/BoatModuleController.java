package GUI.AgentGUI.Insurance.Modules;

/**
 * Created by steinar on 27.04.2015.
 */

import GUI.GuiHelper.CommonGUIMethods;
import GUI.GuiHelper.RegEX;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.time.LocalDate;

import static GUI.AgentGUI.Insurance.AgentInsuranceController.emptyscreen;
import static GUI.GuiHelper.RegEX.*;
import static Insurance.Insurance.*;

public class BoatModuleController implements CommonGUIMethods
{
    @FXML
    private TextField buyPrice;
    @FXML
    private TextField motorsize;
    @FXML
    private TextField model;
    @FXML
    private TextField maker;
    @FXML
    private TextField modelYear;

    @FXML
    private ComboBox<String> type;
    @FXML
    private TextField licenceNumber;
    @FXML
    private TextField speed;
    @FXML
    private TextField size;
    @FXML
    private TextField harbor;

    @FXML
    private DatePicker fromDate;
    @FXML
    private ComboBox<String> kasko;
    @FXML
    private ComboBox<Integer> deductible;
    @FXML
    private ComboBox<String> paymentOption;

    private ObservableList<String> kaskoValues = FXCollections.observableArrayList();
    private ObservableList<String> types = FXCollections.observableArrayList();

    @FXML
    public void initialize()
    {
        kaskoValues.addAll("Delkasko", "Fullkasko", "Pluss (tyveri)");
        kasko.setItems(kaskoValues);

        types.addAll("Innenbordsmotor", "Utenbordsmotor", "Seilbåt");
        type.setItems(types);

        deductible.setItems(deductablenumbers);
        paymentOption.setItems(paymentOptionNames);

        addCSSValidation();
        clearFields();
        setListeners();
    }

    @Override
    public void clearFields() {
        fromDate.setValue(LocalDate.now());
        resetTextFields(speed, size, motorsize, buyPrice, model, maker, harbor, licenceNumber, modelYear);

        //explanation -> https://thierrywasyl.wordpress.com/2014/02/09/update-your-scene-in-javafx/
        Runnable clear = () -> {
            type.setValue( type.getItems().get(1) );
            kasko.setValue(kasko.getItems().get(2));
            deductible.setValue( deductible.getItems().get(1) );
            paymentOption.setValue( paymentOption.getItems().get(1) );
        };

        if(Platform.isFxApplicationThread())
            clear.run();
        else
            Platform.runLater(clear);
    }

    @Override
    public void addCSSValidation()
    {
        addCSSTextValidation(isNumber(), speed, size, motorsize);
        RegEX.addCSSTextValidation(buyPrice, isNumber());
        addCSSTextValidation(isLetters(), model, maker, harbor); //todo: allow numbers for harbor? i.e. peer 16
        addCSSTextValidation(isAllChars(), licenceNumber, model);
        RegEX.addCSSTextValidation(modelYear, isNumber(4));
    }

    private void setListeners()
    {
        emptyscreen.addListener(observable -> {
            SimpleBooleanProperty bool = (SimpleBooleanProperty) observable;
            if (bool.get())
                clearFields();
        });
    }
}