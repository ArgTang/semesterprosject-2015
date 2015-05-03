package GUI.AgentGUI.Insurance.Modules;

import GUI.AgentGUI.Insurance.AgentInsuranceController;
import GUI.GuiHelper.CommonGUIMethods;
import GUI.GuiHelper.RegEX;
import Insurance.Animal.AnimalInsurance;
import Insurance.Helper.PaymentOption;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.stream.Collectors;

import static GUI.AgentGUI.Insurance.AgentInsuranceController.insuranceChoiceListener;
import static GUI.AgentGUI.Insurance.InsuranceConfirmModuleController.confirmOrderButton;
import static GUI.GuiHelper.RegEX.*;
import static Insurance.Insurance.paymentOptions;

public final class AnimalModuleController extends CommonGUIMethods
{
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
    private TextField country;
    @FXML
    private TextField value;
    @FXML
    private DatePicker fromDate;
    @FXML
    private ComboBox<String> paymentOption;

    private static final ObservableList<String> animalTypes = FXCollections.observableArrayList();

    private static AnimalInsurance insurance;
    @FXML
    public void initialize() {
        paymentOption.setItems(paymentOptions.stream()
                                             .map(PaymentOption::getName)
                                             .collect(Collectors.toCollection(FXCollections::observableArrayList)));

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
        resetTextFields(name, breed, color, chipID, usage, country, value);
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
        RegEX.addCSSTextValidation(value, isNumber());
    }

    @Override
    protected void setCustomer() {

    }

    @Override
    protected boolean checkValidation() {
        return validationIsOk(3, name, breed, color, country, usage, value, chipID);
    }

    @Override
    protected void setListeners() {
        AgentInsuranceController.emptyscreenButton.addListener(observable -> {
            SimpleBooleanProperty bool = (SimpleBooleanProperty) observable;
            if (bool.get())
                clearFields();
        });

        confirmOrderButton.addListener(observable -> {
            BooleanProperty bool = (BooleanProperty) observable;
            if (insuranceChoiceListener.getPropertyString().equals("[Hus]") && bool.get()) {
                makeInsurance();
                saveInsurance(insurance);
            }
        });

        birthday.valueProperty().addListener(
                (observable, oldValue, newValue) -> {
                    //if too old refuse insurance
                    //horsemaxage = 22
                    //DogMaxage = 10
                    //catmaxage = 13
                });
    }

    @Override
    protected void makeInsurance() {
        if (!checkValidation())
            return;

        PaymentOption selectedPayment = paymentOptions.get( paymentOption.getSelectionModel().getSelectedIndex() );

    }
}