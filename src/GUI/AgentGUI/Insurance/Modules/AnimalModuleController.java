package GUI.AgentGUI.Insurance.Modules;

import GUI.AgentGUI.Insurance.AgentInsuranceController;
import GUI.GuiHelper.CommonGUIMethods;
import GUI.GuiHelper.RegEX;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;

import static GUI.GuiHelper.RegEX.*;
import static Insurance.Insurance.paymentOptionNames;

public class AnimalModuleController implements CommonGUIMethods {

    @FXML
    private ComboBox<String> animalType;
    @FXML
    private TextField name;
    @FXML
    private DatePicker birthday;
    @FXML
    private TextField breed;
    @FXML
    private TextField color;

    @FXML
    private RadioButton girl;
    @FXML
    private RadioButton boy;

    @FXML
    private TextField chipID;
    @FXML
    private TextField usage;
    @FXML
    private CheckBox neutered;
    @FXML
    private TextField country;
    @FXML
    private TextField sorority;
    @FXML
    private TextField value;
    @FXML
    private DatePicker fromDate;
    @FXML
    private ComboBox<String> paymentOption;

    private static final ObservableList<String> animalTypes = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        paymentOption.setItems(paymentOptionNames);

        animalTypes.setAll("Hund", "Katt", "Hest");
        animalType.setItems(animalTypes);

        ToggleGroup group = new ToggleGroup();
        girl.setToggleGroup(group);
        boy.setToggleGroup(group);

        addCSSValidation();
        setListeners();
        clearFields();
    }

    @Override
    public void clearFields() {
        resetTextFields(name, breed, color, chipID, usage, country, sorority, value);
        fromDate.setValue(LocalDate.now());
        boy.setSelected(true);

        //explanation -> https://thierrywasyl.wordpress.com/2014/02/09/update-your-scene-in-javafx/
        Runnable clear = () -> {
            animalType.setValue( animalType.getItems().get(0) );
            paymentOption.setValue( paymentOption.getItems().get(0) );
        };

        if(Platform.isFxApplicationThread())
            clear.run();
        else
            Platform.runLater(clear);
    }

    @Override
    public void addCSSValidation() {
        addCSSTextValidation(isLetters(), name, breed, color, country);
        RegEX.addCSSTextValidation(chipID, isAllChars()); //todo: make regex for this
        RegEX.addCSSTextValidation(usage, isLetters()); //todo: delete when enum is ready
        RegEX.addCSSTextValidation(sorority, isLetters()); //todo: delete when enum is ready
        RegEX.addCSSTextValidation(value, isNumber());
    }

    private void setListeners() {
        AgentInsuranceController.emptyscreen.addListener(observable -> {
            SimpleBooleanProperty bool = (SimpleBooleanProperty) observable;
            if (bool.get())
                clearFields();
        });
    }
}