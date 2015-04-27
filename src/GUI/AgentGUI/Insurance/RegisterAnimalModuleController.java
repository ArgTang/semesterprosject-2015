package GUI.AgentGUI.Insurance;

import GUI.GuiHelper.CommonGUIMethods;
import GUI.GuiHelper.RegEX;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;

public class RegisterAnimalModuleController implements CommonGUIMethods {

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
        paymentOption.setItems(AgentInsuranceController.paymentOptionNummber);

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
        fromDate.setValue(LocalDate.now());
        boy.setSelected(true);
        animalType.setValue(animalTypes.get(0));
        paymentOption.setValue(AgentInsuranceController.paymentOptionNummber.get(0));

        resetTextFields(name, breed, color, chipID, usage, country, sorority, value);
    }

    @Override
    public void addCSSValidation() {
        addCSSTextValidation(RegEX.isLetters(), name, breed, color, country);
        RegEX.addCSSTextValidation(chipID, RegEX.isAllChars()); //todo: make regex for this
        RegEX.addCSSTextValidation(usage, RegEX.isLetters()); //todo: delete when enum is ready
        RegEX.addCSSTextValidation(sorority, RegEX.isLetters()); //todo: delete when enum is ready
        RegEX.addCSSTextValidation(value, RegEX.isNumber());
    }

    private void setListeners() {
        AgentInsuranceController.emptyscreen.addListener(observable -> {
            SimpleBooleanProperty bool = (SimpleBooleanProperty) observable;
            if (bool.get())
                clearFields();
        });
    }
}