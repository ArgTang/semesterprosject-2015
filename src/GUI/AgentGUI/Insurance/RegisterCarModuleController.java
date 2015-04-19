package GUI.AgentGUI.Insurance;

import GUI.AgentGUI.CommonGUIMethods;
import GUI.GuiHelper.RegEX;
import GUI.StartMain;
import Insurance.Helper.PaymentOption;
import Person.Person;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;

/**
 * Created by steinar on 17.04.2015.
 */
public final class RegisterCarModuleController implements CommonGUIMethods
{
    @FXML
    Label customername;
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
    private ObservableList<Integer> deductablenumbers = FXCollections.observableArrayList();
    private ObservableList<String> paymentOptionNummber = FXCollections.observableArrayList();

    private StringProperty personName = new SimpleStringProperty();

    @FXML
    private void initialize()
    {
        //todo: some of these might be used for more insurances -> move into Vehicle Class
        kaskoValues.addAll("Ansvar", "Delkasko", "Fullkasko");
        kasko.setItems(kaskoValues);

        bonusValues.addAll(0, 10, 20, 30, 40, 50, 60, 70, 75);
        bonus.setItems(bonusValues);

        kmValues.addAll("8000", "12000", "16000", "ubegrenset");
        yearlyKM.setItems(kmValues);

        deductablenumbers.addAll(2000, 4000, 8000, 12000);
        deductible.setItems(deductablenumbers);

        paymentOptionNummber.addAll(PaymentOption.MONTHLY.getName(), PaymentOption.QUARTERLY.getName(), PaymentOption.YEARLY.getName());
        paymentOption.setItems(paymentOptionNummber);

        customername.textProperty().bind(personName);
        customername.setStyle("-fx-font-weight: bold;");
        addCSSValidation();
        clearFields();
        setInsuranceChoiceListener();
        Person person = StartMain.currentCustomer.getProperty();
        if (person != null)
            setCustomer(person);
        setCurrentPersonListener();
    }

    @Override
    public void clearFields() {
        resetTextField(registrationnumber);
        resetTextField(km);
        resetTextField(maker);
        resetTextField(model);
        resetTextField(motorsize);
        resetTextField(modelYear);
        resetTextField(color);
        resetTextField(buyPrice);
        personName.setValue("");
        fromDate.setValue(LocalDate.now());
        kasko.setValue("Fullkasko");
        bonus.setValue(20);
        yearlyKM.setValue("12000");
        deductible.setValue(4000);
        paymentOption.setValue(PaymentOption.MONTHLY.getName());
    }

    @Override
    public void addCSSValidation() {
        RegEX.addCSSTextValidation(registrationnumber, RegEX.isAllChars()); //todo: rege for this?
        RegEX.addCSSTextValidation(km, RegEX.isNumber());
        RegEX.addCSSTextValidation(maker, RegEX.isLetters());
        RegEX.addCSSTextValidation(model, RegEX.isAllChars()); //todo: is chars or is letters
        RegEX.addCSSTextValidation(motorsize, RegEX.isNumber());
        RegEX.addCSSTextValidation(modelYear, RegEX.isNumber(4));
        RegEX.addCSSTextValidation(color, RegEX.isLetters());
        RegEX.addCSSTextValidation(buyPrice, RegEX.isNumber());
    }
    private void setInsuranceChoiceListener()
    {
        AgentInsuranceController.insuranceChoiceListener.getStringProperty().addListener(
                observable -> {
                    StringProperty string = (StringProperty) observable;
                    if (string.getValue().equals("tøm skjerm"))
                        clearFields();
                });

    }

    //todo: put these into an interface (DRY)?? also need to redraw or update label after new name is set
    private void setCurrentPersonListener()
    {
        StartMain.currentCustomer.getPersonProperty().addListener(
                observable -> {
                    SimpleObjectProperty<Person> property = (SimpleObjectProperty) observable;
                    setCustomer(property.getValue());
                });
    }

    private void setCustomer(Person person)
    {
        //customername.setText( person.getFirstName() + " " + person.getLastName() );
        personName.setValue( person.getFirstName() + " " + person.getLastName() );
    }
}
